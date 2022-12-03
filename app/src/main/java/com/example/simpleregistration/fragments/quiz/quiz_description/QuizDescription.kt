package com.example.simpleregistration.fragments.quiz.quiz_description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentQuizDescriptionBinding

class QuizDescription : Fragment(R.layout.fragment_quiz_description) {

    private lateinit var binding: FragmentQuizDescriptionBinding
    private val viewModel by viewModels<QuizDescriptionViewModel> { QuizDescriptionViewModelFactory() }
    private val args by navArgs<QuizDescriptionArgs>()

    private var currentQuestions = 0
    private var currentIndex = -1
    private var currentAnswer = 0
    private var indexAnswer = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuizDescriptionBinding.inflate(layoutInflater, container, false)
        setAnswers()
        binding.btnAnswer.setOnClickListener {
            if (currentQuestions < (args.quiz.questions?.size ?: 1) - 1) currentQuestions++
            setAnswers()
            checkRightAnswer()
            Log.d("TAG", "currentAnswer = $currentAnswer")
        }
        return binding.root
    }

    private fun setAnswers() {
        with(binding) {
            tvTitle.text = args.quiz.questions?.get(currentQuestions)?.questionText
            rbAnswer1.text =
                args.quiz.questions?.get(currentQuestions)?.answers?.get(0)?.description
            rbAnswer2.text =
                args.quiz.questions?.get(currentQuestions)?.answers?.get(1)?.description
            rbAnswer3.text =
                args.quiz.questions?.get(currentQuestions)?.answers?.get(2)?.description
            rbAnswer4.text =
                args.quiz.questions?.get(currentQuestions)?.answers?.get(3)?.description

            args.quiz.questions?.get(currentQuestions)?.answers?.forEachIndexed() { index, question ->
                if (question.correctFlag == true) {
                    currentIndex = index
                    Log.d("TAG", "currentIndex = $currentIndex, " +
                            "currentQuestions = $currentQuestions, " +
                            "description = ${question.description}")
                }
            }
        }
    }

    private fun checkRightAnswer() {
        when (binding.radioGroup.checkedRadioButtonId) {
            R.id.rbAnswer_1 -> indexAnswer = 0
            R.id.rbAnswer_2 -> indexAnswer = 1
            R.id.rbAnswer_3 -> indexAnswer = 2
            R.id.rbAnswer_4 -> indexAnswer = 3
        }
        if (currentIndex == indexAnswer) {
            currentAnswer++
        }
        Log.d("TAG", "currentIndex = $currentIndex, indexAnswer = $indexAnswer")
    }
}
