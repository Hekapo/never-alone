package ru.itis.user_form

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Created by Iskandar on 27.05.2022.
 */

@Composable
fun UserFormRoute(deps: UserFormDeps) {
    val userFormComponentViewModel = viewModel<UserFormComponentViewModel>(
        factory = UserFormComponentViewModelFactory(deps)
    )

    val userFormViewModel = viewModel<UserFormViewModel>(
        factory = userFormComponentViewModel.userFormingComponent.factory
    )
}
