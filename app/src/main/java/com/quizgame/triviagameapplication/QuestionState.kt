package com.quizgame.triviagameapplication

import com.quizgame.triviagameapplication.common.QuestionData

sealed class QuestionState() {
    object Loading: QuestionState()
    object Failure: QuestionState()
    data class Loaded( val question : List<QuestionData>) : QuestionState()
}
