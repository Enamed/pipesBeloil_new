package ru.netology.nmedia1022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia1022.adapter.PostActionListener
import ru.netology.nmedia1022.adapter.PostsAdapter
import ru.netology.nmedia1022.databinding.ActivityMainBinding
import ru.netology.nmedia1022.dto.Post
import ru.netology.nmedia1022.utils.AndroidUtils
import ru.netology.nmedia1022.viewmodel.PostViewModel

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

//        val adapte1r = PostsAdapter(
//            onLikeListener = {
//                viewModel.likeById(it.id)
//            },
//            shareListener = {
//                viewModel.share(it.id)
//            },
//            onRemoveListener = {
//                viewModel.removeById(it.id)
//            }
//        )


        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        with(binding) {
            save.setOnClickListener {
                val text = content.text?.toString()
                if (text.isNullOrBlank()) {
                    Toast.makeText(this@MainActivity, getString(R.string.blank_content_error), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
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
                content.requestFocus()
                content.setText(it.content)
            }
        }

    }
}


//binding.save.setOnClickListener {
//    with(binding.content){
//        if (text.isNullOrBlank()){
//            Toast.makeText(
//                this@MainActivity,
//                "Content can`t be empty",
//                Toast.LENGTH_SHORT
//            ).show()
//            return@setOnClickListener
//        }
//        viewModel.changeContent(text.toString())
//        viewModel.save()
//
//        setText("")
//        clearFocus()
//        AndroidUtils.hideKeyboard(this)
//    }
//}

//
//        viewModel.data.observe(this) { posts ->
//            posts.map { post ->
//                CardPostBinding.inflate(layoutInflater, binding.container, true).apply {
//                    author.text = post.author
//                    published.text = post.published
//                    content.text = post.content
//                    like.setImageResource(
//                        if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_baseline_favorite_border_24
//                    )
//                    like.setOnClickListener {
//                        viewModel.likeById(post.id)
//                    }
//
//                }.root
//            }
//        }
//
//
//    }
//}


//                    countShare.text = Utils.numbers(post.countShare)
//                    countLike.text = Utils.numbers(post.countLike)
//                    countVisio.text = Utils.numbers(post.countVisio)


//val post = Post(
//     id = 1,
//    author = "Нетология - университет интернет профессий",
//    content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появи",
//    published = "16 october 2022 in 11:27",
//    likedByMe = false,
//    countLike = 999,
//    countShare = 12,
//    countVisio = 1_000
//)

//


//like?.setOnClickListener{
//    post.likedByMe = !post.likedByMe
//    like.setImageResource(
//        if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_baseline_favorite_border_24
//    )
//    if (post.likedByMe){
//        ++post.countLike
//        countLike.text = Utils.numbers(post.countLike)
//    }else {
//        --post.countLike
//        countLike.text = Utils.numbers(post.countLike)
//    }
//    share?.setOnClickListener {
//        ++post.countShare
//        post.countShare = post.countShare
//        countShare.text = Utils.numbers(post.countShare)
//
//    }
//
//}