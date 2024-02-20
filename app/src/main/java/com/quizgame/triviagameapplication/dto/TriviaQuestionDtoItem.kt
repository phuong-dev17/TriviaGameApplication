package com.quizgame.triviagameapplication.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TriviaQuestionDtoItem(
    @Json(name = "category")
    val category: String = "",
    @Json(name = "correctAnswer")
    val correctAnswer: String = "",
    @Json(name = "difficulty")
    val difficulty: String = "",
    @Json(name = "id")
    val id: String = "",
    @Json(name = "incorrectAnswers")
    val incorrectAnswers: List<String> = listOf(),
    @Json(name = "isNiche")
    val isNiche: Boolean = false,
    @Json(name = "question")
    val question: Question = Question(),
    @Json(name = "regions")
    val regions: List<Any> = listOf(),
    @Json(name = "tags")
    val tags: List<String> = listOf(),
    @Json(name = "type")
    val type: String = ""
)