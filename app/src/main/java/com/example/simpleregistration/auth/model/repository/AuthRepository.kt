package com.example.simpleregistration.auth.model.repository

import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.auth.model.UserSignIn
import com.example.simpleregistration.auth.model.UserSignUp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.database.reference

    fun signIn(userSignIn: UserSignIn) =
        firebaseAuth.signInWithEmailAndPassword(userSignIn.email, userSignIn.password)

    fun signUpWithEmailAndPass(userSignUp: UserSignUp) =
        firebaseAuth.createUserWithEmailAndPassword(userSignUp.email, userSignUp.password)

    fun signUpWithPersonalInfo(userPersonalInfo: UserPersonalInfo) =
        firebaseAuth.currentUser?.let { firebaseUser ->
            db.child("users").child(firebaseUser.uid).setValue(
                UserPersonalInfo(
                    name = userPersonalInfo.name,
                    sureName = userPersonalInfo.sureName,
                    patronymic = userPersonalInfo.patronymic,
                    teacherFlag = userPersonalInfo.teacherFlag,
                )
            )
        }

    fun getDatabaseUser() =
        firebaseAuth.uid?.let { uid ->
            db.child("users").child(uid).get()
        }

    fun getAuthUser() = firebaseAuth.currentUser

    fun signOut() =
        firebaseAuth.signOut()
}