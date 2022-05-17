package com.sdfa.lw_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    fun refreshTask (Right: Int, Wrong: Int) {
        val task = Task()
        var right = Right
        var wrong = Wrong

        task.generate()

        val taskTextView = findViewById<TextView>(R.id.task)
        val taskText = task.A.toString() + task.Operation + task.B.toString()

        taskTextView.text = taskText

        val answersList = findViewById<ListView>(R.id.answers)

        val adapter = ArrayAdapter<Int>(
            this, android.R.layout.simple_list_item_1, task.AnswerList
        )


        answersList.adapter = adapter
        answersList.setOnItemClickListener {parent, view, position, id ->

            if (position == task.AnswerPosition) { right ++ }
            else { wrong ++ }

            findViewById<TextView>(R.id.right).text = right.toString()
            findViewById<TextView>(R.id.wrong).text = wrong.toString()

            refreshTask(right, wrong)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshTask(0, 0)
    }
}