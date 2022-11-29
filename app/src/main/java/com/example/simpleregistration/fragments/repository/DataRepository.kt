package com.example.simpleregistration.fragments.repository

import com.example.simpleregistration.fragments.model.Guid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DataRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.database.reference

    fun getGuid() = db.child("Courses").get()

    fun getQuiz() = db.child("Quizzes").get()

    fun pushGuid(guid: Guid) = db.child("Courses").child(guid.id.toString()).setValue(guid)

    fun getUid() = auth.currentUser?.uid
}
