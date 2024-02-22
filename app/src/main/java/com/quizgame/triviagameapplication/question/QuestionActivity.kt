package com.quizgame.triviagameapplication.question

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.IntentCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.quizgame.triviagameapplication.QuestionState
import com.quizgame.triviagameapplication.common.QuestionData
import com.quizgame.triviagameapplication.common.StartInfo
import com.quizgame.triviagameapplication.databinding.ActivityQuestionBinding
import kotlinx.coroutines.launch

class QuestionActivity : AppCompatActivity() {
    private val viewModel : TriviaViewModel by viewModels {
        TriviaViewModel.factory(startInfo())
    }

    private lateinit var binding: ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect() { state ->
                    when (state) {
                        QuestionState.Loading -> {}
                        is QuestionState.Loaded -> showQuestions(state.questions)
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

    private fun showQuestions(questions: List<QuestionData>) {
        val adapter = QuestionViewPagerAdapter(this, questions)
        binding.viewPager.adapter = adapter
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