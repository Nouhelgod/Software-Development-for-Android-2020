package com.sdfa.lab03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val tag: String = "MyInfo"
    private var numbersList = arrayListOf<String>()

    fun addTextView(text: String) {
        val textView = TextView(this)
        textView.text = text
        textView.textSize = 24f
        val container = findViewById<LinearLayout>(R.id.innerContainer)
        container.addView(textView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            val numbers = savedInstanceState.getStringArrayList("numbersList")
            if (numbers != null) {
                for(number in numbers) {
                    numbersList.add(number)
                    addTextView(number)
                }
            }

            val container = findViewById<LinearLayout>(R.id.innerContainer)
            container.scrollY = savedInstanceState.getInt("scrollY")
            Log.i(tag, "scrollY set -->" + container.scrollY.toString())
        }

        Log.i(tag, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.i(tag, "onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.i(tag, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(tag, "onDestroy")
    }

    fun buttonAddClick(view: View) {
        val text = Random.nextInt(0, 999).toString()
        numbersList.add(text)
        addTextView(text)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("numbersList", numbersList)
        Log.i(tag, "Numbers saved! -->" + numbersList.toString())

        val container = findViewById<LinearLayout>(R.id.innerContainer)
        outState.putInt("scrollY", container.scrollY)
        Log.i(tag, "scrollY saved --> " + container.scrollY.toString())
    }
}