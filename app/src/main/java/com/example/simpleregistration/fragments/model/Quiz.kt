package com.example.simpleregistration.fragments.model

data class Quiz(
    val id: Int? = null,
    val title: String? = null,
    val questions: List<QuizQuestions>? = null,
)
