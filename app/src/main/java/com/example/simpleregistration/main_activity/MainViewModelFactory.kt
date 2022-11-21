package com.example.simpleregistration.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleregistration.auth.model.repository.AuthRepositoryImpl

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(authRepositoryImpl = AuthRepositoryImpl()) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}