package com.example.simpleregistration.fragments.quiz.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleregistration.databinding.QuizItemBinding
import com.example.simpleregistration.fragments.model.Quiz
import com.example.simpleregistration.utils.ItemClickListener

class QuizAdapter(private val callback: ItemClickListener<Quiz>) :
    ListAdapter<Quiz, QuizListViewHolder>(QuizListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuizListViewHolder(
        QuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: QuizListViewHolder, position: Int) =
        holder.bind(currentList[position])
}