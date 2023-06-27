package ru.netology.nmedia1022.viewmodel

import FeedModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.repository.PostRepository
import ru.netology.nmedia1022.repository.PostRepositoryImpl
import ru.netology.nmedia1022.utils.SingleLiveEvent
import java.io.IOException
import kotlin.concurrent.thread

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

    fun loadPosts() {
        thread {
            // Начинаем загрузку
            _data.postValue(FeedModel(loading = true))
            try {
                // Данные успешно получены
                val postslist = repository.getList()
                FeedModel(postslist = postslist, empty = postslist.isEmpty())
            } catch (e: IOException) {
                // Получена ошибка
                FeedModel(error = true)
            }.also(_data::postValue)
        }
    }
}