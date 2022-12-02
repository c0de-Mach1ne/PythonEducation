package com.example.simpleregistration.fragments.quiz.quiz_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.fragments.model.Quiz
import com.example.simpleregistration.fragments.repository.DataRepository
import com.example.simpleregistration.utils.state_model.Loading

class QuizListViewModel(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _quizList = MutableLiveData<List<Quiz>>()
    var quizList: LiveData<List<Quiz>> = _quizList

    private val _uiState = MutableLiveData<Loading>()
    var uiState: LiveData<Loading> = _uiState

    fun getQuizList() {
        _uiState.value = Loading.Start
        dataRepository.getQuiz().addOnCompleteListener { data ->
            val quizList = mutableListOf<Quiz>()
            for (quiz in data.result.children) {
                quiz.getValue(Quiz::class.java)?.let { quizList.add(it) }
            }
            _quizList.postValue(quizList)
            if(data.isComplete) _uiState.value = Loading.Stop
        }.addOnFailureListener {
            _uiState.value = Loading.Error(it.message.toString())
        }
    }
}