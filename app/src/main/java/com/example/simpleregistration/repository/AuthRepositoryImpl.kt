package com.example.simpleregistration.repository

import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.auth.model.UserSignIn
import com.example.simpleregistration.auth.model.UserSignUp
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AuthRepositoryImpl {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.database.reference

    fun signIn(userSignIn: UserSignIn): Task<AuthResult> =
        firebaseAuth.signInWithEmailAndPassword(userSignIn.email, userSignIn.password)

    fun signUpWithEmailAndPass(userSignUp: UserSignUp): Task<AuthResult> =
        firebaseAuth.createUserWithEmailAndPassword(userSignUp.email, userSignUp.password)

    fun signUpWithPersonalInfo(userPersonalInfo: UserPersonalInfo) {
        firebaseAuth.currentUser?.let { firebaseUser ->
            db.child("users").child(firebaseUser.uid).setValue(
                UserPersonalInfo(
                    name = userPersonalInfo.name,
                    sureName = userPersonalInfo.sureName,
                    patronymic = userPersonalInfo.patronymic,
                    isTeacher = userPersonalInfo.isTeacher,
                )
            )
        }
    }
}