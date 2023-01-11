package ru.netology.nmedia1022.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import ru.netology.nmedia1022.dto.Post
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PostDaoImpl(private val db: SQLiteDatabase) : PostDao {

    companion object {
        val DDL = """
        CREATE TABLE ${PostColumns.TABLE} (
            ${PostColumns.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${PostColumns.COLUMN_AUTHOR} TEXT NOT NULL,
            ${PostColumns.COLUMN_CONTENT} TEXT NOT NULL,
            ${PostColumns.COLUMN_PUBLISHED} TEXT NOT NULL,
            ${PostColumns.COLUMN_LIKED_BY_ME} BOOLEAN NOT NULL DEFAULT 0,
            ${PostColumns.COLUMN_LIKES} INTEGER NOT NULL DEFAULT 0,
            ${PostColumns.COLUMN_SHARES} INTEGER NOT NULL DEFAULT 0,
            ${PostColumns.COLUMN_VIEWS} INTEGER NOT NULL DEFAULT 0,
            ${PostColumns.COLUMN_VIDEO} TEXT DEFAULT NULL
        );
        """.trimIndent()
    }

    object PostColumns {
        const val TABLE = "posts"
        const val COLUMN_ID = "id"
        const val COLUMN_AUTHOR = "author"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_PUBLISHED = "published"
        const val COLUMN_LIKED_BY_ME = "likedByMe"
        const val COLUMN_LIKES = "liked"
        const val COLUMN_SHARES = "shared"
        const val COLUMN_VIEWS = "viewed"
        const val COLUMN_VIDEO = "video"
        val ALL_COLUMNS = arrayOf(
            COLUMN_ID,
            COLUMN_AUTHOR,
            COLUMN_CONTENT,
            COLUMN_PUBLISHED,
            COLUMN_LIKED_BY_ME,
            COLUMN_LIKES,
            COLUMN_SHARES,
            COLUMN_VIEWS,
            COLUMN_VIDEO
        )
    }

    override fun getAll(): List<Post> {
        val posts = mutableListOf<Post>()

        db.query(
            PostColumns.TABLE,
            PostColumns.ALL_COLUMNS,
            null,
            null,
            null,
            null,
            "${PostColumns.COLUMN_ID} DESC"
        ).use {
            while (it.moveToNext()) {
                posts.add(map(it))
            }
        }
        return posts
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun save(post: Post): Post {
        //отображение даты
               val dateTime = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("d MMMM yyyy" +" в " + "HH:mm"))
        //println(dateTime)
//отображение даты


        val values = ContentValues().apply {
            if (post.id != 0L) {
                put(PostColumns.COLUMN_ID, post.id)
            }
            put(PostColumns.COLUMN_AUTHOR, "Новость для программиста, посмотри")
            put(PostColumns.COLUMN_CONTENT, post.content)
            put(PostColumns.COLUMN_PUBLISHED, dateTime)
            put(PostColumns.COLUMN_LIKED_BY_ME, post.likedByMe)
            put(PostColumns.COLUMN_LIKES, post.likes)
            put(PostColumns.COLUMN_SHARES, post.share)
            //put(PostColumns.COLUMN_SHARE_COUNT, post.sharesCount)
        }

        val id = db.replace(PostColumns.TABLE, null, values)
        db.query(
            PostColumns.TABLE,
            PostColumns.ALL_COLUMNS,
            "${PostColumns.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        ).use {
            it.moveToNext()
            return map(it)
        }

    }

    override fun likeById(id: Long) {
        db.execSQL(
            """
           UPDATE posts SET
               liked = liked + CASE WHEN likedByMe THEN -1 ELSE 1 END,
               likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
           WHERE id = ?;
        """.trimIndent(), arrayOf(id)
        )
    }

    override fun share(id: Long) {
        db.execSQL(
            """
           UPDATE posts SET
               shared = shared + 1
           WHERE id = ?;
        """.trimIndent(), arrayOf(id)
        )
    }

    override fun removeById(id: Long) {
        db.delete(
            PostColumns.TABLE,
            "${PostColumns.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
    }

    private fun map(cursor: Cursor): Post {
        with(cursor) {
            return Post(
                id = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_ID)),
                author = getString(getColumnIndexOrThrow(PostColumns.COLUMN_AUTHOR)),
                content = getString(getColumnIndexOrThrow(PostColumns.COLUMN_CONTENT)),
                published = getString(getColumnIndexOrThrow(PostColumns.COLUMN_PUBLISHED)),
                likedByMe = getInt(getColumnIndexOrThrow(PostColumns.COLUMN_LIKED_BY_ME)) != 0,
                likes = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_LIKES)),
                countShare = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_SHARES)),
                countVisio = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_VIEWS)),
                video = getString(getColumnIndexOrThrow(PostColumns.COLUMN_VIDEO))
            )
        }
    }
}












///////////////////////////////////////////////////
//class PostDaoImpl(private val db: SQLiteDatabase) : PostDao {
//    companion object {
//        val DDL = """
//        CREATE TABLE ${PostColumns.TABLE} (
//            ${PostColumns.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
//            ${PostColumns.COLUMN_AUTHOR} TEXT NOT NULL,
//            ${PostColumns.COLUMN_CONTENT} TEXT NOT NULL,
//            ${PostColumns.COLUMN_PUBLISHED} TEXT NOT NULL,
//            ${PostColumns.COLUMN_WWW} INTEGER NOT NULL DEFAULT 0,
//            ${PostColumns.COLUMN_SHARES} INTEGER NOT NULL DEFAULT 0,
//            ${PostColumns.COLUMN_VIEWS} INTEGER NOT NULL DEFAULT 0
//        );
//        """.trimIndent()
//    }
//
//    object PostColumns {
//        const val TABLE = "posts"
//        const val COLUMN_ID = "id"
//        const val COLUMN_AUTHOR = "author"
//        const val COLUMN_CONTENT = "content"
//        const val COLUMN_PUBLISHED = "published"
//
//        const val COLUMN_WWW = "WWW"
//        const val COLUMN_SHARES = "shares"
//        const val COLUMN_VIEWS = "views"
//
//        val ALL_COLUMNS = arrayOf(
//            COLUMN_ID,
//            COLUMN_AUTHOR,
//            COLUMN_CONTENT,
//            COLUMN_PUBLISHED,
//            COLUMN_WWW,
//            COLUMN_PUBLISHED,
//            COLUMN_SHARES
//
//        )
//    }
//
//    override fun getAll(): List<Post> {
////        val posts = mutableListOf<Post>()
////        db.query(
////            PostColumns.TABLE,
////            PostColumns.ALL_COLUMNS,
////            null,
////            null,
////            null,
////            null,
////            "${PostColumns.COLUMN_ID} DESC"
////        ).use {
////            while (it.moveToNext()) {
////                posts.add(map(it))
////            }
////        }
////        return posts
//        TODO()
//    }
//
//    override fun save(post: Post): Post {
////        val values = ContentValues().apply {
////            if (post.id != 0L) {
////                put(PostColumns.COLUMN_ID, post.id)
////            }
////            // TODO: remove hardcoded values
////            put(PostColumns.COLUMN_AUTHOR, "Me")
////            put(PostColumns.COLUMN_CONTENT, post.content)
////            put(PostColumns.COLUMN_PUBLISHED, "now")
////        }
////        val id = db.replace(PostColumns.TABLE, null, values)
////        db.query(
////            PostColumns.TABLE,
////            PostColumns.ALL_COLUMNS,
////            "${PostColumns.COLUMN_ID} = ?",
////            arrayOf(id.toString()),
////            null,
////            null,
////            null,
////        ).use {
////            it.moveToNext()
////            return map(it)
////        }
//        TODO()
//    }
//
//    override fun likeById(id: Long) {
////        db.execSQL(
////            """
////           UPDATE posts SET
////               likes = likes + CASE WHEN likedByMe THEN -1 ELSE 1 END,
////               likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
////           WHERE id = ?;
////        """.trimIndent(), arrayOf(id)
////        )
//    }
//
////    override fun shareById(id: Long) {
////        db.execSQL(
////            """
////           UPDATE posts SET
////               shares = shares + 1
////           WHERE id = ?;
////        """.trimIndent(), arrayOf(id)
////        )
////    }
//
//    override fun removeById(id: Long) {
////        db.delete(
////            PostColumns.TABLE,
////            "${PostColumns.COLUMN_ID} = ?",
////            arrayOf(id.toString())
////        )
//    }
//
//    private fun map(cursor: Cursor): Post {
//        with(cursor) {
//            return Post(
//                id = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_ID)),
//                author = getString(getColumnIndexOrThrow(PostColumns.COLUMN_AUTHOR)),
//                content = getString(getColumnIndexOrThrow(PostColumns.COLUMN_CONTENT)),
//                published = getString(getColumnIndexOrThrow(PostColumns.COLUMN_PUBLISHED)),
//                likes = getInt(getColumnIndexOrThrow(PostColumns.COLUMN_WWW)),
//                countShare = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_SHARES)),
//                countVisio = getLong(getColumnIndexOrThrow(PostColumns.COLUMN_VIEWS))
//
//            )
//        }
//    }
//}