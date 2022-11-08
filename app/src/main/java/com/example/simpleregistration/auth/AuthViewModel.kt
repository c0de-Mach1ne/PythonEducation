package com.example.simpleregistration.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.auth.model.UserAuth
import com.example.simpleregistration.repository.AuthRepositoryImpl

class AuthViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {

//    private val authRepositoryImpl = AuthRepositoryImpl()

    private val _isLoading = MutableLiveData<Boolean>()
    var isLoading = _isLoading
    private val _errorMes = MutableLiveData<String?>()
    var errorMes = _errorMes
    private val _isSuccess = MutableLiveData<Boolean>()
    var isSuccess = _isSuccess

    fun signIn(email: String, pass: String) {
        _isLoading.value = true
        authRepositoryImpl.signIn(UserAuth(email, pass)).addOnCompleteListener {
            if (it.isSuccessful) {
                _isSuccess.value = true
            }
        }.addOnFailureListener {
            errorMes.value = it.message
        }
    }
}
