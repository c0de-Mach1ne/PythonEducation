package com.example.simpleregistration.fragments.model

data class QuizQuestions(
    val id: Int? = null,
    val questionText: String? = null,
    val answers: List<QuizAnswers>? = null,
)
