package com.example.simpleregistration.auth.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleregistration.utils.state_model.AuthState
import com.example.simpleregistration.utils.state_model.UserRole
import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.auth.model.UserSignIn
import com.example.simpleregistration.auth.model.UserSignUp
import com.example.simpleregistration.auth.model.repository.AuthRepository

class AuthViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableLiveData<AuthState>()
    val uiState: LiveData<AuthState> = _uiState
    private val _userRole = MutableLiveData<UserRole>()
    var userRole: LiveData<UserRole> = _userRole

    fun signIn(email: String, pass: String) {
        authRepository.signIn(UserSignIn(email, pass)).addOnCompleteListener {
            if (it.isSuccessful) {
                getUserRole()
            } else _userRole.value = UserRole.Error(mes = it.exception?.message)
        }
    }

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
    ) {
        authRepository.signUpWithEmailAndPass(
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
        authRepository.signUpWithPersonalInfo(UserPersonalInfo(
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
        authRepository.getDatabaseUser()?.addOnCompleteListener { dataSnapshotTask ->
            if (dataSnapshotTask.isSuccessful) {
                _userRole.value =
                    UserRole.Success(dataSnapshotTask.result.getValue(UserPersonalInfo::class.java)?.teacherFlag)
            }
        }
    }
}
