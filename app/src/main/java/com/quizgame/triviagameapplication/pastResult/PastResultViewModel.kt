package com.quizgame.triviagameapplication.pastResult

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.quizgame.triviagameapplication.common.AnswerData
import com.quizgame.triviagameapplication.repository.AnswerDB
import com.quizgame.triviagameapplication.repository.AnswerDBInterface
import com.quizgame.triviagameapplication.repository.AnswerRoomDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PastResultViewModel(private val applicationContext: Context) : ViewModel() {

    private val answerRoomDB : AnswerDBInterface = AnswerDB.instance(applicationContext)
    private var _state : MutableStateFlow<PastResultState> = MutableStateFlow(PastResultState.Loading)
    val state = _state
    private var _showingPastResult = 0
    val showingPastResult : Int
        get() = _showingPastResult

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPastResult()
        }

    }


    fun showAllPastResult() {
        viewModelScope.launch ( Dispatchers.IO ) {
            _showingPastResult = 0
            getPastResult()
        }

    }

    fun showCorrectResult() {
        viewModelScope.launch (Dispatchers.IO) {
            _showingPastResult = 1
            getPastResult()
        }

    }

    fun showIncorrectResult() {
        viewModelScope.launch(Dispatchers.IO) {
            _showingPastResult = 2
            getPastResult()
        }

    }
    private suspend fun getPastResult() {
        val listPastResult = when ( showingPastResult) {
            0 -> { answerRoomDB.getAll()}
            1 -> { answerRoomDB.getAll().filter { it.isCorrect }
                }
            2 -> { answerRoomDB.getAll().filter { !it.isCorrect }
            } else -> {
                emptyList()
            }
        }
        _state.value = PastResultState.Loaded(listPastResult)
    }
    companion object {
        fun factory(applicationContext: Context) : ViewModelProvider.Factory {
            return viewModelFactory {
                initializer {
                    PastResultViewModel(applicationContext)
                }
            }
        }
    }
}