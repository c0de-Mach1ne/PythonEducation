package com.example.simpleregistration.fragments.guid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.fragments.repository.DataRepository
import com.example.simpleregistration.utils.state_model.Loading

class CreateGuidViewModel(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _uiState = MutableLiveData<Loading>()
    var uiState: LiveData<Loading> = _uiState

    fun pushGuid(guid: Guid) {
        _uiState.value = Loading.Start
        // TOdo: добавить валидацию полей
        dataRepository.pushGuid(guid).addOnCompleteListener {
            if (it.isSuccessful) {
                _uiState.value = Loading.Stop
            } else {
                _uiState.value = Loading.Error(it.exception?.message.toString())
            }
        }
    }

    fun getUid() = dataRepository.getUid()
}