package ru.netology.nmedia1022.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia1022.db.AppDb
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.repository.PostRepository
import ru.netology.nmedia1022.repository.PostRepositorySQLiteImpl

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = "",
    //countLike = 0,
    countShare = 0,
    countVisio = 0,
    likes = 0,
    video = null
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository =
        PostRepositorySQLiteImpl(AppDb.getInstance(application).postDao)
    val data = repository.getALL()
    val edited = MutableLiveData(empty)

    val prev = MutableLiveData(empty)

    fun likeById(id: Long) = repository.likeById(id)

    fun share(id: Long) = repository.share(id)

    fun removeById(id: Long) = repository.removeById(id)

    fun edit(post: Post) {
        prev.value = post
        edited.value = post
    }
    fun save() {
//        edited.value?.let {
//            repository.save(it)
//        }
//        edited.value = empty




    }
    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value?.let {
            repository.save(it.copy(content = text))
        }
        edited.value = empty
    }
}




