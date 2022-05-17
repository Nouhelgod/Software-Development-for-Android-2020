package com.sdfa.lw_9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    fun parseRepos(view: View) {

        GlobalScope.launch {
            val keyword = findViewById<EditText>(R.id.name_field).text.toString()
            val t = URL("https://api.github.com/search/repositories?q=" + keyword).readText()
            val json = JSONObject(t)
            val count = json.getInt("total_count")
            Log.i("info", "json init OK")

            val array = json.getJSONArray("items")
            val names: MutableList<String> = mutableListOf()
            for (i in 0 until array.length()) {
                val o = array.getJSONObject(i)
                val s = o.getString("name")
                names.add(s)
            }
            Log.i("info", "array init OK")

            MainScope().launch {
                val adapter = ArrayAdapter<String>(
                    view.context,
                    android.R.layout.simple_list_item_1,
                    names
                )
                Log.i("info", "adapter init OK")

                val listView: ListView = findViewById(R.id.result_list)
                listView.adapter = adapter
                Log.i("info", "list view init OK")

                listView.setOnItemClickListener{parent, view, position, id ->
                    val intent = Intent(view.context, InfoActivity::class.java)

                    intent.putExtra("repoName", names[position])
                    intent.putExtra("avatarURL", array.getJSONObject(position).getJSONObject("owner").getString("avatar_url"))
                    intent.putExtra("description", array.getJSONObject(position).getString("description"))
                    intent.putExtra("url", array.getJSONObject(position).getString("url"))
                    Log.i("info", "intent init step 1 OK")

                    startActivity(intent)
                    Log.i("info", "activity init step 1 OK")
                }
            }
        }
    }
}