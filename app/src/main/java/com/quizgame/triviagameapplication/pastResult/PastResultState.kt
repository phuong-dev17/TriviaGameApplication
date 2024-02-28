package com.quizgame.triviagameapplication.pastResult

import com.quizgame.triviagameapplication.common.AnswerData
import java.util.Objects

sealed class PastResultState() {
    object Loading: PastResultState()
    object Failure: PastResultState()
    data class Loaded(val answerData: List<AnswerData>) : PastResultState()
}


