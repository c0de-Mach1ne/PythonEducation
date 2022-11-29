package com.example.simpleregistration.main_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.utils.state_model.AuthState
import com.example.simpleregistration.auth.model.repository.AuthRepository

class MainViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _userState = MutableLiveData<AuthState>()
    var userState: LiveData<AuthState> = _userState

    init {
        authSignInUser()
    }

    private fun authSignInUser() {
        if (authRepository.getAuthUser() != null) {
            _userState.value = AuthState.Success
        } else {
            _userState.value = AuthState.Error(mes = "Error!")
        }
    }
}