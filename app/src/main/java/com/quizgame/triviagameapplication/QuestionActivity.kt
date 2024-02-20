package com.quizgame.triviagameapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.IntentCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.quizgame.triviagameapplication.common.QuestionData
import com.quizgame.triviagameapplication.common.StartInfo
import kotlinx.coroutines.launch

class QuestionActivity : AppCompatActivity() {
    private val viewModel : TriviaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect() { state ->
                    when (state) {
                        QuestionState.Loading -> {}
                        is QuestionState.Loaded -> showQuestions(state.question)
                        QuestionState.Failure -> {}
                    }
                }
            }
        }
    }

    private fun startInfo() : StartInfo {
        val startInfo = IntentCompat.getParcelableExtra(intent, KEY, StartInfo::class.java)
        return startInfo!!
    }

    private fun showQuestions(question: List<QuestionData>) {
        Log.d("P123", "TRIVIA DATA $question")
    }

    companion object {
        private const val KEY = "KEY"
        fun intent(context: Context, startInfo: StartInfo) :Intent {
            val intent = Intent(context, QuestionActivity::class.java)
            if(startInfo != null) {
                intent.putExtra(KEY, startInfo)
            }
            return intent
        }

    }
}