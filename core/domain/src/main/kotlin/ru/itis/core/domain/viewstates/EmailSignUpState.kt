package ru.itis.core.domain.viewstates

/**
 * Created by Iskandar on 24.03.2022.
 */

sealed class EmailSignUpState {
    object None : EmailSignUpState()
    object InProcess : EmailSignUpState()
    class Error(val message: String?) : EmailSignUpState()
    object Complete : EmailSignUpState()
}
