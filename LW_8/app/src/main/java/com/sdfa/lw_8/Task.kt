package com.sdfa.lw_8

import kotlin.random.Random

class Task () {

    var A: Int = 0
    var B: Int = 0
    var Operation: String = ""
    var Answer: Int = 0
    var AnswerPosition = 0
    var AnswerList = mutableListOf<Int>()

    fun generate() {
        val operations: List<String> = listOf(" + ", " - ", " * ")

        A = Random.nextInt(-9, 9)
        B = Random.nextInt(-9, 9)
        Operation = operations[Random.nextInt(0, 3)]
        AnswerPosition = Random.nextInt(0, 4)

        if (Operation == " + ") { Answer = A + B }
        if (Operation == " - ") { Answer = A - B }
        if (Operation == " * ") { Answer = A * B }

        var i: Int = 0
        while (i <= 3) {
            AnswerList.add(Answer + Random.nextInt(-3, 3))
            i ++
        }

        for(number in AnswerList) {
            if (number == Answer){ AnswerList[AnswerList.indexOf(number)] = Answer + 1 }
        }

        AnswerList[AnswerPosition] = Answer


    }
}