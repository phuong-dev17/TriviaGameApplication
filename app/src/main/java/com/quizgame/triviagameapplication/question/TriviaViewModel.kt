package com.quizgame.triviagameapplication.question

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

    init {
        getTriviaInfo(startInfo)

    }

    private fun getTriviaInfo(startInfo: StartInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            val triviaData = TriviaAPI.triviaApiService.getTriviaInfo(
                startInfo.numberOfQuestion?:10,
                startInfo.category,
                startInfo.difficulty
            )
               val questionData = triviaData.map {
                   QuestionData(
                       category = it.category,
                       question = it.question.text,
                       correctAnswer = it.correctAnswer,
                       incorrectAnswers = it.incorrectAnswers
            ) }
            _state.value = QuestionState.Loaded(questionData)
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