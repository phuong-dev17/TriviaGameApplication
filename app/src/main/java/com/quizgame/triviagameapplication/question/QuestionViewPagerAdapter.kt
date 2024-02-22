package com.quizgame.triviagameapplication.question

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.quizgame.triviagameapplication.QuestionState
import com.quizgame.triviagameapplication.common.QuestionData
import kotlinx.coroutines.coroutineScope

class QuestionViewPagerAdapter(
    activity: FragmentActivity,
    private val questions: List<QuestionData>
): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return questions.size
    }


    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.instance(questions[position])
    }

}