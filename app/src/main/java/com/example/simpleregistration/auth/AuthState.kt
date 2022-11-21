package com.example.simpleregistration.auth


sealed class AuthState {
    object Success : AuthState()
    data class Error(val mes: String?) : AuthState()
}