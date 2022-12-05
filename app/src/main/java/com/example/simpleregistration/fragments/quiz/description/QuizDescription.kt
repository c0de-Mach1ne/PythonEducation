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
import com.example.simpleregistration.fragments.model.Result
import com.example.simpleregistration.utils.state_model.UiState

class QuizDescription : Fragment(R.layout.fragment_quiz_description) {

    private lateinit var binding: FragmentQuizDescriptionBinding
    private val viewModel by viewModels<QuizDescriptionViewModel> { QuizDescriptionViewModelFactory() }
    private val args by navArgs<QuizDescriptionArgs>()

    private var fullName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // установили userIndex во вьюмодели
        args.quiz.id?.let { viewModel.getUserIndex(it) }
        // получили размер списка вопросов
        viewModel.getQuestionSize(args.quiz.questions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuizDescriptionBinding.inflate(layoutInflater, container, false)
        viewModel.selectCurrentQuestion()
        observeViewModel()
        binding.btnAnswer.setOnClickListener {
            // передали выбранный элемент
            viewModel.selectAnswer(binding.radioGroup.checkedRadioButtonId)
            // установили правильный ответ
            viewModel.selectAnswer(args.quiz.questions)
            // проверяем правильный ли текущий ответ
            viewModel.checkAnswerIsCorrect()
            // установили следующий вопрос
            viewModel.selectCurrentQuestion()
        }
        return binding.root
    }

    private fun setAnswers(currentQuestions: Int) {
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

    private fun observeViewModel() {
        viewModel.currentQuestion.observe(viewLifecycleOwner) { currentQuestion ->
            binding.linearProgressBar.progress = currentQuestion
            setAnswers(currentQuestion)
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    Toast.makeText(binding.radioGroup.context,
                        "Поздравляю, ты ответил правильно на ${it.data}% вопросов",
                        Toast.LENGTH_SHORT).show()

                    viewModel.pushResult(result = Result(name = fullName, result = it.data.toInt()),
                        quizId = args.quiz.id)

                    findNavController().navigate(R.id.action_quizDescription_to_quizResult)
                }
                else -> {
                    Toast.makeText(binding.radioGroup.context,
                        "Something wrong!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.questionSize.observe(viewLifecycleOwner) {
            binding.linearProgressBar.max = it - 1
        }

        viewModel.fullName.observe(viewLifecycleOwner) {
            fullName = it
        }
    }
}
