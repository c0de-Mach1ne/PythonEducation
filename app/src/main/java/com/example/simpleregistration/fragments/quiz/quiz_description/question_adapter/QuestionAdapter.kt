package com.example.simpleregistration.fragments.quiz.quiz_description.question_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleregistration.databinding.QuestionItemBinding
import com.example.simpleregistration.fragments.model.QuizQuestions
import com.example.simpleregistration.utils.ItemClickListener

class QuestionAdapter(private val callback: ItemClickListener<QuizQuestions>) :
    ListAdapter<QuizQuestions, QuestionViewHolder>(QuestionDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuestionViewHolder(
        QuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) =
        holder.bind(currentList[position])
}