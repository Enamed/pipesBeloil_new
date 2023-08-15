package ru.netology.nmedia1022.fragment.bt


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.adapter.*
import ru.netology.nmedia1022.databinding.BtObBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.utils.CompanionArg.Companion.longArg
import ru.netology.nmedia1022.utils.CompanionArg.Companion.textArg
import ru.netology.nmedia1022.viewmodel.PostViewModel


class BtOb: Fragment(R.layout.bt_ob) {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BtObBinding.inflate(inflater, container, false)

        val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )


    val bundle = Bundle()


        val adapter = ListAdapter(
            object : ListActionListener {

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
                    // передача аргумента (открытие по айди и + ключ категории труб  priznak)
                    val ids: String? = arguments?.textArg
                    bundle.putString(arguments?.textArg, ids)

                    val id = post.id
                    bundle.putLong("id", id)
                    findNavController().navigate(R.id.action_btObFragment_to_btCard, Bundle().apply {
                      longArg = post.id
                        textArg = arguments?.textArg

                    })
                }
            })
        //проверка на латинский алфавит
        fun isLetters(string: String): Boolean {
            return string.none { it !in 'A'..'Z' && it !in 'a'..'z' }
        }
        //проверка на цифры
        fun isNumeric(s: String): Boolean {
            return try {
                s.toDouble()
                true
            } catch (e: NumberFormatException) {
                false
            }
        }


//кнопка назад

  //      TODO("сделать возврат для двойки")
        binding.imgBack.setOnClickListener {
            if (arguments?.textArg == "BT") {
                  findNavController().navigate(
                R.id.action_btObFragment_to_btFragment2)
            }
            else if (isNumeric(arguments?.textArg.toString())) {
                findNavController().navigate(
                    R.id.action_btObFragment_to_btFragment2)
            }
            else if (arguments?.textArg == "60,3") {
                findNavController().navigate(
                    R.id.action_btObFragment_to_btFragment2)
            }
            else {
                findNavController().navigate(
                    R.id.action_btObFragment_to_mainFragment)
            }
        }



//вывод списка с трубами + фильтр по аргументу
        viewModel.data.observe(viewLifecycleOwner) { state ->
            if (isLetters(arguments?.textArg.toString())) {
           adapter.submitList(state.posts.filter { it.priznak == arguments?.textArg })}

            else if(isNumeric(arguments?.textArg.toString())){
            adapter.submitList(state.posts.filter { it.diametrTrub == arguments?.textArg })}

            else if(arguments?.textArg == "60,3"){
                adapter.submitList(state.posts.filter { it.diametrTrub == arguments?.textArg })}


                }

        binding.spisok.adapter = adapter


//назначение тайтла в зависимости от списка
        when(arguments?.textArg) {
            "BT" -> binding.txtTitle.setText("Список общеоборотных БТ")
            "UBT" -> binding.txtTitle.setText("Список УБТ")
            "NUBT" -> binding.txtTitle.setText("Список НУБТ")
            "TBT" -> binding.txtTitle.setText("Список ТБТ")
            "VBT" -> binding.txtTitle.setText("Список ВБТ")
            "NKTgl" -> binding.txtTitle.setText("Список НКТ гладкая")
            "NKTvis" -> binding.txtTitle.setText("Список НКТ высаженная")
            "NKTplast" -> binding.txtTitle.setText("Список НКТ пластиковая")
            "OTTM" -> binding.txtTitle.setText("Список ОТ - резьба ОТТМ")
            "BC" -> binding.txtTitle.setText("Список ОТ - резьба BC")
            "PREM" -> binding.txtTitle.setText("Список ОТ - резьба PREMIUM")
        }
        return binding.root
    }



}

