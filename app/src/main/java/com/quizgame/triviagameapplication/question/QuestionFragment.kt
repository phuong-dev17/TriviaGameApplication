package com.quizgame.triviagameapplication.question

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.quizgame.triviagameapplication.R
import com.quizgame.triviagameapplication.common.AnswerData
import com.quizgame.triviagameapplication.common.QuestionData
import com.quizgame.triviagameapplication.databinding.FragmentQuestionBinding
import com.quizgame.triviagameapplication.repository.AnswerDB
import com.quizgame.triviagameapplication.repository.AnswerDBInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionFragment : Fragment() {
    private var _binding: FragmentQuestionBinding? = null
    private lateinit var answerDBInterface: AnswerDBInterface
    private val binding get() = _binding!!
    private lateinit var questionData: QuestionData

    companion object {
        private const val DATA_KEY = "DATA_KEY"
        fun instance(data: QuestionData): QuestionFragment {
            val fragment = QuestionFragment()
            val bundle = Bundle()
            bundle.putParcelable(
                DATA_KEY, data
            )
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionData =
                BundleCompat.getParcelable(it, DATA_KEY, QuestionData::class.java)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        answerDBInterface = AnswerDB(requireContext())
        
        binding.txtQuestion.text = questionData.question
        binding.recyclerview.adapter = AnswerRecyclerAdapter(
            items = questionData.allAnswer.shuffled(),
            onCellClicked = ::showResult)
        binding.recyclerview.layoutManager = GridLayoutManager(
            requireContext(),2, GridLayoutManager.VERTICAL, false)
    }

    private fun showResult(answer: String) {
        val answerData = AnswerData(
            questionId = questionData.questionId,
            question = questionData.question,
            userAnswer = answer,
            isCorrect = (answer == questionData.correctAnswer)
        )
        lifecycleScope.launch (Dispatchers.IO) {
            answerDBInterface.add(answerData)
        }
    }
}