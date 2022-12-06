package com.example.simpleregistration.fragments.quiz.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.R
import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.fragments.model.QuizQuestions
import com.example.simpleregistration.fragments.model.Result
import com.example.simpleregistration.fragments.repository.DataRepository
import com.example.simpleregistration.utils.state_model.UiState

class QuizDescriptionViewModel(
    private val repo: DataRepository,
) : ViewModel() {

    init {
        getUserFullName()
    }

    private val _questionSize = MutableLiveData<Int>()
    var questionSize: LiveData<Int> = _questionSize

    private val _currentQuestion = MutableLiveData<Int>()
    var currentQuestion: LiveData<Int> = _currentQuestion

    private val _uiState = MutableLiveData<UiState>()
    var uiState: LiveData<UiState> = _uiState

    private val _fullName = MutableLiveData<String>()
    var fullName: LiveData<String> = _fullName

    private var currentQuestions = -1
    private var indexCorrectAnswer = -1
    private var countCorrectAnswers = 0
    private var selectAnswer = -1
    private var indexLastAnswer: Long = -1
    private var questSize = -1

    fun getQuestionSize(questions: List<QuizQuestions>?) {
        questSize = questions?.size ?: -1
        _questionSize.value = questSize
    }

    fun selectAnswer(checkedRadioButtonId: Int) {
        selectAnswer = when (checkedRadioButtonId) {
            R.id.rbAnswer_1 -> 0
            R.id.rbAnswer_2 -> 1
            R.id.rbAnswer_3 -> 2
            R.id.rbAnswer_4 -> 3
            else -> -1
        }
    }

    fun selectAnswer(questions: List<QuizQuestions>?) {
        questions?.get(currentQuestions)?.answers?.forEachIndexed { index, question ->
            if (question.correctFlag == true) indexCorrectAnswer = index
        }
    }

    fun checkAnswerIsCorrect() {
        if (indexCorrectAnswer == selectAnswer && countCorrectAnswers < questSize) {
            countCorrectAnswers++
        }
    }

    fun selectCurrentQuestion() {
        if (currentQuestions < questSize - 1) {
            currentQuestions++
            _currentQuestion.value = currentQuestions
        } else {
            _uiState.value =
                UiState.Success(((countCorrectAnswers.toFloat() / questSize.toFloat()) * 100).toString())
        }
    }

    fun pushResult(result: Result, quizId: Int?) {
        repo.pushResult(
            result = result,
            quizId = quizId.toString(),
            userIndex = indexLastAnswer.toString())
    }

    fun getUserIndex(quizId: Int) {
        repo.getResultList("$quizId").addOnSuccessListener {
            indexLastAnswer = it.childrenCount
        }
    }

    private fun getUserFullName() {
        repo.getDatabaseUser()?.addOnSuccessListener {
            with(it.getValue(UserPersonalInfo::class.java)) {
                if (this != null) _fullName.value =
                    "${this.sureName} ${this.name} ${this.patronymic}"
            }
        }
    }
}