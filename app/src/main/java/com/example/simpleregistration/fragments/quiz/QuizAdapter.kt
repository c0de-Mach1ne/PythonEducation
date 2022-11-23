package com.example.simpleregistration.fragments.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleregistration.databinding.QuizItemBinding
import com.example.simpleregistration.fragments.model.Quiz

class QuizAdapter : ListAdapter<Quiz, QuizViewHolder>(QuizDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuizViewHolder(
        QuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) =
        holder.bind(currentList[position])
}