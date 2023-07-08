package ru.netology.nmedia1022.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia1022.databinding.BtCardBinding

import ru.netology.nmedia1022.dto.Post


class PostViewHolder(
    private val binding: BtCardBinding,
    private val listener: PostActionListener

) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        with(binding) {
            naimenovanie.text = post.naimenovanie
            diametTrub.text = post.diametrTrub
            vnDimetrTrub.text = post.vnDimetrTrub
            thick.text = post.thick
            ieu.text = post.ieu
            rastiagUsilie.text = post.rastiagUsilie
            krutMoment.text = post.krutMoment
            vnDavlenie.text = post.vnDavlenie
            tipZamka.text = post.tipZamka
            narDiametrZamka.text = post.narDiametrZamka
            vnDiametrZamka.text = post.vnDiametrZamka
            pin.text = post.pin
            rastiagUsilieZamka.text = post.rastiagUsilieZamka
            krutMomentZamka.text = post.krutMoment
            g105.text = post.g105
      priznak.text = post.priznak



//            published.text = post.published
//            content.text = post.content
//
//            //    countLike.text = Utils.numbers(post.countLike)
//            //      countShare.text = Utils.numbers(post.countShare)
//            countVisio.text = Utils.numbers(post.countVisio)
//            like.isChecked = post.likedByMe
//
//
//
//            like.text = Utils.numbers(post.likes)
//
//            share.text = post.countShare.toString()
//
//            like.setOnClickListener {
//                listener.like(post)
//            }
//
//            video.setOnClickListener {
//                listener.onVidioClicked(post)
//            }
            naimenovanie.setOnClickListener{
                listener.onPost(post)
            }


//
//            if (post.video == null) {
//                video.visibility = View.GONE
//            } else {
//                video.visibility = View.VISIBLE
//            }
//
//
//            menu.setOnClickListener {
//                PopupMenu(it.context, it).apply {
//                    inflate(R.menu.posts_menu)
//                    setOnMenuItemClickListener { item ->
//                        when (item.itemId) {
//                            R.id.remove -> {
//                                listener.delete(post)
//                                true
//                            }
//                            R.id.edit -> {
//                                listener.edit(post)
//                                true
//                            }
//                            else -> false
//                        }
//                    }
//                    show()
//                }
//            }
//            share.setOnClickListener {
//                listener.share(post)
//            }


        }
    }
}

//class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
//    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
//        return oldItem == newItem
//    }
//}


interface PostActionListener {
    fun edit(post: Post)
    fun like(post: Post)
    fun delete(post: Post)
    fun share(post: Post)
    fun onVidioClicked(post: Post) = Unit
    fun onPost(post: Post)

    //fun closeEdit (post: Post)
}

class PostsAdapter(
    private val listener: PostActionListener


) : ListAdapter<Post, PostViewHolder>(PostDiffItemCallback()) {

    //вытаскиваем элемент из списка

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = BtCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostViewHolder(binding, listener)
        //кнопка активная онпосткликед!!! еще одна
    }

    
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
       // val post = getItem(position)
        holder.bind(getItem(position))
    }

}

class PostDiffItemCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem == newItem
}