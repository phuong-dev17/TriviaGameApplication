package com.quizgame.triviagameapplication.pastResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.quizgame.triviagameapplication.R
import com.quizgame.triviagameapplication.common.AnswerData
import com.quizgame.triviagameapplication.databinding.ActivityPastResultBinding
import kotlinx.coroutines.launch

open class PastResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPastResultBinding
    private val viewModel : PastResultViewModel by viewModels {PastResultViewModel.factory(applicationContext)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPastResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureToolbar()

        binding.apply {
            btnAll.setOnClickListener{
                viewModel.showAllPastResult()
            }
            btnCorrect.setOnClickListener{
                viewModel.showCorrectResult()
            }
            btnIncorrect.setOnClickListener {
                viewModel.showIncorrectResult()
            }
        }

        observerUiState()
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    private fun observerUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect() { state ->
                    when ( state) {
                        PastResultState.Loading  -> {}
                        is PastResultState.Loaded -> showResult(state.answerData)
                        PastResultState.Failure -> {}
                    }
                }
            }
        }
    }
    private fun showResult(answerData: List<AnswerData>) {
        binding.recyclerview.adapter = PastResultRecyclerAdapter(answerData)
        binding.recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false )
    }
}