package com.example.simpleregistration.auth.model

data class UserPersonalInfo(
    val name: String? = null,
    val sureName: String? = null,
    val patronymic: String? = null,
    val teacherFlag: Boolean? = null,
)