package com.example.simpleregistration.fragments.quiz.quiz_description.answer_adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleregistration.databinding.AnswerItemBinding
import com.example.simpleregistration.fragments.model.QuizAnswers
import com.example.simpleregistration.utils.ItemClickListener

class AnswerViewHolder(
    private val binding: AnswerItemBinding,
    private val callback: ItemClickListener<QuizAnswers>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(quizAnswers: QuizAnswers) {
        binding.root.setOnClickListener { callback.onClickItem(quizAnswers) }
    }
}