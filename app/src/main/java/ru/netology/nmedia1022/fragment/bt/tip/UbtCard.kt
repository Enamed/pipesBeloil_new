package ru.netology.nmedia1022.fragment.bt.tip


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia1022.databinding.UbtCardBinding

import ru.netology.nmedia1022.utils.CompanionArg.Companion.longArg
import ru.netology.nmedia1022.utils.CompanionArg.Companion.textArg
import ru.netology.nmedia1022.viewmodel.PostViewModel


class UbtCard : Fragment(ru.netology.nmedia1022.R.layout.ubt_card) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = UbtCardBinding.inflate(layoutInflater)
        val viewModel: PostViewModel by viewModels(::requireParentFragment)
        val bundle = Bundle()

        with(binding) {
            viewModel.data.observe(viewLifecycleOwner) { state ->
                val post = state.posts.find { it.id == arguments?.longArg }

                if (post != null) {
                    naimenovanie.text = post.naimenovanie
                    diametTrub.text = post.diametrTrub
                    vnDimetrTrub.text = post.vnDimetrTrub
                    priznak.text = post.priznak
                    momentNew.text = post.momentNew
                    sigmaT.text = post.sigma_t
                    sigmaV.text = post.sigma_v
                    otnosRast.text = post.otnos_rast
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
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }


        //кнопка контрольные параметры
        binding.buttonSertificat.setOnClickListener {
            val id = it.id
            bundle.putString("id", id.toString())
            findNavController().navigate(ru.netology.nmedia1022.R.id.action_ubtCard_to_btTechnical, Bundle().apply {
                longArg = arguments?.longArg!!
            })
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
