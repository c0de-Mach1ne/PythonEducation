package com.example.simpleregistration.fragments.quiz.quiz_description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var countCurrentAnswers = 0
    private var selectAnswer = -1
    private val questions = args.quiz.questions

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuizDescriptionBinding.inflate(layoutInflater, container, false)
        setAnswers()
        binding.btnAnswer.setOnClickListener {
            if (currentQuestions < (questions?.size ?: 1) - 1) {
                selectRightAnswer()
                checkRightAnswer()
                currentQuestions++
            } else {
                selectRightAnswer()
                checkRightAnswer()
                Toast.makeText(binding.radioGroup.context,
                    "Поздравляю, ты ответил " +
                            "правильно на $countCurrentAnswers из ${questions?.size}",
                    Toast.LENGTH_SHORT).show()
            }
            setAnswers()
        }
        return binding.root
    }

    private fun setAnswers() {
        with(binding) {
            tvTitle.text = questions?.get(currentQuestions)?.questionText
            rbAnswer1.text =
                questions?.get(currentQuestions)?.answers?.get(0)?.description
            rbAnswer2.text =
                questions?.get(currentQuestions)?.answers?.get(1)?.description
            rbAnswer3.text =
                questions?.get(currentQuestions)?.answers?.get(2)?.description
            rbAnswer4.text =
                questions?.get(currentQuestions)?.answers?.get(3)?.description
        }
    }

    private fun selectRightAnswer() {
        questions?.get(currentQuestions)?.answers?.forEachIndexed { index, question ->
            if (question.correctFlag == true) {
                currentIndex = index
            }
        }
    }

    private fun checkRightAnswer() {
        when (binding.radioGroup.checkedRadioButtonId) {
            R.id.rbAnswer_1 -> selectAnswer = 0
            R.id.rbAnswer_2 -> selectAnswer = 1
            R.id.rbAnswer_3 -> selectAnswer = 2
            R.id.rbAnswer_4 -> selectAnswer = 3
        }
        if (currentIndex == selectAnswer) {
            countCurrentAnswers++
        }
    }
}
