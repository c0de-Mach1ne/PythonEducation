package com.example.simpleregistration.utils.state_model

sealed class UiState {
    data class Success(val data: String): UiState()
    data class Error(val mes: String) : UiState()
}
