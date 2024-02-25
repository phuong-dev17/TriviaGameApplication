package com.quizgame.triviagameapplication.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.quizgame.triviagameapplication.common.AnswerData
import com.quizgame.triviagameapplication.common.QuestionData

@Database(entities = [AnswerEntity::class], version = 1)
abstract class AnswerRoomDB : RoomDatabase() {
    abstract fun answerDao(): AnswerDao
}

class AnswerDB(applicationContext: Context) : AnswerDBInterface {
    private var instance: AnswerRoomDB? = null

    private val answerDao
        get() = instance!!.answerDao()

    init {
        initialize(applicationContext)
    }

    private fun initialize(applicationContext: Context) {
        instance = Room.databaseBuilder(
            applicationContext,
            AnswerRoomDB::class.java,
            "AnswerRoomDB"
        ).build()
    }

    companion object {
        private var dbServiceInstance: AnswerDB? = null
        fun instance(applicationContext: Context): AnswerDB {
            if (dbServiceInstance == null) {
                dbServiceInstance = AnswerDB(applicationContext)
            }
            return dbServiceInstance!!

        }
    }

    override suspend fun add(inputAnswer: AnswerData) {
        //answerDao.getQuestionById
        //If ton tai -> answerDao.updateAnswer
        if (answerDao.getQuestionFromId(inputAnswer.questionId) != null) {
            answerDao.updateAnswer(AnswerEntity(inputAnswer))
        } else {
            answerDao.addAnswer(AnswerEntity(inputAnswer))
        }
    }

    override suspend fun getAll(): List<AnswerData> {
        return answerDao.getAll().map { AnswerData(it) }
    }



}