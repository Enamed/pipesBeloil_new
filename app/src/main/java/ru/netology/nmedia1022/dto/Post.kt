package ru.netology.nmedia1022.dto

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val share: Boolean = false,
    var countLike: Long,
    var countShare: Long,
    val countVisio: Long

)