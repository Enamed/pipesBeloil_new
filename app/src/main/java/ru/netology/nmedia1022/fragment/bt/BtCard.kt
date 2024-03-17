package ru.netology.nmedia1022.fragment.bt

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia1022.databinding.BtCardBinding
import ru.netology.nmedia1022.utils.CompanionArg.Companion.longArg
import ru.netology.nmedia1022.utils.CompanionArg.Companion.textArg
import ru.netology.nmedia1022.viewmodel.PostViewModel


class BtCard : Fragment(ru.netology.nmedia1022.R.layout.bt_card) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BtCardBinding.inflate(layoutInflater)
        val viewModel: PostViewModel by viewModels(::requireParentFragment)
        val bundle = Bundle()

        with(binding) {
            viewModel.data.observe(viewLifecycleOwner) { state ->
                val post = state.posts.find { it.id == arguments?.longArg }

                if (post != null) {
                    naimenovanie.text = post.naimenovanie
                    diametTrub.text = post.diametrTrub
                    vnDimetrTrub.text = post.vnDimetrTrub
                    thick.text = post.thick
                    ieu.text = post.ieu
                    rastiagUsilie.text = post.rastiagUsilie
                    krutMoment.text = post.krutMoment
                    vnDavlenie.text = post.vnDavlenie
                    tipZamka.text = post.tipZamka
                    narDiametrZamka.text = post.narDiametrZamka
                    vnDiametrZamka.text = post.vnDiametrZamka
                    pin.text = post.pin
                    rastiagUsilieZamka.text = post.rastiagUsilieZamka
                    krutMomentZamka.text = post.krutMoment
                    g105.text = post.g105
                    priznak.text = post.priznak
                    rastiagUsilie1klass.text = post.rastiagUsilie_1klass
                    rastiagUsilie2klass.text = post.rastiagUsilie_2klass
                    krutMoment1klass.text = post.krutMoment_1klass
                    krutMoment2klass.text = post.krutMoment_2klass
                    momentNew.text = post.momentNew
                    moment1klass.text = post.moment_1klass
                    moment2klass.text = post.moment_2klass
                    sigmaT.text = post.sigma_t
                    sigmaV.text = post.sigma_v
                    otnosRast.text = post.otnos_rast
                  //  sertificat.text = post.sertificat
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
//            val redirect = arguments?.textArg
//
//            when (arguments?.textArg) {
//                redirect -> findNavController().navigate(
//                    ru.netology.nmedia1022.R.id.action_btCard_to_btObFragment,
//                    Bundle().apply {
//                        textArg = arguments?.textArg
//                    })
//            }


          //  getActivity()?.onBackPressed();

            requireActivity().onBackPressedDispatcher.onBackPressed()






//            val ids = binding.diametTrub.text.toString()
//            bundle.putString("60,3", ids)
//            findNavController().navigate(ru.netology.nmedia1022.R.id.action_btCard_to_btObFragment, Bundle().apply {
//                textArg = ids
//            })



        }

        binding.buttonSertificat.setOnClickListener {

    val id = it.id
    bundle.putString("id", id.toString())
    findNavController().navigate(ru.netology.nmedia1022.R.id.action_btCard_to_btTechical2, Bundle().apply {
        longArg = arguments?.longArg!!
    })



        }


//        val id = post.id
//        bundle.putLong("id", id)
//        findNavController().navigate(ru.netology.nmedia1022.R.id.action_btObFragment_to_btCard, Bundle().apply {
//            longArg = post.id
//            textArg = arguments?.textArg




        //   Post {}
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
