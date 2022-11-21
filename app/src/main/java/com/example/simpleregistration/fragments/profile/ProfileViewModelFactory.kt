package com.example.simpleregistration.fragments.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleregistration.auth.model.repository.AuthRepositoryImpl

class ProfileViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfieViewModel(authRepositoryImpl = AuthRepositoryImpl()) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}