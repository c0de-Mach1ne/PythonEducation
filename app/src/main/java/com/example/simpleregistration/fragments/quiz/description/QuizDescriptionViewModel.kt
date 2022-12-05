package com.example.simpleregistration.fragments.quiz.description

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.R
import com.example.simpleregistration.fragments.model.QuizQuestions
import com.example.simpleregistration.fragments.model.QuizResult
import com.example.simpleregistration.fragments.repository.DataRepository

class QuizDescriptionViewModel(
    private val repo: DataRepository,
) : ViewModel() {

    private var currentQuestions = 0
    private var currentIndex = -1
    private var countCurrentAnswers = 0
    private var selectAnswer = -1

    fun selectCorrectAnswer(checkedRadioButtonId: Int) {
        selectAnswer = when (checkedRadioButtonId) {
            R.id.rbAnswer_1 -> 0
            R.id.rbAnswer_2 -> 1
            R.id.rbAnswer_3 -> 2
            R.id.rbAnswer_4 -> 3
            else -> -1
        }
    }

    fun calculateCorrectAnswers(questionSize: Int) {
        if (currentIndex == selectAnswer && countCurrentAnswers < questionSize) {
            countCurrentAnswers++
        }
    }

    fun selectCorrectAnswer(questions: List<QuizQuestions>) {
        questions[currentQuestions].answers?.forEachIndexed { index, question ->
            if (question.correctFlag == true) {
                currentIndex = index
            }
        }
    }

    fun selectCurrentQuestion(questionSize: Int) {
        if (currentQuestions < questionSize - 1) {
            currentQuestions++
        } else {
            // TODO: какой-то стейт прокидываем
        }
    }

    fun pushResult(result: QuizResult, quizId: String, userIndex: String) {
        repo.pushResult(
            result = result,
            quizId = quizId,
            userIndex = userIndex)
    }

    fun getUserIndex(quizId: Int){
        repo.getResultList("$quizId").addOnSuccessListener {
            Log.d("TAG", "children count ${it.childrenCount}")
        }
    }
}