package com.example.simpleregistration.fragments.quiz.quiz_description.answer_adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.simpleregistration.fragments.model.QuizAnswers

class AnswerDiffUtilCallback : DiffUtil.ItemCallback<QuizAnswers>() {

    override fun areItemsTheSame(oldItem: QuizAnswers, newItem: QuizAnswers) = oldItem == newItem

    override fun areContentsTheSame(oldItem: QuizAnswers, newItem: QuizAnswers) =
        oldItem.isCorrect == newItem.isCorrect
}