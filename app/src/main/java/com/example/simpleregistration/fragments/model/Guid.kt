package com.example.simpleregistration.fragments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Guid(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val urlReference: String? = null,
): Parcelable