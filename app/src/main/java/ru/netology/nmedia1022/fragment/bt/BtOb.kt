package ru.netology.nmedia1022.fragment.bt


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.adapter.*
import ru.netology.nmedia1022.databinding.BtObBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.utils.CompanionArg.Companion.longArg
import ru.netology.nmedia1022.utils.CompanionArg.Companion.textArg
import ru.netology.nmedia1022.viewmodel.PostViewModel


class BtOb : Fragment(R.layout.bt_ob) {

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

                    //Выбираем карточку взависимости от признака в БД
                    val chooseCard = when (post.priznak) {
                        "BT" -> ru.netology.nmedia1022.R.id.action_btObFragment_to_btCard
                        "UBT", "NUBT" -> R.id.action_btObFragment_to_ubtCard
                        else -> R.id.action_btObFragment_to_btCard
                    }

                    findNavController().navigate(chooseCard, Bundle().apply {

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

        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        //Проверяем интернет и вывод ТОАСТину
        if (NetworkManager.isNetworkAvailable(requireContext()) == false) Toast.makeText(context,
            "Отсутствует связь с интернетом", Toast.LENGTH_LONG).show()

//Свайп для обновления данных
        val mSwipeRefreshLayout = binding.swipeRefreshLayout as SwipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
            mSwipeRefreshLayout.isRefreshing = true
            viewModel.loadPosts()
            mSwipeRefreshLayout.postDelayed({
                //выводим ТОАСТ при свайпе об отсутствии интернета
                if (NetworkManager.isNetworkAvailable(requireContext()) == false) Toast.makeText(context,
                    "Отсутствует связь с интернетом", Toast.LENGTH_LONG).show()

                mSwipeRefreshLayout.isRefreshing = false
            }, 1000)
        }







//        adapter.items = listOf<Clients> из своей активити.
//        Вам просто нужно будет перед поставлением данных сортировать
//                этот список items.sortBy { it.time }

//вывод списка с трубами + фильтр по аргументу
        viewModel.data.observe(viewLifecycleOwner) { state ->
            if (isLetters(arguments?.textArg.toString())) {

//             adapter.submitList(state.posts.sortedWith(compareBy({it.priznak}, {it.diametrTrub} )))


                //  adapter.submitList(state.posts.filter { it.priznak == arguments?.textArg })}
//23.03.24
//                adapter.submitList(state.posts.filter{ it.priznak == arguments?.textArg})
                adapter.submitList(state.posts.filter { it.priznak == arguments?.textArg })


            }
//            adapter.submitList(state.posts.filter { it.priznak == arguments?.textArg })}

            else if (isNumeric(arguments?.textArg.toString())) {
                adapter.submitList(state.posts.filter { it.diametrTrub == arguments?.textArg })
            } else if (arguments?.textArg == "60,3") {
                adapter.submitList(state.posts.filter { it.diametrTrub == arguments?.textArg })
            }


        }

        binding.spisok.adapter = adapter


//назначение тайтла в зависимости от списка
        when (arguments?.textArg) {
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

object NetworkManager {
    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected) {
            true
        } else {
            false
        }
    }
}