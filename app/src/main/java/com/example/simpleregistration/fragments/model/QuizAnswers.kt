package com.example.simpleregistration.fragments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizAnswers(
    val description: String? = null,
    // TODO тут потенциально могут быть проблемы с файрбейзом, как в прошлый раз меняла название на другое
    val correctFlag: Boolean? = null,
) : Parcelable
