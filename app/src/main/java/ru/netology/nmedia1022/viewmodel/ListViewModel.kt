package ru.netology.nmedia1022.viewmodel

import FeedModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
<<<<<<< HEAD
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.dto.PostList
=======
import ru.netology.nmedia1022.dto.Post
>>>>>>> e43862f (fronted)
import ru.netology.nmedia1022.repository.PostRepository
import ru.netology.nmedia1022.repository.PostRepositoryImpl
import ru.netology.nmedia1022.utils.SingleLiveEvent
import java.io.IOException
import kotlin.concurrent.thread

<<<<<<< HEAD
private val empty = PostList(
    id = 0,
    naimenovanie = ""


=======
private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    likes = 0,
    published = "",
    countShare = 0,
    countVisio = 0,
    naimenovanie = "",
    diametrTrub = "127",
    vnDimetrTrub = "51",
    thick = "9",
    ieu = "IEU",
    rastiagUsilie = "12",
    krutMoment = "12",
    vnDavlenie = "12",
    tipZamka = "ЗП-168",
    narDiametrZamka = "168",
    vnDiametrZamka = "51",
    pin = "З-133",
    rastiagUsilieZamka = "122",
    krutMomentZamka = "234",
    g105 = "S-135"
>>>>>>> e43862f (fronted)
)

class ListViewModel(application: Application) : AndroidViewModel(application) {
    // упрощённый вариант
    private val repository: PostRepository = PostRepositoryImpl()
    private val _data = MutableLiveData(FeedModel())
    val data: LiveData<FeedModel>
        get() = _data
    val edited = MutableLiveData(empty)
    private val _postCreated = SingleLiveEvent<Unit>()
    val postCreated: LiveData<Unit>
        get() = _postCreated

    init {
        loadPosts()
    }

<<<<<<< HEAD


//обновление свайпом
//    private val _dataState = MutableLiveData<FeedModel>()
//    val dataState: LiveData<FeedModel>
//        get() = _dataState
////



=======
>>>>>>> e43862f (fronted)
    fun loadPosts() {
        thread {
            // Начинаем загрузку
            _data.postValue(FeedModel(loading = true))
            try {
                // Данные успешно получены
<<<<<<< HEAD
                val posts = repository.getALL()
                FeedModel(posts = posts, empty = posts.isEmpty())
=======
                val postslist = repository.getList()
                FeedModel(postslist = postslist, empty = postslist.isEmpty())
>>>>>>> e43862f (fronted)
            } catch (e: IOException) {
                // Получена ошибка
                FeedModel(error = true)
            }.also(_data::postValue)
        }
    }
<<<<<<< HEAD

    fun refreshPosts() = viewModelScope.launch {
        thread {
            // Начинаем загрузку
            _data.postValue(FeedModel(refreshing = true))
            try {
                // Данные успешно получены
                val posts = repository.getALL()
                FeedModel(posts = posts, empty = posts.isEmpty())
            } catch (e: IOException) {
                // Получена ошибка
                FeedModel(error = true)
            }.also(_data::postValue)
        }
    }

//    fun save() {
//        edited.value?.let {
//            thread {
//                repository.save(it)
//                _postCreated.postValue(Unit)
//            }
//        }
//        edited.value = empty
//    }

//    fun edit(post: Post) {
//        edited.value = post
//    }

//    fun changeContent(content: String) {
//        val text = content.trim()
//        if (edited.value?.content == text) {
//            return
//        }
//        edited.value = edited.value?.copy(content = text)
//    }

    fun likeById(id: Long, likedByMe: Boolean) {
//
    }

    // обновление свайпом

    fun removeById(id: Long) {
        thread {
            // Оптимистичная модель
            val old = _data.value?.posts.orEmpty()
            _data.postValue(
                _data.value?.copy(posts = _data.value?.posts.orEmpty()
                    .filter { it.id != id }
                )
            )
            try {
                repository.removeById(id)
            } catch (e: IOException) {
                _data.postValue(_data.value?.copy(posts = old))
            }
        }
    }
=======
>>>>>>> e43862f (fronted)
}