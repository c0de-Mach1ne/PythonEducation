package com.example.simpleregistration.fragments.guid.guid_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleregistration.fragments.repository.DataRepository

class GuidListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GuidListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GuidListViewModel(dataRepository = DataRepository() ) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}