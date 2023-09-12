package ru.netology.nmedia1022.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia1022.dto.Post

@Entity
data class PostEntity(
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
    val likes: Long,
    val video: String? = null,
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
//val attachment: Attachment? = null
) {

//    data class Attachment(val url: String)

    companion object {
        fun fromDto(dto: Post): PostEntity = with(dto) {
            PostEntity(
                id, author, content, published, likedByMe, share, countShare,
                countVisio, likes, naimenovanie, diametrTrub,
                vnDimetrTrub, thick, ieu, rastiagUsilie, krutMoment,
                vnDavlenie, tipZamka, narDiametrZamka, vnDiametrZamka,
                pin, rastiagUsilieZamka, krutMoment, krutMomentZamka, g105, priznak,
                rastiagUsilie_1klass, rastiagUsilie_2klass, krutMoment_1klass, krutMoment_2klass,
                momentNew, moment_1klass, moment_2klass, sigma_t, sigma_v, otnos_rast, sertificat
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
            likes = likes,
            naimenovanie = naimenovanie,
            diametrTrub = diametrTrub,
            vnDimetrTrub = vnDimetrTrub,
            thick = thick,
            ieu = ieu,
            rastiagUsilie = rastiagUsilie,
            krutMoment = krutMoment,
            vnDavlenie = vnDavlenie,
            tipZamka = tipZamka,
            narDiametrZamka = narDiametrZamka,
            vnDiametrZamka = vnDiametrZamka,
            pin = pin,
            rastiagUsilieZamka = rastiagUsilieZamka,
            krutMomentZamka = krutMomentZamka,
            g105 = g105,
            priznak = priznak,
            rastiagUsilie_1klass = rastiagUsilie_1klass,
          rastiagUsilie_2klass = rastiagUsilie_2klass,
       krutMoment_1klass = krutMoment_1klass,
        krutMoment_2klass = krutMoment_2klass,
         momentNew = momentNew,
        moment_1klass = moment_1klass,
        moment_2klass = moment_2klass,
        sigma_t = sigma_t,
        sigma_v = sigma_v,
        otnos_rast = otnos_rast,
            sertificat = sertificat
        )
    }
}