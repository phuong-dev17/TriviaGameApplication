package com.quizgame.triviagameapplication.repository

import com.quizgame.triviagameapplication.common.AnswerData

interface AnswerDBInterface {
    suspend fun add(inputAnswer : AnswerData)

    suspend fun getAll(): List<AnswerData>


}