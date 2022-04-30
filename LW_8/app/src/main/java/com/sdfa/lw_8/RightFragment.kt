package com.sdfa.lw_8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.zip.Inflater

class RightFragment (Operation: Int = 0) : Fragment() {

    private val operation = Operation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inf = inflater.inflate(R.layout.fragment_quiz_sum, container, false)

        refreshTask(1, 1)

        return inf

//        return inflater.inflate(
//            when (operation) {
//                0 -> R.layout.fragment_quiz_sum
//                1 -> R.layout.fragment_quiz_sub
//                else -> R.layout.fragment_quiz_mul
//            },
//            container, false
//        )
    }
    fun refreshTask (Right: Int, Wrong: Int) {
        val task = Task()
        var right = Right
        var wrong = Wrong

        task.generate()

        val taskTextView = view?.findViewById<TextView>(R.id.task)
        val taskText = task.A.toString() + task.Operation + task.B.toString()

        taskTextView?.text = taskText

        val answersList = view?.findViewById<ListView>(R.id.answers)

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

            view?.findViewById<TextView>(R.id.right)?.text = right.toString()
            view?.findViewById<TextView>(R.id.wrong)?.text = wrong.toString()

            refreshTask(right, wrong)
        }
    }
}