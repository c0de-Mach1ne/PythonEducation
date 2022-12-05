package com.example.simpleregistration.fragments.guid.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.fragments.repository.DataRepository
import com.example.simpleregistration.utils.state_model.LoadingState

class CreateGuidViewModel(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _uiState = MutableLiveData<LoadingState>()
    var uiState: LiveData<LoadingState> = _uiState

    fun pushGuid(guid: Guid) {
        _uiState.value = LoadingState.Start
        // TOdo: добавить валидацию полей
        dataRepository.pushGuid(guid).addOnCompleteListener {
            if (it.isSuccessful) {
                _uiState.value = LoadingState.Stop
            } else {
                _uiState.value = LoadingState.Error(it.exception?.message.toString())
            }
        }
    }

    fun getUid() = dataRepository.getUid()
}