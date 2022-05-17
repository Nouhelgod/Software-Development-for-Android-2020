package com.sdfa.lw_8

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.zip.Inflater

class RightFragment (Operation: Int = 0, Right: Int = 0, Wrong: Int = 0) : Fragment() {
    private var r = Right
    private var w = Wrong
    private val operation = Operation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inf = inflater.inflate(R.layout.fragment_quiz, container, false)

        refreshTask(inf, operation, r, w)

        return inf


    }
    fun refreshTask (v: View, op: Int, Right: Int, Wrong: Int) {
        lateinit var mainContext: Context
        val task = Task(op)
        var right = Right
        var wrong = Wrong

        task.generate()

        val taskTextView = v.findViewById<TextView>(R.id.task)
        val taskText = task.A.toString() + task.Operation + task.B.toString()

        taskTextView?.text = taskText

        val answersList = v.findViewById<ListView>(R.id.answers)

        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, task.AnswerList
        )

        answersList?.adapter = adapter
        answersList?.setOnItemClickListener { parent, view, position, id ->

            if (position == task.AnswerPosition) {
                right++
            } else {
                wrong++
            }
            (mainContext as onResultListener).onResult(op, right, wrong)

            v.findViewById<TextView>(R.id.right)?.text = right.toString()
            v.findViewById<TextView>(R.id.wrong)?.text = wrong.toString()

            refreshTask(v, op, right, wrong)
        }
    }

    interface onResultListener {
        fun onResult (op: Int, right: Int, wrong: Int)
    }
}