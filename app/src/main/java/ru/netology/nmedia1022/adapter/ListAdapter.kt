package ru.netology.nmedia1022.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia1022.databinding.BtCardBinding
import ru.netology.nmedia1022.databinding.BtObBinding
import ru.netology.nmedia1022.databinding.SpisokBinding

import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.dto.PostList

// выводит список труб //
class ListHolder(
    private val binding: SpisokBinding,
    private val listener: ListActionListener

) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        with(binding) {
            texttrub.text = post.naimenovanie

            texttrub.setOnClickListener{
                listener.onPost(post)
            }

        }
    }
}


//olkol

interface ListActionListener {
    fun edit(post: Post)
    fun like(post: Post)
    fun delete(post: Post)
    fun share(post: Post)
    fun onPost(post: Post)

    //fun closeEdit (post: Post)
}

class ListAdapter(
    private val listener: ListActionListener


) : ListAdapter<Post, ListHolder>(ListDiffItemCallback()) {

    //вытаскиваем элемент из списка

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val binding = SpisokBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListHolder(binding, listener)
        //кнопка активная онпосткликед!!! еще одна
    }


    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        // val post = getItem(position)
        holder.bind(getItem(position))
    }

}

class ListDiffItemCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem == newItem
}