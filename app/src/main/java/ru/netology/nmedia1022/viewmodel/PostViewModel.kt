package ru.netology.nmedia1022.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia1022.databinding.ActivityMainBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.repository.PostRepository
import ru.netology.nmedia1022.repository.PostRepositoryInMemory

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = "",
    //countLike = 0,
    countShare = 0,
    countVisio = 0,
    likes = 0
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()
    val data = repository.getALL()
    val edited = MutableLiveData(empty)
    fun likeById(id: Long) = repository.likeById(id)
    //удаление
    fun removeById(id: Long) = repository.removeById(id)
    fun share(id: Long) = repository.share(id)

    fun edit(post: Post) {
        edited.value = post
    }
    //val edited = MutableLiveData(empty)

    fun save() {

        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun closeEdit() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun changeContent(content: String) {
        edited.value?.let {
            val text = content.trim()
            if (it.content == text) {
                return
            }
            edited.value = it.copy(content = text)
        }
    }

    fun editContent(text: String) {


        val formatted = text.trim()
        if (edited.value?.content == formatted) {
            return
        }
        edited.value = edited.value?.copy(content = formatted)
    }


}