package ru.itis.core

/**
 * Created by Iskandar on 04.04.2022.
 */

interface EventHandler<T> {
    fun obtainEvent(event: T)
}
