package ru.netology.nmedia1022.fragment.bt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.databinding.BtCardBinding
import ru.netology.nmedia1022.databinding.BtTechnicalBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.utils.CompanionArg.Companion.longArg
import ru.netology.nmedia1022.utils.CompanionArg.Companion.textArg
import ru.netology.nmedia1022.viewmodel.PostViewModel
import kotlin.math.floor
import kotlin.math.roundToInt


class BtTechical : Fragment(ru.netology.nmedia1022.R.layout.bt_technical) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = BtTechnicalBinding.inflate(layoutInflater)

        val _binding = BtCardBinding.inflate(inflater, container, false)
        //val binding = BtCardBinding.inflate(layoutInflater)
        val viewModel: PostViewModel by viewModels(::requireParentFragment)
        val bundle = Bundle()


        with(binding) {
            viewModel.data.observe(viewLifecycleOwner) { state ->
                val post = state.posts.find { it.id == arguments?.longArg }
//Определяет допустимую толщину стенки тела для 2 класса
                val thickness = when (post?.thick?.toDouble()) {
                    7.11 -> 6.3
                    8.0 -> 7.0
                    8.38 -> 7.3
                    8.56 -> 7.5
                    9.19 -> 8.1
                    9.35 -> 8.2
                    10.54 -> 9.2
                    10.9 -> 9.5
                    11.4 -> 10.0
                    12.7 -> 11.1
                    else -> (post?.thick?.toDouble()?.minus(
                        (post?.thick?.toDouble()?.times(0.125)!! * 100).roundToInt() / 100.0
                    ))
                }
                //Определяет допустимый диаметр тела
                val teloGeometry = when (post?.diametrTrub) {
                    "60.3" -> 58.5
                    "73" -> 70.8
                    "Т73" -> 71.3
                    "89" -> 86.2
                    "102" -> 98.6
                    "114" -> 110.9
                    "127" -> 123.2
                    "140" -> 135.5
                    else -> "См. РЭ"
                }
                //Определяет диаметр d6
                val d6 = when (post?.narDiametrZamka) {
                    "77" -> 76.0
                    "85.7", "86" -> 83.0
                    "92" -> 86.7
                    "105.5" -> 99.4
                    "95" -> 93.2
                    "104.8","105"  -> 100.5
                    "102.6" -> 123.2
                    "127" -> 116
                    "133.4", "139.7" -> 127.5
                    "158.8", "159" -> 145.3
                    "161.9", "162", "165.1", "168",  "168.3" -> 154.0
                    "177", "184", "177.8", "184.1"  -> 170.5
                    else -> "См. РЭ"
                }
//Определяет 2 класс замка
                val classZamokTwo = when (post?.narDiametrZamka){
                    "77" -> 75.6
                    "85.7" -> 83.4
                    "86" -> 83.4
                    "92" -> 89.2
                    "105.5", "105", "104.8" -> 101.9
                    "95" -> 92.8
                    "111" -> 107.7
                    "127" -> 123.2
                    "139.7" -> 135.5
                    "158.8", "159" -> 145.3
                    "162", "161.9" -> 157.1
                    "168", "168.3" -> 163.0
                    "177", "177.8" -> 172.5
                    "184", "184.1" -> 178.5
                    else -> "См ТП НБС"
                }
//Определяет 3 класс замка
                val classZamokThre = when (post?.narDiametrZamka){
                    "77", "95" -> "---"
                    "85.7", "86" -> 82.6
                    "92" -> 88.3
                    "105.5", "105", "104.8" -> 100.8
                    "111" -> 105.5
                    "127" -> 121.9
                    "139.7" -> 134.1
                    "158.8", "159" -> 152.6
                    "161.9", "162" -> 155.5
                    "168", "168.3" -> 161.3
                    "177", "177.8" -> 170.7
                    "184", "184.1" -> 176.6
                    else -> "См ТП НБС"
                }


                val pinD4 = when (post?.pin){
                "З-65" ->	66.674
                "З-66" ->	68.261
                "З-73" ->	74.611
                "З-76" ->	77.786
                "З-86" ->	87.708
                "З-88" ->	90.486
                "З-94" ->	96.836
                "З-101" ->	102.788
                "З-102" ->	103.582
                "З-108" ->	110.329
                "З-117", "З-118" ->	119.060
                "З-121" ->	123.822
                "З-122" ->	124.616
                "З-133" ->	134.935
                "З-140" ->	141.681
                "З-147" ->	150.016
                "З-149" ->	150.809
                "З-152" ->	153.984
                "З-161" ->	163.934
                "З-163" ->	165.097
                "З-171" ->	173.828
                "З-177" ->	180.177
                "З-185" ->	187.321
                "З-189" ->	191.764
                "З-201", "З-203" ->	204.386
                    else -> "См ГОСТ"
                }

                val pinLm = when (post?.pin){
                    "З-65","З-66", "З-73" ->	92
                    "З-76", "З-86" ->	105
                    "З-102", "З-121" ->	118
                    "З-108", "З-133" ->	130
                    "З-152", "З-161", "З-147", "З-171" ->	143
                    "З-163" ->	156
                    "З-177" ->	149
                    else -> "См ГОСТ"
                }
                val pinLn = when (post?.pin){
                    "З-65","З-66", "З-73" ->	76
                    "З-76", "З-86" ->	89
                    "З-102", "З-121" ->	102
                    "З-108", "З-133" ->	114
                    "З-152", "З-161", "З-147", "З-171" ->	127
                    "З-163" ->	140
                    "З-177" ->	133
                    else -> "См ГОСТ"
                }

                val zamokLn = when (post?.narDiametrZamka){
                    "77",  "85.7", "86", "92"-> 140
                    "95" -> 80
                    "105.5", "105", "104.8", "108", "121", "127",
                    "133", "139.7", "140", "152", "158.8", "159", "161.9", "162",
                    "165", "168", "168.3"-> 130
                    "177", "177.8", "178", "184", "184.1" -> 140
                    else -> "См ТП НБС"
                }
                val zamokLm = when (post?.narDiametrZamka){
                    "77",  "85.7", "86", "92"-> 160
                    "95" -> 125
                    "105.5", "105", "104.8", "108" -> 190
                    "121", "127", "133", "139.7", "140", "152", "158.8", "159", "161.9", "162",
                    "165", "168", "168.3", "177", "177.8", "178", "184", "184.1"-> 200
                    else -> "См ТП НБС"
                }
                // Определяет натяг взависимости от типоразмера
                val replaseZ = (post?.pin as String).replace("З-", "").toInt()
                val natiagNippel = when (replaseZ) {
                    in 0..129 -> "14.445 - 15.875"
                    in 133..200 -> "13.725 - 15.875"
                    else -> {
                        "См ТП НБС"
                    }
                }

                if (post != null) {
                    sertificat.text = post.sertificat
                    naimenovanie.text = post.naimenovanie
                    pinPipes.text = post.pin
                    tolshinaOneClass.text = post.thick
                    tolshinaTwoClass.text = thickness.toString()
                    teloDiametrOneKlass.text = post.diametrTrub
                    teloDiametrTwoKlass.text = teloGeometry.toString()
                    d6m.text = d6.toString()
                    d6n.text = d6.toString()
                    //sertificat.text = (pinPipes.text as String).replace("З-", "")
                    natiagN.text = natiagNippel.toString()
                    diametrZamokOneClass.text = post.narDiametrZamka
                    diametrZamokTwoClass.text = classZamokTwo.toString()
                    diametrZamokThreeClass.text = classZamokThre.toString()
                    vitochkaD4.text = pinD4.toString()
                    dlinaPinMufta.text = pinLm.toString()
                    dlinaPinNippel.text = pinLn.toString()
                    dlinaZamkaM.text = zamokLm.toString()
                    dlinaZamkaN.text = zamokLn.toString()
                }
            }
        }


//        with(_binding) {
//            viewModel.data.observe(viewLifecycleOwner) { state1 ->
//                val post = state1.posts.find { it.id == arguments?.longArg }
////val post = state.posts.sortedBy { it.id }
//
//                if (post != null) {
//                    pin.text = post.pin
//
//                }
//            }
//        }
        viewModel.edited.observe(viewLifecycleOwner) {
            if (it.id == 0L) {
                return@observe
            }
        }


        binding.imgBack.setOnClickListener {
//            val id = arguments?.longArg
//            when (arguments?.longArg) {
//                id -> findNavController().navigate(R.id.action_btTechnical_to_btCard,
//                    Bundle().apply {
//                        longArg = arguments?.longArg!!
//                    })
//            }
            requireActivity().onBackPressedDispatcher.onBackPressed()

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


