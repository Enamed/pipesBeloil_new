package ru.netology.nmedia1022.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia1022.dto.Post

class PostRepositoryInMemory : PostRepository {

    private var posts = listOf(
        Post(
            id = 4,
            author = "ID =4 Нетология - университет интернет профессий",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появи",
            published = "16 october 2022 in 11:27",
            likedByMe = false,
            countLike = 999,
            countShare = 12,
            countVisio = 9_000
        ),
        Post(
            id = 3,
            author = "ID =3 Нетология - университет интернет профессий",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появи",
            published = "16 october 2022 in 11:27",
            likedByMe = false,
            countLike = 999,
            countShare = 12,
            countVisio = 9_000
        ),
        Post(
            id = 2,
            author = "ID =2 Нетология - университет интернет профессий",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появи",
            published = "16 october 2022 in 11:27",
            likedByMe = false,
            countLike = 999,
            countShare = 12,
            countVisio = 9_000
        ),
        Post(
            id = 1,
            author = "ID = 1 Нетология - университет интернет профессий",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появи",
            published = "16 october 2022 in 11:27",
            likedByMe = false,
            countLike = 999,
            countShare = 12,
            countVisio = 1_000
        )
    )
    private val data = MutableLiveData(posts)

    override fun getALL(): LiveData<List<Post>> = data


    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                countLike = if (it.likedByMe) it.countLike - 1 else it.countLike + 1
            )
        }
        data.value = posts
    }

    override fun share(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                countShare = it.countShare + 1
            )
        }
        data.value = posts
    }


    //DELETE
    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

//    override fun closeEdit(id: Long) {
//        posts = posts.filter { it.id != id }
//        data.value = posts
//    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(id = posts.firstOrNull()?.id?.inc() ?: 0)
            ) + posts
            data.value = posts
            return
        }
       posts =  posts.map {
            if (it.id == post.id) {
                it.copy(content = post.content)
            } else {
                it
            }
        }
        data.value = posts

//        posts = listOf(
//            post.copy(
//                id = nextId++,
//                author = "Me",
//                likedByMe = false,
//                published = "now"
//            )
//        ) + posts
//        data.value = posts
//        return
    }

//    override fun share(){
//        val counter = post.countShare +1
//        post = post.copy(share = true, countShare = counter)
//        data.value = post
//    }

}