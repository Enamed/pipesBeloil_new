package ru.netology.nmedia1022.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia1022.dto.Post

class PostRepositorySharedPrefsImpl(context: Context) : PostRepository {
    private val prefs = context.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    private val gson = Gson()
    private val key = "posts"
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type

    override fun getALL(): LiveData<List<Post>> {
        TODO("Not yet implemented")
    }

    override fun likeById(id: Long) {


    }

    init {
        prefs.getString(key, null)?.let {
            posts = gson.fromJson(it, type)
        }
    }

    override fun share(id: Long) {
        TODO("Not yet implemented")
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(id = posts.firstOrNull()?.id?.inc() ?: 0)
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if (it.id == post.id) {
                it.copy(content = post.content)
            } else {
                it
            }
        }
        data.value = posts

        sync()
    }

    override fun removeById(id: Long) {

        posts = posts.filter { it.id != id }
        data.value = posts

        sync()

    }

    private fun sync() {
        prefs.edit().apply() {
            putString(key, gson.toJson(posts))
            commit()
        }
    }

}