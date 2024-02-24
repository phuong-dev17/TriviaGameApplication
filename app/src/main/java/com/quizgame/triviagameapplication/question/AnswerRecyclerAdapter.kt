package com.quizgame.triviagameapplication.question

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quizgame.triviagameapplication.databinding.CellAnswerLayoutBinding

class AnswerRecyclerAdapter(
    private val items: List<String>,
   private val onCellClicked: (String) -> Unit
): RecyclerView.Adapter<AnswerRecyclerAdapter.AnswerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder.create(parent, onCellClicked)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class AnswerViewHolder(
        private val binding: CellAnswerLayoutBinding,
        private val onCellClicked: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(answer: String) {
            binding.btnAnswer.text = answer
            binding.btnAnswer.setOnClickListener {
                onCellClicked(answer)
            }
        }

        companion object {
            fun create(parent: ViewGroup, onCellClicked: (String) -> Unit) : AnswerViewHolder {
                val binding = CellAnswerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return AnswerViewHolder(binding, onCellClicked)
            }
        }
    }




}