package com.quizgame.triviagameapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quizgame.triviagameapplication.databinding.ActivityMainBinding
import com.quizgame.triviagameapplication.pastResult.PastResultActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartGame.setOnClickListener {
            gotoStartGameActivity()
        }
        binding.btnPastResults.setOnClickListener {
            gotoPastResultActivity()
        }
    }

    private fun gotoPastResultActivity() {
        startActivity(Intent(this, PastResultActivity::class.java))
    }

    private fun gotoStartGameActivity() {
        startActivity(StartGameActivity.intent(this))
        finish()
    }

    companion object {
        fun intent(context: Context) : Intent {
            val intent = Intent(
                context,
                MainActivity::class.java
            )
            return intent
        }
    }
}