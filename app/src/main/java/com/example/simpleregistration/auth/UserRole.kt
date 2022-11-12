package com.example.simpleregistration.auth

sealed class UserRole {
    data class Success(val userRole: Boolean?): UserRole()
    data class Error(val mes: String?): UserRole()
}