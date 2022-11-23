package com.example.simpleregistration.fragments.guid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleregistration.fragments.model.UserRepository

class GuidViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GuidViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GuidViewModel(userRepository = UserRepository() ) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}