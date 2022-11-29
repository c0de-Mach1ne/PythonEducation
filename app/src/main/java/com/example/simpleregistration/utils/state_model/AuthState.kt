package com.example.simpleregistration.utils.state_model


sealed class AuthState {
    object Success : AuthState()
    data class Error(val mes: String?) : AuthState()
}