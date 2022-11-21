package com.example.simpleregistration.auth.viewmode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.auth.AuthState
import com.example.simpleregistration.auth.UserRole
import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.auth.model.UserSignIn
import com.example.simpleregistration.auth.model.UserSignUp
import com.example.simpleregistration.auth.model.repository.AuthRepositoryImpl

class AuthViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl,
) : ViewModel() {

    private val _uiState = MutableLiveData<AuthState>()
    val uiState: LiveData<AuthState> = _uiState
    private val _userRole = MutableLiveData<UserRole>()
    var userRole: LiveData<UserRole> = _userRole



    fun signIn(email: String, pass: String) {
        authRepositoryImpl.signIn(UserSignIn(email, pass)).addOnCompleteListener {
            if (it.isSuccessful) {
                getUserRole()
            } else _userRole.value = UserRole.Error(mes = it.exception?.message)
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



    private fun getUserRole() {
        authRepositoryImpl.getDatabaseUser()?.addOnCompleteListener { dataSnapshotTask ->
            if (dataSnapshotTask.isSuccessful) {
                _userRole.value =
                    UserRole.Success(dataSnapshotTask.result.getValue(UserPersonalInfo::class.java)?.teacherFlag)
            }
        }
    }
}
