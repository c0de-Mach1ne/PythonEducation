package com.example.simpleregistration.fragments.quiz.quiz_description.answer_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleregistration.databinding.AnswerItemBinding
import com.example.simpleregistration.fragments.model.QuizAnswers
import com.example.simpleregistration.utils.ItemClickListener

class AnswerAdapter(private val callback: ItemClickListener<QuizAnswers>) :
    ListAdapter<QuizAnswers, AnswerViewHolder>(AnswerDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AnswerViewHolder(
        AnswerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) =
        holder.bind(currentList[position])
}