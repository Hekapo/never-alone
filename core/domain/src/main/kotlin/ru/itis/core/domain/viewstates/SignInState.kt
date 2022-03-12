package ru.itis.core.domain.viewstates

/**
 * Created by Iskandar on 12.03.2022.
 */

sealed class SignInState {
    object SignInStateNone : SignInState()
    object SignInStateInProcess : SignInState()
    object SignInStateSuccess : SignInState()
    class SignInStateError(val message: String?) : SignInState()
}
