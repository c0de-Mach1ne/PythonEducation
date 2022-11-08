package com.example.simpleregistration.repository

import com.example.simpleregistration.auth.model.UserAuth

interface AuthRepository {
    fun signIn(userAuth: UserAuth)
    fun signUp(userAuth: UserAuth)
    fun isUserAuthenticated()
}