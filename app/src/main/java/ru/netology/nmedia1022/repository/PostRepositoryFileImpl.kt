package ru.netology.nmedia1022.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia1022.dto.Post

//data/data/nmedia...../files/posts.json
//сохраняет посты и редактирования в этот файл

class PostRepositoryFileImpl(
    private val context: Context
) : PostRepository {


    private val prefs = context.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)
    private var postId = 1L
    private val gson = Gson()
    private val filename = "posts.json"
    //обьясняем JSON что мы хотим получить List из Post
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type

    override fun getALL(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likes = if (it.likedByMe) it.likes - 1 else it.likes + 1

            )
        }
        data.value = posts

    }

    //из файла берем строку и парсим
    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)

                data.value = posts
            }
        } else {
            sync()
        }
    }

    override fun share(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                countShare = it.countShare + 1
            )
        }
        data.value = posts
        sync()
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
            //context.openFileOutput - откоывает OutputStream,
            //.bufferedWriter позволяет получить буферизированнный writer
            //.use позволяет автоматически закарыть ресурс после использования (будет закрыт writer, котороый сам закроет OutputStream
            context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
              //записываем данные
                it.write(gson.toJson(posts))
            }
        }
    }

