package com.example.simpleregistration.fragments.quiz.list

import androidx.recyclerview.widget.DiffUtil
import com.example.simpleregistration.fragments.model.Quiz


class QuizListDiffUtilCallback : DiffUtil.ItemCallback<Quiz>() {

    override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz) =
        oldItem.questions == newItem.questions
}