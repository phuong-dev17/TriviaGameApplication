package com.quizgame.triviagameapplication.question

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.quizgame.triviagameapplication.R
import com.quizgame.triviagameapplication.common.QuestionData
import com.quizgame.triviagameapplication.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {
    private var _binding: FragmentQuestionBinding? = null
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
        Log.d("P123", "BEFORE")
        binding.txtQuestion.text = questionData.question
        binding.recyclerview.adapter = AnswerRecyclerAdapter(
            items = questionData.allAnswer.shuffled(),
            onCellClicked = ::showResult)
        binding.recyclerview.layoutManager = GridLayoutManager(
            requireContext(),2, GridLayoutManager.VERTICAL, false)
        Log.d("P123", "AFTER")
    }

    private fun showResult(answer: String) {
        Log.d("P123", "SHOW RESULT")
    }
}