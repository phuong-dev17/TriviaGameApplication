package com.quizgame.triviagameapplication.common

import com.quizgame.triviagameapplication.repository.AnswerEntity

data class AnswerData(
    val questionId: String,
    val question: String,
    val userAnswer: String,
    val isCorrect: Boolean
) {
    constructor(inputEntity: AnswerEntity): this(
        questionId = inputEntity.id,
        question = inputEntity.question,
        userAnswer = inputEntity.userAnswer,
        isCorrect = inputEntity.isCorrect
    )
}
