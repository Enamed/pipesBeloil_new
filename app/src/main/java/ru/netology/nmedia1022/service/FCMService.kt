package ru.netology.nmedia1022.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import ru.netology.nmedia1022.R
import kotlin.random.Random

// FCM - Уведомление через FireBase о наличии новых лайков или постов
class FCMService : FirebaseMessagingService() {
    private val action = "action"
    private val content = "content"
    private val channelId = "remote"
    private val gson = Gson()

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_remote_name)
            val descriptionText = getString(R.string.channel_remote_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {

        message.data[action]?.let {
            when (Action.valueOf(it)) {
                Action.LIKE -> handleLike(
                    gson.fromJson(message.data[content],
                        Like::class.java))
                Action.NEW_POST -> handleNewPost(
                    gson.fromJson(message.data[content],
                    NewPost::class.java
                )
                )
            }
        }
    }

    override fun onNewToken(token: String) {
        println(token)
    }

    @SuppressLint("MissingPermission")
    private fun handleLike(content: Like) {
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.logo_foreground)
            .setContentTitle(
                getString(
                    R.string.notification_user_liked,
                    content.userName,
                    content.postAuthor,
                )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)
    }


@SuppressLint("MissingPermission")
private fun handleNewPost(newPost: NewPost) {
    val notification = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.drawable.logo_foreground)
        .setContentTitle(
            getString(
                R.string.notification_user_newpost,
                newPost.userName,
                newPost.textPost
            )
        )
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()

    NotificationManagerCompat.from(this)
        .notify(Random.nextInt(100_000), notification)
}
}
enum class Action {
    LIKE, NEW_POST
}

data class Like(
    val userId: Long,
    val userName: String,
    val postId: Long,
    val postAuthor: String,
)

data class NewPost(
    val userName: String,
    val textPost: String,

)




//eACpv6SlT_Gm6FCQoACe-t:APA91bEv52d_T3A-HSxX6PE87JqQrK8643FX4h6s9wnjaL4gH5A3ObXl_C6mJPMQGsPS9I7N2rg8X1L8rJ3AQ8gXM4ejBCfmW6U9LE6xVaHkSmDFgJTWcujUY4jaf67GVvE9Qebd7oQa