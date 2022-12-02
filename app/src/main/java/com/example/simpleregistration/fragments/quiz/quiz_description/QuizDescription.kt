package com.example.simpleregistration.fragments.quiz.quiz_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentQuizDescriptionBinding
import com.example.simpleregistration.fragments.model.QuizAnswers
import com.example.simpleregistration.fragments.model.QuizQuestions
import com.example.simpleregistration.fragments.quiz.quiz_description.answer_adapter.AnswerAdapter
import com.example.simpleregistration.fragments.quiz.quiz_description.question_adapter.QuestionAdapter
import com.example.simpleregistration.utils.ItemClickListener

class QuizDescription : Fragment(R.layout.fragment_quiz_description) {

    private lateinit var binding: FragmentQuizDescriptionBinding
    private val viewModel by viewModels<QuizDescriptionViewModel> { QuizDescriptionViewModelFactory() }
    private val args by navArgs<QuizDescriptionArgs>()

    private val questionAdapter by lazy {
        QuestionAdapter(
            object : ItemClickListener<QuizQuestions> {
                override fun onClickItem(value: QuizQuestions) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    private val answerAdapter by lazy {
        AnswerAdapter(
            object : ItemClickListener<QuizAnswers> {
                override fun onClickItem(value: QuizAnswers) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuizDescriptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}