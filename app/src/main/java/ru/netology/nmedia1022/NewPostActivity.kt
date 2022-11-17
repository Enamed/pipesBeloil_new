package ru.netology.nmedia1022

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia1022.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ok.setOnClickListener{
            val intent = Intent()
val text = binding.edit.text
            if (text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED,intent)
            }else {
                val content = text.toString()
                intent.putExtra(Intent.EXTRA_TEXT,content)
                setResult(Activity.RESULT_OK, intent)
            }
        finish()
        }

    }
}