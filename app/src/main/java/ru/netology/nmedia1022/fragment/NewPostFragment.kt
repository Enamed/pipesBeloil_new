package ru.netology.nmedia1022.fragment


import android.os.Bundle

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia1022.R

import ru.netology.nmedia1022.databinding.NewPostBinding
import ru.netology.nmedia1022.utils.AndroidUtils
import ru.netology.nmedia1022.viewmodel.PostViewModel


class NewPostFragment : Fragment(R.layout.new_post) {
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = NewPostBinding.bind(view)

arguments?.let { arguments ->
    val text = arguments.textArg ?: return@let
    binding.edit.setText(text)
}

        binding.ok.setOnClickListener {
           viewModel.changeContent(
               binding.edit.text.toString()
           )
            viewModel.save()
            AndroidUtils.hideKeyboard(it)
            findNavController().navigateUp()
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