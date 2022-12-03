package com.example.simpleregistration.fragments.quiz.quiz_description.question_adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleregistration.databinding.QuestionItemBinding
import com.example.simpleregistration.fragments.model.QuizQuestions
import com.example.simpleregistration.utils.ItemClickListener

class QuestionViewHolder(
    private val binding: QuestionItemBinding,
    private val callback: ItemClickListener<QuizQuestions>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(quizQuestion: QuizQuestions) {
        binding.root.setOnClickListener { callback.onClickItem(quizQuestion) }

    }
}