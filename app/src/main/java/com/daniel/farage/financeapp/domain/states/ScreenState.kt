package com.daniel.farage.financeapp.domain.states

sealed class ScreenState<T> {
    data class Loading<T>(val isLoading: Boolean): ScreenState<T>()
    data class Success<T>(val data: T): ScreenState<T>()
    data class Error<T>(val error: String): ScreenState<T>()
}
