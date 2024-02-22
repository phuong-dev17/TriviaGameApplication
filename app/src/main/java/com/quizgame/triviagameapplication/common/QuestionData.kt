package com.quizgame.triviagameapplication.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionData(
    val category: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
) :Parcelable
