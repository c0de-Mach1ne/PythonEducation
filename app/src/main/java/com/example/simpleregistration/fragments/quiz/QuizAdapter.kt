package com.example.simpleregistration.fragments.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleregistration.databinding.QuizItemBinding
import com.example.simpleregistration.fragments.model.Quiz

class QuizAdapter : ListAdapter<Quiz, QuizListViewHolder>(QuizListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuizListViewHolder(
        QuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: QuizListViewHolder, position: Int) =
        holder.bind(currentList[position])
}