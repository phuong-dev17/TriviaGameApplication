package com.quizgame.triviagameapplication.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionData(
    val question: String,
    val allAnswer: List<String>,
    val correctAnswer: String,
    val currentAnswer: String?
) :Parcelable
