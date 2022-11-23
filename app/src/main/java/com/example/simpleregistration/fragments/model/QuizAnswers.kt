package com.example.simpleregistration.fragments.model

data class QuizAnswers(
    val description: String? = null,
    // TODO тут потенциально могут быть проблемы с файрбейзом, как в прошлый раз меняла название на другое
    val isCorrect: Boolean? = null,
)
