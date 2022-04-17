package com.sdfa.lw_5

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.sdfa.lw_5.BuildConfig.DEBUG

class MainActivity : AppCompatActivity() {
    private lateinit var con: SQLiteDatabase;

    private fun getItems() {
        val cursor = con.query("items",
            arrayOf("id", "kind", "title", "price", "photo"),
            null, null, null, null, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val s = Item()
            s.id = cursor.getInt(0)
            s.kind = cursor.getString(1)
            s.title = cursor.getString(2)
            s.price = cursor.getDouble(3)
            s.photo = cursor.getString(4)
            items.add(s)
            cursor.moveToNext()
        }
        cursor.close()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = SQLiteHelper(this);
        con = db.writableDatabase
        getItems()

        val ListView: ListView = findViewById(R.id.listItems)
        ListView.adapter = ItemAdapter(this, items)
        ListView.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            val intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("index", i)
            intent.putExtra("item", items[i])
            Log.i("info", "going to $i")
            startActivityForResult(intent, 0)
        }

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, ItemActivity::class.java)
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
        {
            val index: Int = data?.getIntExtra("index", -1) ?: -1

            if (data?.getBooleanExtra("del", false) == true) {

                con.delete("items", "id=?", arrayOf(index.toString()))
                Log.i("info", "deleted $index")

            } else {

                val item: Item = data?.getParcelableExtra("item") ?: Item()

                val cv = ContentValues()
                cv.put("kind", item.kind)
                cv.put("title", item.title)
                cv.put("price", item.price)
                cv.put("photo", item.photo)
                if (index != -1) {
                    items[index] = item
                    cv.put("id", item.id)
                    con.update("items", cv, "id=?", arrayOf(item.id.toString()))
                } else {
                    con.insert("items", null, cv)
                }

                if (index != -1)
                    items[index] = item
                else
                    items.add(item)
            }

            val listView: ListView = findViewById(R.id.listItems)
            (listView.adapter as ItemAdapter).notifyDataSetChanged()
        }
    }


    private var items = ArrayList<Item>()
}
