package ru.netology.nmedia1022.adapter

import android.system.Os.remove
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.databinding.CardPostBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.adapter.PostViewHolder
import ru.netology.nmedia1022.utils.Utils

import ru.netology.nmedia1022.viewmodel.PostViewModel



//typealias OnLikeListener = (post : Post) -> Unit
//typealias ShareListener = (post : Post) -> Unit
//typealias OnRemoveListener = (post : Post) -> Unit



class PostViewHolder(
    private val binding: CardPostBinding,
    private val listener: PostActionListener

): RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post){
        with(binding){
        author.text = post.author
        published.text = post.published
        content.text = post.content
            countLike.text = Utils.numbers(post.countLike)
            countShare.text = Utils.numbers(post.countShare)
            countVisio.text = Utils.numbers(post.countVisio)
        like.setImageResource(
            if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_baseline_favorite_border_24
        )
        like.setOnClickListener {
          listener.like(post)
        }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.posts_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                listener.delete(post)
                                true
                            }
                            R.id.edit -> {
                                listener.edit(post)
                                true
                            }
                            else -> false
                        }
                    }
                    show()
                }
            }
            share.setOnClickListener {
               listener.share(post)
            }

    }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}


interface PostActionListener {
    fun edit (post: Post)
    fun like (post: Post)
    fun delete (post: Post)
    fun share (post: Post)
}
class PostsAdapter(
    private val listener: PostActionListener
//    private val onLikeListener: OnLikeListener,
//    private val shareListener: ShareListener,
//    private val onRemoveListener: OnRemoveListener

): ListAdapter<Post, PostViewHolder>(PostDiffItemCallback()) {

    //вытаскиваем элемент из списка

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, listener)
        //кнопка активная онпосткликед!!! еще одна
    }

    override fun onBindViewHolder(holder: PostViewHolder, position:Int){
        val post = getItem(position)
        holder.bind(getItem(position))
    }

    //

    //
}

class PostDiffItemCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem == newItem
}