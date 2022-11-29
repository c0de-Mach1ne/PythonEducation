package com.example.simpleregistration.utils.state_model

sealed class Loading{
    object Start: Loading()
    object Stop: Loading()
    data class Error(val message: String): Loading()
}
