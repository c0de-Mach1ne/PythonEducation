package com.example.simpleregistration.auth.model

data class UserPersonalInfo(
    val name: String,
    val sureName: String,
    val patronymic: String,
    val isTeacher: Boolean,
)