package ru.netology.nmedia1022


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

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
                    //создание интента для шаринга
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                   val shareIntent = Intent.createChooser(intent, getString(R.string.share))
                    startActivity(intent)
                }

                override fun onVidioClicked(post: Post) {
                    val youtubeInternet = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    startActivity(youtubeInternet)
                }

            }
        )


        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

//        добавление новости через кнопку справа внизу

        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }



        with(binding) {

            fab.setOnClickListener{
    newPostLauncher.launch()
            }


//            fab1.setOnClickListener{
//                editPostLauncher.launch()
//            }
//

//            close.setOnClickListener {
//             //   close.setVisibility(View.INVISIBLE)
//                AndroidUtils.hideKeyboard(content)
//                content.setText("")
//                binding.close.visibility = View.GONE
//            }

//            content.setOnClickListener(){
//                binding.close.visibility = View.VISIBLE
//
//
//
//            }

//            content.setOnClickListener(View.OnClickListener {
//                Toast.makeText(this@MainActivity, getString(R.string.blank_content_error), Toast.LENGTH_SHORT).show()
//            })
//
//            save.setOnClickListener {
//                val text = content.text?.toString()
//                       binding.close.visibility = View.VISIBLE
//
//                if (text.isNullOrBlank()) {
//                    Toast.makeText(this@MainActivity, getString(R.string.blank_content_error), Toast.LENGTH_SHORT).show()
//                    return@setOnClickListener
//                }
//
//                viewModel.editContent(text)
//
//                viewModel.save()
//
//                content.setText("")
//                content.clearFocus()
//                AndroidUtils.hideKeyboard(content)
//            }




            val editPostLauncher = registerForActivityResult(EditPostResultContract()) { text ->
                text ?: return@registerForActivityResult
                viewModel.changeContent(text)
                viewModel.save()
            }
            viewModel.edited.observe(this@MainActivity){

                if (it.id == 0L) {
                    return@observe

                }

                editPostLauncher.launch(it.content)
//                binding.close.visibility = View.VISIBLE
//          content.requestFocus()
             // edit.setText(it.content)
            }
          //binding.close.visibility = View.INVISIBLE
        }

    }
}



