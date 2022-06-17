package ru.itis.user_form

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.itis.core.domain.models.User
import ru.itis.user_form.add_photo.AddPhotoScreenRoute
import ru.itis.user_form.add_photo.PickPhotoMethodScreenRoute
import ru.itis.user_form.birth.BirthScreenRoute
import ru.itis.user_form.gender.GenderScreenRoute
import ru.itis.user_form.interests.InterestsScreenRoute

/**
 * Created by Iskandar on 27.05.2022.
 */

@Composable
fun UserFormRoute(deps: UserFormDeps, toMainScreen: () -> Unit) {
    val userFormComponentViewModel = viewModel<UserFormComponentViewModel>(
        factory = UserFormComponentViewModelFactory(deps)
    )

    val userFormViewModel = viewModel<UserFormViewModel>(
        factory = userFormComponentViewModel.userFormingComponent.factory
    )

    val uiState by userFormViewModel.userFormScreen.collectAsState()
    val userState by userFormViewModel.userInfo.collectAsState()
    val context = LocalContext.current

    val childNavController = rememberNavController()


    UserFormScreen(
        childNavController = childNavController,
        userState = userState,
        userFormViewModel = userFormViewModel,
        toMainScreen = toMainScreen
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun UserFormScreen(
    userState: User,
    userFormViewModel: UserFormViewModel,
    childNavController: NavHostController,
    toMainScreen: () -> Unit,
) {
    val navBackStackEntry by childNavController.currentBackStackEntryAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavHost(
            navController = childNavController,
            startDestination = UserFormScreens.BirthScreen.route
        ) {
            composable(route = UserFormScreens.BirthScreen.route) {
                BirthScreenRoute(
                    user = userState,
                    userFormViewModel = userFormViewModel,
                    onNext = { childNavController.navigate(UserFormScreens.GenderScreen.route) },
                )
            }

            composable(route = UserFormScreens.GenderScreen.route) {
                GenderScreenRoute(
                    userFormViewModel = userFormViewModel,
                    onNext = {
                        childNavController.navigate(UserFormScreens.InterestsScreen.route)
                    },
                    onBack = { childNavController.popBackStack() }
                )
            }

            composable(route = UserFormScreens.InterestsScreen.route) {
                InterestsScreenRoute(
                    onNext = {
                        childNavController.navigate(UserFormScreens.AddPhotoScreen.route)
                    },
                    onBack = { childNavController.popBackStack() }
                )
            }

            composable(
                route = UserFormScreens.AddPhotoScreen.route,
            ) {
                AddPhotoScreenRoute(
                    userFormViewModel = userFormViewModel,
                    onNext = {
                        userFormViewModel.updateUserData()
                        toMainScreen()
                    },
                    onBack = { childNavController.popBackStack() },
                    onPickPhoto = {
                        childNavController.navigate(UserFormScreens.PickPhotoMethodScreen.route)
                    }
                )
            }

            composable(
                route = UserFormScreens.PickPhotoMethodScreen.route
            ) {
                PickPhotoMethodScreenRoute(
                    userFormViewModel = userFormViewModel,
                    onBack = {
                        childNavController.popBackStack()
                    }
                )
            }
        }
    }
}
