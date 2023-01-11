package ru.netology.nmedia1022.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia1022.dto.Post

interface PostRepository {
    fun getALL(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun share(id: Long)
    fun save(post: Post)
    //delete post
    fun removeById(id: Long)
  //  fun closeEdit(id: Long)



}