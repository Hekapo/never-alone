package ru.itis.core.domain.viewstates

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

sealed class ResultState<out T : Any, out E : Any> {
    object None : ResultState<Nothing, Nothing>()
    object InProcess : ResultState<Nothing, Nothing>()
    data class Error<E : Any>(val message: E?) : ResultState<Nothing, E>()
    data class Success<T : Any>(val data: T) : ResultState<T, Nothing>()
}
