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
import ru.netology.nmedia1022.databinding.FeedBinding
import ru.netology.nmedia1022.databinding.MainBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.viewmodel.PostViewModel

class MainFragment: Fragment(R.layout.main) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainBinding.inflate(inflater, container, false)

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

            }
        )




       // binding.list.adapter = adapter
        //binding.list.animation = null   // отключаем анимацию
//        viewModel.data.observe(viewLifecycleOwner) { posts ->
//            adapter.submitList(posts)
//        }

binding.disclaimer.setOnClickListener {
    findNavController().navigate(R.id.disclaimerFragment)
}

        binding.imgBt.setOnClickListener {
            findNavController().navigate(R.id.btFragment)
        }
        binding.logo.setOnClickListener {
            findNavController().navigate(R.id.btCard)
        }

        binding.preambula.setOnClickListener {
            findNavController().navigate(R.id.feedFragment)
        }
        return binding.root
    }
}
