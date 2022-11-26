package ru.netology.nmedia1022.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import ru.netology.nmedia1022.R

import ru.netology.nmedia1022.adapter.PostActionListener
import ru.netology.nmedia1022.adapter.PostsAdapter
import ru.netology.nmedia1022.databinding.FeedBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.viewmodel.PostViewModel
import ru.netology.nmedia1022.fragment.fragment_single_post as fragment_single_post1


//abstract fun onTouch(v: View!, event: MotionEvent!): Boolean

class FeedFragment : Fragment(R.layout.feed) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FeedBinding.bind(view)
        val bundle = Bundle()

//предоставляем одну viewModel для нескольких фрагментов
        val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )

        val adapter = PostsAdapter(
            object : PostActionListener {
                override fun edit(post: Post) {
                    viewModel.edit(post)
                    bundle.putString("content", post.content)
                    findNavController().navigate(R.id.to_newPostFragment, bundle)
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

                override fun onVidioClicked(post: Post) {
                    val youtubeInternet = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    startActivity(youtubeInternet)
                }

                override fun onPost(post: Post) {
                    val id = post.id
                    bundle.putLong("id", id)
                    findNavController().navigate(R.id.fragment_single_post)

                }

            }
        )


        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }
//        добавление новости через кнопку справа внизу

//        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
//result ?: return@registerForActivityResult
//            viewModel.changeContent(result)
//            viewModel.save()
//        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.to_newPostFragment)
            //newPostLauncher.launch()
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.to_newPostFragment)
            //newPostLauncher.launch()
        }

//            val editPostLauncher = registerForActivityResult(EditPostResultContract()) { text ->
//                text ?: return@registerForActivityResult
//                viewModel.changeContent(text)
//                viewModel.save()
//            }
        viewModel.edited.observe(viewLifecycleOwner) {
            if (it.id == 0L) {
                return@observe
            }
//                editPostLauncher.launch(it.content)
        }

    }
}



