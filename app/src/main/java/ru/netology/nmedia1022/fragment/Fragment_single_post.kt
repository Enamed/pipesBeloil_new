package ru.netology.nmedia1022.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.databinding.ActivityAppBinding.bind
import ru.netology.nmedia1022.databinding.FragmentSinglePostBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.utils.Utils
import ru.netology.nmedia1022.viewmodel.PostViewModel

class fragment_single_post : Fragment(R.layout.fragment_single_post) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSinglePostBinding.bind(view)
        val bundle = Bundle()
        val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )

        val id = arguments?.getLong("id")
        var singlePost: Post? = null

      // binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner){ posts ->
            posts.map { post ->
                if(post.id == id) {
                    singlePost = post
                }
            }

        }

        fun bind(post: Post) {
            with(binding) {
                author.text = singlePost?.author
                published.text = singlePost?.published
                content.text = singlePost?.content
                countVisio.text = singlePost?.let { it -> Utils.numbers(it.countVisio) }
                like.isChecked = singlePost?.likedByMe == true
                like.text = post.likes.toString()
                share.text = post.countShare.toString()

                if (post.video == null) {
                    video.visibility = View.GONE
                } else {
                    video.visibility = View.VISIBLE
                }

//                like.setOnClickListener {
//                    listener.like(post)
//                }
//
//                video.setOnClickListener {
//                    listener.onVidioClicked(post)
//                }
//                content.setOnClickListener{
//                    listener.onPost(post)
//                }




                menu.setOnClickListener {
                    PopupMenu(it.context, it).apply {
                        inflate(R.menu.posts_menu)
                        setOnMenuItemClickListener { item ->
                            when (item.itemId) {
                                R.id.remove -> {
                                    singlePost?.id?.let { id -> viewModel.removeById(id) }
                                    true
                                }
                                R.id.edit -> {
                                    singlePost?.let { it -> viewModel.edit(it) }
                                    bundle.putString("content", singlePost?.content)

                                    findNavController().navigate(R.id.action_feedFragment_to_fragment_single_post)
                                    bundle
                                    //listener.edit(post)
                                    true
                                }
                                else -> false
                            }
                        }
                        show()
                    }
                }
                share.setOnClickListener {
                   singlePost?.countShare?.let { it ->
                       Utils.numbers(it)
                   }
                }


            }
        }



    }


}
