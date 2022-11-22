package ru.netology.nmedia1022

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.activity_edit_post.*
import ru.netology.nmedia1022.databinding.ActivityEditPostBinding
import ru.netology.nmedia1022.databinding.ActivityNewPostBinding
import ru.netology.nmedia1022.viewmodel.PostViewModel


class EditPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()




        //не понимаю как это работает
        binding.edit.setText(intent.getStringExtra(Intent.EXTRA_TEXT))




//        viewModel.editedTextall.observe(this@EditPostActivity) {
//
//            if (it.id == 0L) {
//                edit.setText(it.content)
//                return@observe
//            }
//            edit.setText(it.content)
//        }

 //edit.setText(it.content)
//       //binding.edit.setText(Post:content)
//
//        binding.edit.requestFocus()
//
        ok.setOnClickListener {
        val content = edit.text?.toString()

            if (content.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED)
            } else {

                val intent = Intent()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}
