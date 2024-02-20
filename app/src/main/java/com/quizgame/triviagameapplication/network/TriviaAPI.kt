package com.quizgame.triviagameapplication.network

import com.quizgame.triviagameapplication.dto.TriviaQuestionDtoItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://the-trivia-api.com/"

val retrofitBuilder = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface TriviaApiService {
    @GET("/v2/questions")
    suspend fun getTriviaInfo(
        @Query("limit") limit: Int,
        @Query("categories") categories: String,
        @Query("difficulties") difficulties : String
    ) : List<TriviaQuestionDtoItem>
}

object TriviaAPI {
    val triviaApiService: TriviaApiService by lazy {
        retrofitBuilder.create(TriviaApiService::class.java)
    }
}