package com.sdfa.lw_9

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.net.URL


class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        Log.i("info", "activity init step 2 OK")

        val intent = intent
        val repoName = intent?.getStringExtra("repoName")
        val avatarURL = intent?.getStringExtra("avatarURL")
        val description = intent?.getStringExtra("description")
        val url = intent?.getStringExtra("url")
        Log.i("info", "intent init step 2 OK")

        val repoName_view = findViewById<TextView>(R.id.repo_name)
        repoName_view.text = repoName
        Log.i("info", "set name OK")

        GlobalScope.launch {
            val avatar = findViewById<ImageView>(R.id.web_image)
            val buf = URL(avatarURL).readBytes()
            val image_object = BitmapFactory.decodeByteArray(buf, 0, buf.size)

            MainScope().launch {
                avatar.setImageBitmap(image_object)
            }
        }
        Log.i("info", "set image OK")

        val description_view = findViewById<TextView>(R.id.description)
        description_view.text = description
        Log.i("info", "set description OK")

    }
}