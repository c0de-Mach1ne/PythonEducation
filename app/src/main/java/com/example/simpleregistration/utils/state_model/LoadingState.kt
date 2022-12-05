package com.example.simpleregistration.utils.state_model

sealed class LoadingState{
    object Start: LoadingState()
    object Stop: LoadingState()
    data class Error(val message: String): LoadingState()
}
