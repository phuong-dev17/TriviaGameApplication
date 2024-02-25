package com.quizgame.triviagameapplication.common

import android.os.Parcelable
import com.quizgame.triviagameapplication.repository.AnswerEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionData(
    val questionId: String,
    val question: String,
    val allAnswer: List<String>,
    val correctAnswer: String,
    val currentAnswer: String?
) :Parcelable