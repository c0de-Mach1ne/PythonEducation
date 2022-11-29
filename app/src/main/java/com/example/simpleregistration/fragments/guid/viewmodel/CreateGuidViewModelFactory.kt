package com.example.simpleregistration.fragments.guid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleregistration.fragments.repository.DataRepository

class CreateGuidViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateGuidViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateGuidViewModel(dataRepository = DataRepository()) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}