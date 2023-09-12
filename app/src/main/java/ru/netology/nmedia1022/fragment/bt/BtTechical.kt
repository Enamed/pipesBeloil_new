package ru.netology.nmedia1022.fragment.bt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.databinding.BtCardBinding
import ru.netology.nmedia1022.databinding.BtTechnicalBinding
import ru.netology.nmedia1022.utils.CompanionArg.Companion.longArg
import ru.netology.nmedia1022.utils.CompanionArg.Companion.textArg
import ru.netology.nmedia1022.viewmodel.PostViewModel


class BtTechical : Fragment(R.layout.bt_technical) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BtTechnicalBinding.inflate(layoutInflater)
        val viewModel: PostViewModel by viewModels(::requireParentFragment)
        val bundle = Bundle()

        with(binding) {
            viewModel.data.observe(viewLifecycleOwner) { state ->
                val post = state.posts.find { it.id == arguments?.longArg }

                if (post != null) {
                  sertificat.text = post.sertificat

                }

            }
        }
        viewModel.edited.observe(viewLifecycleOwner) {
            if (it.id == 0L) {
                return@observe
            }


        }

//        viewModel.data.observe(viewLifecycleOwner) { state ->
//            adapter.submitList(state.posts)
//
//        }

        binding.imgBack.setOnClickListener {
            val redirect = arguments?.textArg
            when (arguments?.textArg) {
                redirect -> findNavController().navigate(
                    R.id.action_btCard_to_btObFragment,
                    Bundle().apply {
                        textArg = arguments?.textArg
                    })
            }
        }

        when (arguments?.textArg) {
            "BT" -> binding.txtTitle.setText("Общеоборотные трубы")
            "UBT" -> binding.txtTitle.setText("УБТ")
            "NUBT" -> binding.txtTitle.setText("НУБТ")
            "TBT" -> binding.txtTitle.setText("ТБТ")
            "VBT" -> binding.txtTitle.setText("ВБТ")
            "NKTgl" -> binding.txtTitle.setText("НКТ гладкая")
            "NKTvis" -> binding.txtTitle.setText("НКТ высаженная")
            "NKTplast" -> binding.txtTitle.setText("НКТ пластиковая")
            "OTTM" -> binding.txtTitle.setText("ОТ - резьба ОТТМ")
            "BC" -> binding.txtTitle.setText("ОТ - резьба BC")
            "PREM" -> binding.txtTitle.setText("ОТ - резьба PREMIUM")
            "42" -> binding.txtTitle.setText("Бурильная труба ⌀42")
        }





        return binding.root
    }

}
