package com.example.simpleregistration.fragments.quiz

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleregistration.databinding.QuizItemBinding
import com.example.simpleregistration.fragments.model.Quiz
import com.example.simpleregistration.utils.ItemClickListener

class QuizListViewHolder(
    private val binding: QuizItemBinding,
    private val callback: ItemClickListener<Quiz>
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(quiz: Quiz) {
        binding.root.setOnClickListener { callback.onClickItem(quiz) }
        binding.tvQuizName.text = quiz.title
    }
}