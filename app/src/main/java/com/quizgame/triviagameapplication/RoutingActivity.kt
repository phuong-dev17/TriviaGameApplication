package com.quizgame.triviagameapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.quizgame.triviagameapplication.databinding.ActivityRoutingBinding
import kotlinx.coroutines.launch

class RoutingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRoutingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readAccessStatusDataStore()

        binding.btnTestMe.setOnClickListener {
            val application = (application as TriviaGameApplication)
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED) {
                    application.accessStatusRepository
                        .put(accessStatus = AccessStatus(isFirstTimeAccess = false))
                }
            }

        }
    }

    private fun readAccessStatusDataStore() {
        val application = (application as TriviaGameApplication)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                application.accessStatusRepository.hasAccessed.collect{ accessStatus ->
                    Log.d("P123", "ACCESS STATUS ${accessStatus.isFirstTimeAccess}")
                    if (!accessStatus.isFirstTimeAccess) {
                        gotoTriviaGameActivity()
                    }
                }
            }
        }
    }

    private fun gotoTriviaGameActivity() {
        startActivity(MainActivity.intent(this))
        finish()
    }

}