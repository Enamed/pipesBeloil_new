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
import ru.netology.nmedia1022.databinding.MainBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.utils.CompanionArg.Companion.longArg
import ru.netology.nmedia1022.utils.CompanionArg.Companion.textArg
import ru.netology.nmedia1022.viewmodel.PostViewModel

class MainFragment : Fragment(R.layout.main) {
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

        binding.imgUbt.setOnClickListener {
            val ids: String = "UBT"
            bundle.putString("UBT", ids)
            findNavController().navigate(R.id.action_mainFragment_to_btObFragment, Bundle().apply {
                textArg = "UBT"
            })
        }

        binding.imgNubt.setOnClickListener {
            val ids: String = "NUBT"
            bundle.putString("NUBT", ids)
            findNavController().navigate(R.id.action_mainFragment_to_btObFragment, Bundle().apply {
                textArg = "NUBT"
            })
        }

        binding.imgTbt.setOnClickListener {
            val ids: String = "TBT"
            bundle.putString("TBT", ids)
            findNavController().navigate(R.id.action_mainFragment_to_btObFragment, Bundle().apply {
                textArg = "TBT"
            })
        }

        binding.imgVbt.setOnClickListener {
            val ids: String = "VBT"
            bundle.putString("VBT", ids)
            findNavController().navigate(R.id.action_mainFragment_to_btObFragment, Bundle().apply {
                textArg = "VBT"
            })
        }

        binding.imgNktGl.setOnClickListener {
            val ids: String = "NKTgl"
            bundle.putString("NKTgl", ids)
            findNavController().navigate(R.id.action_mainFragment_to_btObFragment, Bundle().apply {
                textArg = "NKTgl"
            })
        }

        binding.imgNktVis.setOnClickListener {
            val ids: String = "NKTvis"
            bundle.putString("NKTvis", ids)
            findNavController().navigate(R.id.action_mainFragment_to_btObFragment, Bundle().apply {
                textArg = "NKTvis"
            })
        }
        binding.imgNktPlastic.setOnClickListener {
            val ids: String = "NKTplast"
            bundle.putString("NKTplast", ids)
            findNavController().navigate(R.id.action_mainFragment_to_btObFragment, Bundle().apply {
                textArg = "NKTplast"
            })
        }

        binding.imgOttm.setOnClickListener {
            val ids: String = "OTTM"
            bundle.putString("OTTM", ids)
            findNavController().navigate(R.id.action_mainFragment_to_btObFragment, Bundle().apply {
                textArg = "OTTM"
            })
        }

        binding.imgBC.setOnClickListener {
            val ids: String = "BC"
            bundle.putString("BC", ids)
            findNavController().navigate(R.id.action_mainFragment_to_btObFragment, Bundle().apply {
                textArg = "BC"
            })
        }

        binding.imgPrem.setOnClickListener {
            val ids: String = "PREM"
            bundle.putString("PREM", ids)
            findNavController().navigate(R.id.action_mainFragment_to_btObFragment, Bundle().apply {
                textArg = "PREM"
            })
        }


//        binding.logo.setOnClickListener {
//            findNavController().navigate(R.id.btCard)
//        }
//        binding.preambula.setOnClickListener {
//            findNavController().navigate(R.id.feedFragment)
//        }
        return binding.root
    }
}
