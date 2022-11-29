package com.example.simpleregistration.utils.state_model

sealed class UserRole {
    data class Success(val userRole: Boolean?): UserRole()
    data class Error(val mes: String?): UserRole()
}