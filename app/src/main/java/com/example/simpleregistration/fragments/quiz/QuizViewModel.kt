package com.example.simpleregistration.fragments.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.fragments.model.DataRepository
import com.example.simpleregistration.fragments.model.Quiz

class QuizViewModel(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _quizList = MutableLiveData<Quiz>()
    var quizList: LiveData<Quiz> = _quizList


}