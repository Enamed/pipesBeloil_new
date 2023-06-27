//package ru.netology.nmedia1022.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import ru.netology.nmedia1022.databinding.BtObBinding
//
//import ru.netology.nmedia1022.dto.Post
//
//
//class PostViewHolderTwo(
//    private val binding: BtObBinding,
//    private val listener: PostActionListenerTwo
//
//) : RecyclerView.ViewHolder(binding.root) {
//    fun bind(post: Post) {
//        with(binding) {
//           // naimenovanie.text = post.author
//            naimenovanie.text = post.naimenovanie
//
//            naimenovanie.setOnClickListener{
//                listener.onPost(post)
//            }
//        }
//    }
//}
//
//interface PostActionListenerTwo : PostActionListener {
//    override fun edit(post: Post) {
//        TODO("Not yet implemented")
//    }
//
//    override fun like(post: Post) {
//        TODO("Not yet implemented")
//    }
//
//    override fun delete(post: Post) {
//        TODO("Not yet implemented")
//    }
//
//    override fun share(post: Post) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onPost(post: Post) {
//        TODO("Not yet implemented")
//    }
//
//    //fun closeEdit (post: Post)
//}
//
//class PostsAdapterTwo(
//    private val listenerTwo: PostActionListenerTwo
//
//
//) : ListAdapter<Post, PostViewHolderTwo >(PostDiffItemCallback()) {
//
//    //вытаскиваем элемент из списка
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolderTwo {
//        val binding = BtObBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return PostViewHolderTwo(binding, listenerTwo)
//        //кнопка активная онпосткликед!!! еще одна
//    }
//
//    override fun onBindViewHolder(holder: PostViewHolderTwo, position: Int) {
//       // val post = getItem(position)
//        holder.bind(getItem(position))
//    }
//
//}
//
////class PostDiffItemCallback : DiffUtil.ItemCallback<Post>() {
////    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
////        oldItem.id == newItem.id
////
////    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
////        oldItem == newItem
////}