package com.example.simpleregistration.fragments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizQuestions(
    val id: Int? = null,
    val questionText: String? = null,
    val answers: List<QuizAnswers>? = null,
): Parcelable
