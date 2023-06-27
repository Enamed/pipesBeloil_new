package ru.netology.nmedia1022.fragment.bt

//import ru.netology.nmedia1022.databinding.BtBinding
//import ru.netology.nmedia1022.databinding.BtObBinding
//import ru.netology.nmedia1022.databinding.DisclaimerBinding

import android.R
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia1022.adapter.*
import ru.netology.nmedia1022.databinding.BtCardBinding
import ru.netology.nmedia1022.databinding.BtObBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.viewmodel.ListViewModel
import ru.netology.nmedia1022.viewmodel.PostViewModel


class BtObFragment: Fragment(ru.netology.nmedia1022.R.layout.bt_ob) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BtObBinding.inflate(inflater, container, false)

        val viewModel: ListViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )



//        fun Intent.getData(key: String): String {
//            return extras?.getString(key) ?: "intent is null"
//        }

        val bundle = Bundle()
        val adapter = PostsAdapter(
            object : PostActionListener {
                override fun edit(post: Post) {
                    TODO("Not yet implemented")
                }

                override fun like(post: Post) {
                    TODO("Not yet implemented")
                }

                override fun delete(post: Post) {
                    TODO("Not yet implemented")
                }

                override fun share(post: Post) {
                    TODO("Not yet implemented")
                }

                override fun onPost(post: Post) {
                    val id = post.id
                    bundle.putLong("id", id)
                    findNavController().navigate(ru.netology.nmedia1022.R.id.fragment_single_post, Bundle().apply {
//                        textArg = post.id.toString()
                    })
                }
            }

        )
//        val textViewName: TextView = binding.naimenovanie
//var gav = binding.naimenovanie
//        binding.ololo.setText(gav.toString())



        //  binding.naimenovanie.adapter = adapter
       // binding.list.adapter = adapter
//
//        val ttt = binding.naimenovanie
//
//binding.ololo.text = "ddd"
//

        viewModel.data.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.posts)

//            binding.emptyText.isVisible = state.empty

        }

            viewModel.edited.observe(viewLifecycleOwner) {
                if (it.id == 0L) {
                  //  binding.ololo.setText(it.naimenovanie)
                    return@observe

                }
             //   val content = arguments?.getString("naimenovanie")

           //     binding.ololo.setText(it.naimenovanie.)





//                findNavController().navigate(R.id.to_newPostFragment, Bundle().apply {
//                    putString("content", it.content)
//                })
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

//            binding.imgBack.setOnClickListener {
//                findNavController().navigate(R.id.btFragment)
//            }


        return binding.root
        }
    companion object {
        private const val TEXT_KEY = "naimenovanie"

        var Bundle.textArg: String?
            set(value) = putString(TEXT_KEY, value)
            get() = getString(TEXT_KEY)
    }
    }
