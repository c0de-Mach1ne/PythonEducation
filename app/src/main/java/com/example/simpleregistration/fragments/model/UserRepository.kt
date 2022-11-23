package com.example.simpleregistration.fragments.model

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserRepository {

    private val db = Firebase.database.reference
    var guidCount = 0

    fun getGuidCount() = db.child("Courses").get().addOnCompleteListener {
        guidCount = it.result.childrenCount.toInt()
    }

    fun test(key: Int)=
        db.child("Courses").child("id=$key").get()

    fun getRef() = db.child("Courses")
}
