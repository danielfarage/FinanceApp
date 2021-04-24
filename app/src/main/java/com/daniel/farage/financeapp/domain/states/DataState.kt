package com.daniel.farage.financeapp.domain.states

import java.lang.Exception

sealed class DataState<T> {
    data class Success<T>(val data: T): DataState<T>()
    data class Failure<T>(val error: Exception): DataState<T>()
}
