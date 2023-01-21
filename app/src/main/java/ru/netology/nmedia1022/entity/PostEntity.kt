package ru.netology.nmedia1022.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia1022.dto.Post

@Entity
data class PostEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val share: Boolean = false,
//    var countLike: Long,
    val countShare: Long,
    val countVisio: Long,
    val likes:Long,
    val video: String? = null
//val attachment: Attachment? = null
) {

//    data class Attachment(val url: String)

    companion object {
        fun fromDto(dto: Post): PostEntity = with(dto) {
            PostEntity(
                id, author, content, published, likedByMe, share, countShare, countVisio, likes
            )
        }

    }

    fun toDto(): Post = with(this) {
        Post(
            id = id,
            author = author,
            content = content,
            published = published,
            likedByMe = likedByMe,
            share = share,
            countShare = countShare,
            countVisio = countVisio,
            likes = likes
        )
    }
}