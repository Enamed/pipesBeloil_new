package ru.netology.nmedia1022.fragment.bt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.adapter.PostActionListener
import ru.netology.nmedia1022.adapter.PostsAdapter
import ru.netology.nmedia1022.databinding.BtBinding
import ru.netology.nmedia1022.databinding.BtCardBinding
import ru.netology.nmedia1022.databinding.BtObBinding
import ru.netology.nmedia1022.databinding.DisclaimerBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.viewmodel.PostViewModel

class BtCard: Fragment(R.layout.bt_card) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BtCardBinding.inflate(inflater, container, false)

        val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )

        val bundle = Bundle()
        val adapter = PostsAdapter(
            object : PostActionListener {

                override fun like(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun share(post: Post) {
//                    val intent = Intent().apply {
//                        action = Intent.ACTION_SEND
//                        putExtra(Intent.EXTRA_TEXT, post.content)
//                        type = "text/plain"
//                    }
//                    val shareIntent = Intent.createChooser(intent, "Поделится")
//                    startActivity(shareIntent)
//                    viewModel.share(post.id)
//                }

                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }

                    val shareIntent =
                        Intent.createChooser(intent, getString(R.string.chooser_share_post))
                    startActivity(shareIntent)
                }

                override fun delete(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun edit(post: Post) {
                    viewModel.edit(post)
//                    findNavController().navigate(R.id.newPostFragment,
//                        Bundle().apply {
//                            textArg = post.content
//                        })
                }

                override fun onVidioClicked(post: Post) {
                    val youtubeInternet = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    startActivity(youtubeInternet)
                }

                override fun onPost(post: Post) {
                    val id = post.id
                    bundle.putLong("id", id)
                    findNavController().navigate(R.id.fragment_single_post, Bundle().apply {
//                        textArg = post.id.toString()
                    })
                }
            }

        )


        //binding.list.adapter = adapter


        viewModel.data.observe(viewLifecycleOwner) { state ->
//            binding.progress.isVisible = state.loading
//            binding.swipeRefreshLayout.isRefreshing = state.refreshing
//            if (state.error) {
//                Snackbar.make(binding.root, R.string.error_loading, Snackbar.LENGTH_LONG)
//                    .setAction(R.string.retry_loading) { viewModel.loadPosts() }
//                    .show()
//            }
//        }
        }


        viewModel.data.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.posts)

//            binding.emptyText.isVisible = state.empty

        }

            viewModel.edited.observe(viewLifecycleOwner) {
                if (it.id == 0L) {
                    return@observe
                }

                findNavController().navigate(R.id.to_newPostFragment, Bundle().apply {
                    putString("content", it.content)
                })
            }


//        binding.swipeRefreshLayout.setOnRefreshListener {
//            viewModel.refreshPosts()
//        }
//
//        binding.fab.setOnClickListener {
//            findNavController().navigate(R.id.to_newPostFragment)
//        }


            // binding.list.adapter = adapter
            //binding.list.animation = null   // отключаем анимацию
//        viewModel.data.observe(viewLifecycleOwner) { posts ->
//            adapter.submitList(posts)
//        }

            binding.imgBack.setOnClickListener {
                findNavController().navigate(R.id.btFragment)
            }


        return binding.root
        }

    }
