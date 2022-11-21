package com.example.simpleregistration.main_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.auth.AuthState
import com.example.simpleregistration.auth.model.repository.AuthRepositoryImpl

class MainViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl,
) : ViewModel() {

    private val _userState = MutableLiveData<AuthState>()
    var userState: LiveData<AuthState> = _userState

    init {
        authSignInUser()
    }

    private fun authSignInUser() {
        if (authRepositoryImpl.getAuthUser() != null) {
            _userState.value = AuthState.Success
        } else {
            _userState.value = AuthState.Error(mes = "Welcome!")
        }
    }
}