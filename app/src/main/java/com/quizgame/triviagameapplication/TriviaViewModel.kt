package com.quizgame.triviagameapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quizgame.triviagameapplication.network.TriviaAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TriviaViewModel : ViewModel() {
    private var _state : MutableStateFlow<QuestionState> = MutableStateFlow(QuestionState.Loading)
    val state = _state

    init {
        getTriviaInfo()
    }

    private fun getTriviaInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val triviaData = TriviaAPI.triviaApiService.getTriviaInfo(
                1,
                "music",
                "easy"
            )
            Log.d("P123","TRIVIA DATA $triviaData")
        }
    }
}