package ru.netology.nmedia1022.fragment.bt


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.databinding.BtBinding
import ru.netology.nmedia1022.utils.CompanionArg.Companion.textArg


class BtFragment: Fragment(R.layout.bt) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = BtBinding.inflate(inflater, container, false)

//        val viewModel: PostViewModel by viewModels(
//            ownerProducer = ::requireParentFragment
//        )


        val bundle = Bundle()


//        val fName: tring = intent.getStringExtra("fname")
//
//        val bundle = Intent.EXTRA_REPLACEMENT_EXTRAS

        // val photo = intent.getSerializableExtra("photo") as? Photo // если нет значения с таким ключом, то photo == null

//        val adapter = PostsAdapter(
//            object : PostActionListener {
//                override fun edit(post: Post) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun like(post: Post) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun delete(post: Post) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun share(post: Post) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onPost(post: Post) {
//                    TODO("Not yet implemented")
//                }
//
//            }
//        )


        // binding.list.adapter = adapter
        //binding.list.animation = null   // отключаем анимацию
//        viewModel.data.observe(viewLifecycleOwner) { posts ->
//            adapter.submitList(posts)
//        }

        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }

//        binding.buttonObsheoborot.setOnClickListener {
//            findNavController().navigate(R.id.btObFragment)
//
//
//        }


        binding.buttonObsheoborot.setOnClickListener {
            val ids = "BT"
            bundle.putString("BT", ids)
            findNavController().navigate(R.id.btObFragment, Bundle().apply {
                textArg = "BT"
            })
        }

        binding.pipes42.setOnClickListener {
            val ids = "42"
            bundle.putString("42", ids)
            findNavController().navigate(R.id.btObFragment, Bundle().apply {
                textArg = "42"
            })
        }

        binding.pipes60.setOnClickListener {
            val ids = "60,3"
            bundle.putString("60,3", ids)
            findNavController().navigate(R.id.btObFragment, Bundle().apply {
                textArg = "60,3"
            })
        }

        binding.pipes73.setOnClickListener {
            val ids = "73"
            bundle.putString("73", ids)
            findNavController().navigate(R.id.btObFragment, Bundle().apply {
                textArg = "73"
            })
        }
        binding.pipes89.setOnClickListener {
            val ids = "89"
            bundle.putString("89", ids)
            findNavController().navigate(R.id.btObFragment, Bundle().apply {
                textArg = "89"
            })
        }

        binding.pipes102.setOnClickListener {
            val ids = "102"
            bundle.putString("102", ids)
            findNavController().navigate(R.id.btObFragment, Bundle().apply {
                textArg = "102"
            })
        }

        binding.pipes114.setOnClickListener {
            val ids = "114"
            bundle.putString("114", ids)
            findNavController().navigate(R.id.btObFragment, Bundle().apply {
                textArg = "114"
            })
        }
        binding.pipes127.setOnClickListener {
            val ids = "127"
            bundle.putString("127", ids)
            findNavController().navigate(R.id.btObFragment, Bundle().apply {
                textArg = "127"
            })
        }

        binding.pipes140.setOnClickListener {
            val ids = "140"
            bundle.putString("140", ids)
            findNavController().navigate(R.id.btObFragment, Bundle().apply {
                textArg = "140"
            })
        }
//        60,3

//        viewModel.edited.observe(viewLifecycleOwner) {
////            if (it.id == 0L) {
////                return@observe
////            }
//            findNavController().navigate(R.id.btObFragment, Bundle().apply {
//                putString("content", it.content)
//            })
//
//
//        }
        return binding.root
    }
}