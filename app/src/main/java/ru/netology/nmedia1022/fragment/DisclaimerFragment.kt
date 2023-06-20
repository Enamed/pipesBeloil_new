package ru.netology.nmedia1022.fragment

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
import ru.netology.nmedia1022.databinding.DisclaimerBinding
import ru.netology.nmedia1022.databinding.MainBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.viewmodel.PostViewModel

class DisclaimerFragment: Fragment(R.layout.disclaimer) {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val binding = DisclaimerBinding.inflate(inflater, container, false)

            val viewModel: PostViewModel by viewModels(
                ownerProducer = ::requireParentFragment
            )

            val bundle = Bundle()






            // binding.list.adapter = adapter
            //binding.list.animation = null   // отключаем анимацию
//            viewModel.data.observe(viewLifecycleOwner) { posts ->
//                adapter.submitList(posts)
//            }

            binding.imgBack.setOnClickListener {
                findNavController().navigate(R.id.mainFragment)
            }


            return binding.root
        }
    }