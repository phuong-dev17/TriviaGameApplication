package com.quizgame.triviagameapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import com.quizgame.triviagameapplication.common.StartInfo
import com.quizgame.triviagameapplication.databinding.ActivityStartGameBinding
import com.quizgame.triviagameapplication.question.QuestionActivity

class StartGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartGameBinding
    private val categories = listOf<String>(
        "music",
        "sport and leisure",
        "film and tv",
        "arts and literature",
        "history",
        "society and culture",
        "science",
        "geography",
        "food and drink",
        "general knowledge"
    )

    private val difficulties = listOf<String>(
        "easy",
        "medium",
        "hard"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtSelectCategory.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                createCategoryPopupWindow(categories)
            }
        }

        binding.edtSelectDifficulty.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                createDifficultyPopupWindow(difficulties)
            }
        }

            binding.btnStartGame.setOnClickListener {
                val numberOfQuestion = binding.edtNumOfQuestions.text.toString().trim()
                val category = binding.edtSelectCategory.text.toString().trim()
                val difficulty = binding.edtSelectDifficulty.text.toString().trim()
                if(numberOfQuestion.isNotEmpty() && numberOfQuestion.toIntOrNull() != null && category.isNotEmpty() && difficulty.isNotEmpty()) {
                    Log.d("P123","CALLING START ACTIVITY")
                    startActivity(
                        QuestionActivity.intent(
                            this, StartInfo(
                                numberOfQuestion.toInt(),
                                category,
                               difficulty
                            )
                        )
                    )
                }
            }
        }

        private fun createCategoryPopupWindow(items: List<String>) {
            Log.d("P123", " CALLING POPUP WINDOWN")

            val listPopupWindow = ListPopupWindow(this)
            val adapter = ArrayAdapter(this, R.layout.menu_cell, items)
            listPopupWindow.setAdapter(adapter)
            listPopupWindow.width = 350
            listPopupWindow.anchorView = binding.edtSelectCategory

            listPopupWindow.setOnItemClickListener { _, _, position, _ ->
                // Lấy nội dung dòng được chọn
                val selectedItem = categories[position]
                // Điền nội dung dòng được chọn vào EditText
                binding.edtSelectCategory.setText(selectedItem)
                listPopupWindow.dismiss()
            }
            listPopupWindow.show()
        }

        private fun createDifficultyPopupWindow(items: List<String>) {
            val listPopupWindow = ListPopupWindow(this)
            val adapter = ArrayAdapter(this, R.layout.menu_cell, items)
            listPopupWindow.setAdapter(adapter)
            listPopupWindow.anchorView = binding.edtSelectDifficulty

            listPopupWindow.setOnItemClickListener { _, _, position, _ ->
                val selectedItem = difficulties[position]
                binding.edtSelectDifficulty.setText(selectedItem)
                listPopupWindow.dismiss()
            }
            listPopupWindow.show()
        }

        companion object {
            fun intent(context: Context): Intent {
                return Intent(
                    context,
                    StartGameActivity::class.java
                )
            }
        }
    }
