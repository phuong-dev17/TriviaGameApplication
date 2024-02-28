package com.quizgame.triviagameapplication.pastResult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.quizgame.triviagameapplication.R
import com.quizgame.triviagameapplication.common.AnswerData
import com.quizgame.triviagameapplication.databinding.CellPastResultLayoutBinding

class PastResultRecyclerAdapter (
    private val pastResultList : List<AnswerData>
) : RecyclerView.Adapter<PastResultRecyclerAdapter.PastResultViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastResultViewHolder {
        return PastResultViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return pastResultList.size
    }

    override fun onBindViewHolder(holder: PastResultViewHolder, position: Int) {
        holder.bind(pastResultList[position])
    }

    class PastResultViewHolder(
        private val binding: CellPastResultLayoutBinding) : ViewHolder(binding.root) {
            fun bind(pastResult : AnswerData) {
                binding.apply {
                    txtQuestion.text =  pastResult.question
                   txtResult.text = txtResult.context.getString(R.string.user_answer,pastResult.userAnswer)
                    if (pastResult.isCorrect) {
                        txtQuestion.setTextColor(txtQuestion.context.getColor(R.color.orange))
                        txtResult.setTextColor(txtResult.context.getColor(R.color.orange))
                        Line.setBackgroundColor(Line.context.getColor(R.color.orange))
                    } else {
                        txtQuestion.setTextColor(txtQuestion.context.getColor(R.color.gray89))
                        txtResult.setTextColor(txtResult.context.getColor(R.color.gray89))
                        Line.setBackgroundColor(Line.context.getColor(R.color.gray89))
                    }
                }
            }
        companion object {
            fun create( parent: ViewGroup) : PastResultViewHolder {
                val binding = CellPastResultLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PastResultViewHolder(binding)
            }
        }
    }
}