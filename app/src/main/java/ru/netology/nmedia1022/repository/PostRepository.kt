package ru.netology.nmedia1022.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.dto.PostList

interface PostRepository {
    fun getALL(): List<Post>
    fun getList(): List<PostList>

    fun likeById(id: Long)
    fun share(id: Long)
    fun save(post: Post)
    //delete post
    fun removeById(id: Long)
  //  fun closeEdit(id: Long)



}