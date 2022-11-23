package com.example.simpleregistration.fragments.guid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleregistration.fragments.model.repository.DataRepository

class GuidViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GuidViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GuidViewModel(dataRepository = DataRepository() ) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}