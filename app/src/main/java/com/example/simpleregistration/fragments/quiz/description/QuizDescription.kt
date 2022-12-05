package com.example.simpleregistration.fragments.quiz.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuizDescriptionBinding.inflate(layoutInflater, container, false)
        setAnswers()

        binding.linearProgressBar.max = args.quiz.questions?.size ?: 0
        binding.btnAnswer.setOnClickListener {
            binding.linearProgressBar.progress = currentQuestions + 1
            selectRightAnswer()
            checkRightAnswer()
            if (currentQuestions < (args.quiz.questions?.size ?: 1) - 1) {
                currentQuestions++
            } else {
                Toast.makeText(binding.radioGroup.context,
                    "Поздравляю, ты ответил " +
                            "правильно на $countCurrentAnswers из ${args.quiz.questions?.size}",
                    Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_quizDescription_to_quizResult)
            }
            setAnswers()
        }
        return binding.root
    }

    private fun setAnswers() {
        with(binding) {
            tvTitle.text = args.quiz.questions?.get(currentQuestions)?.questionText
            with(args.quiz.questions?.get(currentQuestions)?.answers) {
                rbAnswer1.text = this?.get(0)?.description
                rbAnswer2.text = this?.get(1)?.description
                rbAnswer3.text = this?.get(2)?.description
                rbAnswer4.text = this?.get(3)?.description
            }
        }
    }

    private fun selectRightAnswer() {
        args.quiz.questions?.get(currentQuestions)?.answers?.forEachIndexed { index, question ->
            if (question.correctFlag == true) {
                currentIndex = index
            }
        }
    }

    private fun checkRightAnswer() {
        selectAnswer = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.rbAnswer_1 -> 0
            R.id.rbAnswer_2 -> 1
            R.id.rbAnswer_3 -> 2
            R.id.rbAnswer_4 -> 3
            else -> -1
        }

        if (currentIndex == selectAnswer && countCurrentAnswers < (args.quiz.questions?.size ?: 0)) {
            countCurrentAnswers++
        }
    }
}
