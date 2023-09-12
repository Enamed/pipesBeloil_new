package ru.netology.nmedia1022.dto

data class Post(
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
    val video: String? = null,
    // для приложения белоруснефть
    val naimenovanie: String,
    val diametrTrub: String,
    val vnDimetrTrub: String,
    val thick: String,
    val ieu: String,
    val rastiagUsilie: String,
    val krutMoment: String,
    val vnDavlenie: String,
    val tipZamka: String,
    val narDiametrZamka: String,
    val vnDiametrZamka: String,
    val pin: String,
    val rastiagUsilieZamka: String,
    val krutMomentZamka: String,
    val g105: String,
    val priznak: String,
    val rastiagUsilie_1klass: String,
    val rastiagUsilie_2klass: String,
    val krutMoment_1klass: String,
    val krutMoment_2klass: String,
    val momentNew: String,
    val moment_1klass: String,
    val moment_2klass: String,
    val sigma_t: String,
    val sigma_v: String,
    val otnos_rast: String,
    val sertificat: String
    //dsfsdfdagadfgadfgadgdfga




)