package ru.netology.nmedia1022.repository

import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.dto.PostList

interface ListRepository {

    fun getALL(): List<PostList>

}