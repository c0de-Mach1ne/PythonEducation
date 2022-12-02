package com.example.simpleregistration.fragments.quiz.quiz_description.question_adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.simpleregistration.fragments.model.QuizQuestions

class QuestionDiffUtilCallback : DiffUtil.ItemCallback<QuizQuestions>() {

    override fun areItemsTheSame(oldItem: QuizQuestions, newItem: QuizQuestions) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: QuizQuestions, newItem: QuizQuestions) =
        oldItem.questionText == newItem.questionText
}