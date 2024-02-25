package com.quizgame.triviagameapplication.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface AnswerDao {
    @Query("SELECT * FROM answer")
    suspend fun getAll(): List<AnswerEntity>
    @Insert
    suspend fun addAnswer(answer: AnswerEntity)

    @Update
    suspend fun updateAnswer(answer: AnswerEntity)

    @Query("SELECT * FROM answer WHERE id = :questionID")
    suspend fun getQuestionFromId(questionID: String): AnswerEntity?

}