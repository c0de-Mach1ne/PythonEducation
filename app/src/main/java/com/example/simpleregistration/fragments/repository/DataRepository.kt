package com.example.simpleregistration.fragments.repository

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import io.reactivex.Single

class DataRepository {

    private val db = Firebase.database.reference

    fun getGuid() = db.child("Courses").get()

    fun getQuiz() = db.child("Quizzes").get()
}
