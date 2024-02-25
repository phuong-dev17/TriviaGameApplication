package com.quizgame.triviagameapplication.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.quizgame.triviagameapplication.common.AnswerData
import com.quizgame.triviagameapplication.common.QuestionData
import java.util.UUID

@Entity(tableName = "answer")
data class AnswerEntity (
    @PrimaryKey val id: String,
    @ColumnInfo("question") var question: String,
    @ColumnInfo("user_answer") var userAnswer: String,
    @ColumnInfo("is_correct") var isCorrect: Boolean
) {
    constructor(inputAnswer: AnswerData) : this(
        id = inputAnswer.questionId,
        question = inputAnswer.question,
        userAnswer = inputAnswer.userAnswer,
        isCorrect = inputAnswer.isCorrect
    )
}