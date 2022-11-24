package com.example.simpleregistration.fragments.quiz

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleregistration.databinding.QuizItemBinding
import com.example.simpleregistration.fragments.model.Quiz

class QuizListViewHolder(
    private val binding: QuizItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(quiz: Quiz) {
        binding.tvQuizName.text = quiz.title
    }
}