package com.example.simpleregistration.fragments.quiz.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.fragments.model.Quiz
import com.example.simpleregistration.fragments.repository.DataRepository
import com.example.simpleregistration.utils.state_model.LoadingState

class QuizListViewModel(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _quizList = MutableLiveData<List<Quiz>>()
    var quizList: LiveData<List<Quiz>> = _quizList

    private val _uiState = MutableLiveData<LoadingState>()
    var uiState: LiveData<LoadingState> = _uiState

    fun getQuizList() {
        _uiState.value = LoadingState.Start
        dataRepository.getQuiz().addOnCompleteListener { data ->
            val quizList = mutableListOf<Quiz>()
            for (quiz in data.result.children) {
                quiz.getValue(Quiz::class.java)?.let { quizList.add(it) }
            }
            _quizList.postValue(quizList)
            if(data.isComplete) _uiState.value = LoadingState.Stop
        }.addOnFailureListener {
            _uiState.value = LoadingState.Error(it.message.toString())
        }
    }
}