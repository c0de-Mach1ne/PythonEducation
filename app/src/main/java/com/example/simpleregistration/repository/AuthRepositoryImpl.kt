package com.example.simpleregistration.repository

import com.example.simpleregistration.auth.model.UserAuth
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImpl /*: AuthRepository */ {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun signIn(userAuth: UserAuth): Task<AuthResult> =
        firebaseAuth.signInWithEmailAndPassword(userAuth.email, userAuth.password)

//    override fun signUp(userAuth: UserAuth) {
//        TODO("Not yet implemented")
//    }
}