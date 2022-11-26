package ru.netology.nmedia1022.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.new_post.*
import ru.netology.nmedia1022.R
import ru.netology.nmedia1022.databinding.NewPostBinding
import ru.netology.nmedia1022.utils.AndroidUtils
import ru.netology.nmedia1022.utils.StringArg
import ru.netology.nmedia1022.viewmodel.PostViewModel


class NewPostFragment : Fragment(R.layout.new_post) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = NewPostBinding.bind(view)
        ///val viewModel: PostViewModel by viewModels()

//предоставляем одну viewModel для нескольких фрагментов
        val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )

        val content = arguments?.getString("content")
        binding.edit.setText(content)
        binding.edit.requestFocus()


        binding.ok.setOnClickListener {

            viewModel.changeContent(binding.edit.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(binding.root)


            findNavController().navigate(R.id.feedFragment)
        }


    }

    companion object {
        private const val TEXT_KEY = "text"

        var Bundle.textArg: String?
            set(value) = putString(TEXT_KEY, value)
            get() = getString(TEXT_KEY)
    }

}

//outlinedTextField.editText?.text.toString()


//class NewPostActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityNewPostBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.ok.setOnClickListener{
//            val intent = Intent()
//            val text = binding.edit.text
//            if (text.isNullOrBlank()) {
//                setResult(Activity.RESULT_CANCELED,intent)
//            }else {
//                val content = text.toString()
//                intent.putExtra(Intent.EXTRA_TEXT,content)
//                setResult(Activity.RESULT_OK, intent)
//            }
//            finish()
//        }
//
//    }
//}