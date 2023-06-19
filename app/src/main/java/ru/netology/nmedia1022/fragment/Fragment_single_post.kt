package ru.netology.nmedia1022.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.adapter.PostActionListener
import ru.netology.nmedia1022.adapter.PostViewHolder
import ru.netology.nmedia1022.databinding.FragmentSinglePostBinding
import ru.netology.nmedia1022.dto.Post

import ru.netology.nmedia1022.utils.StringArg

import ru.netology.nmedia1022.viewmodel.PostViewModel

class Fragment_single_post : Fragment(R.layout.fragment_single_post) {

    companion object {
        var Bundle.textArg by StringArg
    }

         override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
         val viewModel: PostViewModel by viewModels(
                ownerProducer = ::requireParentFragment
            )

            val bundle = Bundle()

             val binding = FragmentSinglePostBinding.inflate(inflater, container, false)
            //  val id = arguments?.getLong("id")
       // var singlePost: Post? = null

        val postViewHolder = PostViewHolder(binding.post, object : PostActionListener {

            override fun edit(post: Post) {
                    viewModel.edit(post)
                    bundle.putString("content", post.content)
                 //   findNavController().navigate(R.id.newPostFragment, bundle)
            }

            override fun like(post: Post) {
//                viewModel.likeById(post.id, post.likedByMe)
            }

            override fun delete(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun share(post: Post) {
//                     viewModel.share(post.id)
                    //создание интента для шаринга
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                 //   val shareIntent = Intent.createChooser(intent, getString(R.string.share))
                    startActivity(intent)

            }

            override fun onPost(post: Post) {
//                val id = post.id
//                    bundle.putLong("id", id)
//                    findNavController().navigate(R.id.fragment_single_post)
            }
        })



//выводит карточку
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            val id = arguments?.textArg?.toLong()
//            val post = posts.find { it.id == id } ?: kotlin.run {
//                findNavController().navigateUp()
//                return@observe
//            }
//          postViewHolder.bind(post)
        }
return binding.root
        }



        }






