package com.quizgame.triviagameapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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