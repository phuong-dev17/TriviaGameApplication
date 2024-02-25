package com.quizgame.triviagameapplication.question

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.quizgame.triviagameapplication.QuestionState
import com.quizgame.triviagameapplication.common.QuestionData
import com.quizgame.triviagameapplication.common.StartInfo
import com.quizgame.triviagameapplication.network.TriviaAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TriviaViewModel(private val startInfo: StartInfo) : ViewModel() {
    private var _state : MutableStateFlow<QuestionState> = MutableStateFlow(QuestionState.Loading)
    val state = _state
    private lateinit var questionData : List<QuestionData>

    init {
        getTriviaInfo(startInfo)
    }

    private fun getTriviaInfo(startInfo: StartInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val triviaData = TriviaAPI.triviaApiService.getTriviaInfo(
                    startInfo.numberOfQuestion?:10,
                    startInfo.category,
                    startInfo.difficulty
                )
                questionData = triviaData.map {
                    QuestionData(
                        questionId = it.id,
                        question = it.question.text,
                        allAnswer = (it.incorrectAnswers).toMutableList().plus(it.correctAnswer),
                        correctAnswer = it.correctAnswer,
                        currentAnswer = null
                    ) }
                _state.value = QuestionState.Loaded(questionData)
            } catch (exception: Exception) {
                Log.d( "P123", "$exception")
                throw exception
            }

        }
    }

    companion object {
        fun factory(startInfo: StartInfo): ViewModelProvider.Factory {
            return viewModelFactory {
                initializer {
                    TriviaViewModel(startInfo)
                }
            }
        }
    }
}