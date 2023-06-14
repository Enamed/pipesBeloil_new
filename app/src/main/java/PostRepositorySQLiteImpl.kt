//package ru.netology.nmedia1022.repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.map
//import ru.netology.nmedia1022.dao.PostDao
//import ru.netology.nmedia1022.dto.Post
//import ru.netology.nmedia1022.entity.PostEntity
//
//class PostRepositorySQLiteImpl(
//    private val dao: PostDao
//    ) : PostRepository {
//
//
//
//
//
//    override fun getALL(): LiveData<List<Post>> = dao.getAll()
//        .map{
//        it.map (PostEntity::toDto)
//    }
//
//    override fun likeById(id: Long) {
//
//        dao.likeById(id)
//
//
//    }
//
//    override fun share(id: Long) {
//dao.share(id)
//
//    }
//
//    override fun removeById(id: Long) {
//        dao.removeById(id)
//
//
//    }
//
//    override fun save(post: Post) {
//dao.insert(PostEntity.fromDto(post))
//    }
//}
//
//
////Прошлый вариант
////class PostRepositorySQLiteImpl(
////    private val dao: PostDao
////): PostRepository {
////    private var posts = emptyList<Post>()
////    override var data = MutableLiveData(posts)
////
////    init {
////
////        posts = dao.getAll()
////        data.value = posts
////    }
////
////
////    override fun getALL(): LiveData<List<Post>> = data
////
////    override fun likeById(id: Long) {
//////        dao.likeById(id)
//////        posts = posts.map {
//////            if (it.id != id) it else it.copy(
//////                likedByMe = !it.likedByMe,
//////                likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
//////            )
//////        }
//////        data.value = posts
////    }
////
////    override fun share(id: Long) {
//////        dao.share(id)(
//////            """
//////           UPDATE posts SET
//////               shared = shared + 1
//////           WHERE id = ?;
//////        """.trimIndent(), arrayOf(id)
//////        )
////    }
////
////    override fun save(post: Post) {
////
////        val id = post.id
////        val saved = dao.save(post)
////        posts = if (id == 0L) {
////            listOf(saved) + posts
////        } else {
////            posts.map {
////                if (it.id != id) it else saved
////            }
////        }
////        data.value = posts
////
////    }
////
////    override fun removeById(id: Long) {
////        TODO("Not yet implemented")
////    }
////
////}
////
//
//
