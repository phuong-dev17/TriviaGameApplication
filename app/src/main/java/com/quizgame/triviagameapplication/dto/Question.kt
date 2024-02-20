package com.quizgame.triviagameapplication.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Question(
    @Json(name = "text")
    val text: String = ""
)