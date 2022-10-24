package ru.netology.nmedia1022

import android.os.Bundle
import android.view.KeyEvent.ACTION_DOWN
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat.getActionIndex
import ru.netology.nmedia1022.adapter.PostActionListener
import ru.netology.nmedia1022.adapter.PostsAdapter
import ru.netology.nmedia1022.databinding.ActivityMainBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.utils.AndroidUtils
import ru.netology.nmedia1022.viewmodel.PostViewModel

//abstract fun onTouch(v: View!, event: MotionEvent!): Boolean

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            object : PostActionListener{
                override fun edit(post: Post) {
                    viewModel.edit(post)
                }

                override fun like(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun delete(post: Post) {
                    viewModel.removeById(post.id)
                }
                override fun share(post: Post) {
                    viewModel.share(post.id)
                }

            }
        )


        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        with(binding) {
            close.setOnClickListener {
             //   close.setVisibility(View.INVISIBLE)
                AndroidUtils.hideKeyboard(content)
                content.setText("")
                binding.close.visibility = View.GONE
            }

//content.setOnTouchListener(){
//   // binding.close.visibility = View.VISIBLE
//
//}
content.setOnClickListener(){
    binding.close.visibility = View.VISIBLE
}


            save.setOnClickListener {
                val text = content.text?.toString()
                //val even = onTouchEvent()

                if (text.isNullOrBlank()) {

                    Toast.makeText(this@MainActivity, getString(R.string.blank_content_error), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
           //     val pointerIndex: Int = getActionIndex()
//                if (content.onTouchEvent(this)) {
//                    binding.close.visibility = View.VISIBLE
//                }

                viewModel.editContent(text)

                viewModel.save()

                content.setText("")
                content.clearFocus()
                AndroidUtils.hideKeyboard(content)
            }
            viewModel.edited.observe(this@MainActivity){

                if (it.id == 0L) {

                    return@observe
                }
                binding.close.visibility = View.VISIBLE



                content.requestFocus()
                content.setText(it.content)
            }
          //binding.close.visibility = View.INVISIBLE
        }

    }
}
