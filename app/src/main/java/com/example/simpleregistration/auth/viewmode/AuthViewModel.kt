package com.example.simpleregistration.auth.viewmode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.auth.AuthState
import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.auth.model.UserSignIn
import com.example.simpleregistration.auth.model.UserSignUp
import com.example.simpleregistration.auth.model.repository.AuthRepositoryImpl

class AuthViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl,
) : ViewModel() {

    private val _uiState = MutableLiveData<AuthState>()
    val uiState: LiveData<AuthState> = _uiState

    fun signIn(email: String, pass: String) {
        authRepositoryImpl.signIn(UserSignIn(email, pass)).addOnCompleteListener {
            if (it.isSuccessful) _uiState.value = AuthState.Success
            else _uiState.value = AuthState.Error(mes = it.exception?.message)
        }
    }

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
    ) {
        authRepositoryImpl.signUpWithEmailAndPass(
            UserSignUp(email, password)
        ).addOnCompleteListener {
            if (it.isSuccessful) _uiState.value = AuthState.Success
            else _uiState.value = AuthState.Error(mes = it.exception?.message)
        }
    }

    fun signUpWithPersonalInfo(
        name: String,
        sureName: String,
        patronymic: String,
        isTeacher: Boolean,
    ) {
        authRepositoryImpl.signUpWithPersonalInfo(UserPersonalInfo(
            name,
            sureName,
            patronymic,
            isTeacher)
        )?.addOnCompleteListener {
            if (it.isSuccessful) _uiState.value = AuthState.Success
            else _uiState.value = AuthState.Error(mes = it.exception?.message)
        }
    }

    fun checkUser() {
        authRepositoryImpl.getAuthUser()?.addOnSuccessListener {
            Log.d("TAG", "value ${it.value}")
            Log.d("TAG", "isTeacher ${it.getValue(UserPersonalInfo::class.java)?.teacherFlag} \n")
            Log.d("TAG", "name ${it.getValue(UserPersonalInfo::class.java)?.name} \n")
            Log.d("TAG", "sureName ${it.getValue(UserPersonalInfo::class.java)?.sureName} \n")
            Log.d("TAG", "patronymic ${it.getValue(UserPersonalInfo::class.java)?.patronymic} \n")
        }
    }
}
