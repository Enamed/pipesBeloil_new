//package ru.netology.nmedia1022.fragment
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import ru.netology.nmedia1022.R
//import ru.netology.nmedia1022.adapter.PostActionListener
//import ru.netology.nmedia1022.adapter.PostViewHolder
//import ru.netology.nmedia1022.databinding.CardPostBinding
//import ru.netology.nmedia1022.databinding.FragmentSinglePostBinding
//import ru.netology.nmedia1022.dto.Post
//import ru.netology.nmedia1022.utils.CompanionArg.Companion.longArg
//import androidx.navigation.findNavController
//
//
//import ru.netology.nmedia1022.viewmodel.PostViewModel
//
//class Fragment_single_post : Fragment(R.layout.fragment_single_post) {
//    val viewModel: PostViewModel by viewModels(
//        ownerProducer = ::requireParentFragment
//    )
//    var singlePost: Post? = null
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        val binding = FragmentSinglePostBinding.inflate(inflater, container, false)
//
//        val id = arguments?.getLong("id")
//        if (id == null) {
//            Toast.makeText(context,
//                "Отсутствует id",
//                Toast.LENGTH_SHORT
//            ).show()
//            return binding.root
//        }
//
//        viewModel.data.observe(viewLifecycleOwner) { posts ->
//
//            posts.map { post ->
//                if (post.id == id) {
//                    singlePost = post
//                    binding.post.apply {
//                        author.text = post.author
//
//                    }
//                }
//            }
//        }
//
//
//
//
//
//
//        return binding.root
//    }
//}
//
//
