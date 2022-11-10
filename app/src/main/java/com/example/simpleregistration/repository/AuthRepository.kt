package com.example.simpleregistration.repository

import com.example.simpleregistration.auth.model.UserSignUp

interface AuthRepository {
    fun signIn(userSignIn: UserSignUp)
    fun signUp(userSignIn: UserSignUp)
    fun isUserAuthenticated()
}