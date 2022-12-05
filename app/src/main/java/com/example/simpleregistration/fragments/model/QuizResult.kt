package com.example.simpleregistration.fragments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val name: String? = null,
    val result: Int? = null,
) : Parcelable
