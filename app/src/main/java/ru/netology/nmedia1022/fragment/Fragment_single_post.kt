package ru.netology.nmedia1022.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_single_post.*
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.adapter.PostActionListener
import ru.netology.nmedia1022.adapter.PostViewHolder
import ru.netology.nmedia1022.adapter.PostsAdapter
import ru.netology.nmedia1022.databinding.ActivityAppBinding.bind
import ru.netology.nmedia1022.databinding.FragmentSinglePostBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.fragment.NewPostFragment.Companion.textArg
import ru.netology.nmedia1022.utils.StringArg
import ru.netology.nmedia1022.utils.Utils
import ru.netology.nmedia1022.viewmodel.PostViewModel

class fragment_single_post : Fragment(R.layout.fragment_single_post) {

    companion object {
        var Bundle.textArg by StringArg
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSinglePostBinding.bind(view)
        val bundle = Bundle()
        val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )

        val id = arguments?.getLong("id")
        var singlePost: Post? = null

        val postViewHolder = PostViewHolder(binding.post, object : PostActionListener {
            override fun edit(post: Post) {
                    viewModel.edit(post)
                    bundle.putString("content", post.content)
                    findNavController().navigate(R.id.newPostFragment, bundle)

            }

            override fun like(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun delete(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun share(post: Post) {
                viewModel.share(post.id)
                    //создание интента для шаринга
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(intent, getString(R.string.share))
                    startActivity(intent)
            }

            override fun onPost(post: Post) {
                    val id = post.id
                    bundle.putLong("id", id)
                    findNavController().navigate(R.id.fragment_single_post)
            }
        })




        viewModel.data.observe(viewLifecycleOwner) { posts ->
            val id = arguments?.textArg?.toLong()
            val post = posts.find { it.id == id } ?: kotlin.run {
                findNavController().navigateUp()
                return@observe
            }
          postViewHolder.bind(post)
        }

        }

//        viewModel.data.observe(viewLifecycleOwner) { posts ->
//            posts.map { post ->
//                if (post.id == id) {
//                    singlePost = post
//                }
//                adapter.submitList(posts)
//                singlePost?.id?.let { id -> viewModel. }
//
//
//
//            }
//
//            singlePost?.id
//            if (it.id == 0L) {
//                return@observe
//            }
//        }

//            fun bind(post: Post) {
//                with(binding) {
//                    author.text = singlePost?.author
//                    published.text = singlePost?.published
//                    content.text = singlePost?.content
//                    countVisio.text = singlePost?.let { it -> Utils.numbers(it.countVisio) }
//                    like.isChecked = singlePost?.likedByMe == true
//                    like.text = post.likes.toString()
//                    share.text = post.countShare.toString()
//
//                    if (post.video == null) {
//                        video.visibility = View.GONE
//                    } else {
//                        video.visibility = View.VISIBLE
//                    }
//
//                    menu.setOnClickListener {
//                        PopupMenu(it.context, it).apply {
//                            inflate(R.menu.posts_menu)
//                            setOnMenuItemClickListener { item ->
//                                when (item.itemId) {
//                                    R.id.remove -> {
//                                        singlePost?.id?.let { id -> viewModel.removeById(id) }
//                                        true
//                                    }
//                                    R.id.edit -> {
//                                        singlePost?.let { it -> viewModel.edit(it) }
//                                        bundle.putString("content", singlePost?.content)
//
//                                        findNavController().navigate(R.id.action_feedFragment_to_fragment_single_post)
//                                        bundle
//                                        true
//                                    }
//                                    else -> false
//                                }
//                            }
//                            show()
//                        }
//                    }
//                    share.setOnClickListener {
//                        singlePost?.countShare?.let { it ->
//                            Utils.numbers(it)
//                        }
//                    }
//
//
//                }
//            }


        }






