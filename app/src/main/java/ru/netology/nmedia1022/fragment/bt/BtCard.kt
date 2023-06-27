package ru.netology.nmedia1022.fragment.bt

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
import ru.netology.nmedia1022.adapter.PostsAdapter
import ru.netology.nmedia1022.databinding.BtCardBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.entity.PostEntity
import ru.netology.nmedia1022.viewmodel.PostViewModel
import java.io.Serializable

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
                    TODO("Not yet implemented")
                }


            })




        viewModel.data.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.posts)

        }


            binding.imgBack.setOnClickListener {
                findNavController().navigate(R.id.btFragment)
            }


        return binding.root
        }

    }
