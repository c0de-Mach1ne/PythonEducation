package com.example.simpleregistration.fragments.quiz

import androidx.recyclerview.widget.DiffUtil
import com.example.simpleregistration.fragments.model.Quiz


class QuizDiffUtilCallback : DiffUtil.ItemCallback<Quiz>() {

    override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz) =
        oldItem.questions == newItem.questions
}