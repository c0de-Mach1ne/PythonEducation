package com.example.simpleregistration.fragments.repository

import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.fragments.model.Quiz
import com.example.simpleregistration.fragments.model.QuizResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DataRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.database.reference

    fun getGuid() = db.child("Courses").get()

    fun getQuiz() = db.child("Quiz").get()

    fun pushGuid(guid: Guid) = db.child("Courses").child(guid.id.toString()).setValue(guid)

    fun getUid() = auth.currentUser?.uid

    fun getDatabaseUser() =
        auth.uid?.let { uid ->
            db.child("Users").child(uid).get()
        }

    fun pushQuiz(quiz: Quiz) =
        db.child("Quiz").child("id= ${quiz.id.toString()}").setValue(quiz)

    fun pushResult(result: QuizResult, quizId: String, userIndex: String) =
        db.child("QuizzesLeaderBoard").child(quizId).child(userIndex).setValue(result)

    fun getResultList(quizId: String) =
        db.child("QuizzesLeaderBoard").child("id=$quizId").get()
}
