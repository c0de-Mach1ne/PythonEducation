package com.example.simpleregistration.fragments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz(
    val id: Int? = null,
    val title: String? = null,
    val questions: List<QuizQuestions>? = null,
): Parcelable
