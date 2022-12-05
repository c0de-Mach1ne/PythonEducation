package com.example.simpleregistration.fragments.quiz.description

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleregistration.fragments.repository.DataRepository

class QuizDescriptionViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizDescriptionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizDescriptionViewModel(dataRepository = DataRepository()) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}