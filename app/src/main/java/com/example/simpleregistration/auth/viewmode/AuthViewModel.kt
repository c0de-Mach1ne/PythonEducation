package com.example.simpleregistration.auth.viewmode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.auth.AuthState
import com.example.simpleregistration.auth.model.UserAuth
import com.example.simpleregistration.repository.AuthRepositoryImpl

class AuthViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableLiveData<AuthState>()
    val uiState: LiveData<AuthState> = _uiState

    fun signIn(email: String, pass: String) {
        authRepositoryImpl.signIn(UserAuth(email, pass)).addOnCompleteListener {
            if (it.isSuccessful) {
                _uiState.value = AuthState.Success
            }else{
                _uiState.value = AuthState.Error(mes = it.exception?.message)
                Log.d("TAG", "${it.exception?.message}")
            }
        }
    }
}
