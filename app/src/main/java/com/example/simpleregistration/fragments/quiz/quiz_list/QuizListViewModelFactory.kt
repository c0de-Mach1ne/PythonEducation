package com.example.simpleregistration.fragments.quiz.quiz_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleregistration.fragments.repository.DataRepository

class QuizListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizListViewModel(dataRepository = DataRepository()) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}