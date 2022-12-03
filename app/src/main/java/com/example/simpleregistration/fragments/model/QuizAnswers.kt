package com.example.simpleregistration.fragments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizAnswers(
    val description: String? = null,
    val correctFlag: Boolean? = null,
) : Parcelable
