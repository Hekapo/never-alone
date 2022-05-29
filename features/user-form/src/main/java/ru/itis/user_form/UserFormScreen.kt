package ru.itis.user_form

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import ru.itis.core.domain.models.User
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.theme.AppTheme
import ru.itis.user_form.add_photo.AddPhotoScreen
import ru.itis.user_form.birth.BirthScreenRoute
import ru.itis.user_form.gender.GenderScreenRoute
import ru.itis.user_form.interests.InterestsScreen

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

    UserFormScreen(
        uiState = uiState,
        userState = userState,
        userFormViewModel = userFormViewModel,
        toMainScreen = toMainScreen
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun UserFormScreen(
    uiState: UserFormScreens,
    userState: User,
    userFormViewModel: UserFormViewModel?,
    toMainScreen: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        IconButton(
            onClick = {
                userFormViewModel?.navigateBack()
            },
            content = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = AppTheme.colors.textHighEmphasis
                )
            }
        )
        if (userFormViewModel != null) {
            when (uiState) {
                is UserFormScreens.BirthScreen -> {
                    BirthScreenRoute(userState, keyboardController, userFormViewModel)
                }
                is UserFormScreens.GenderScreen -> {
                    GenderScreenRoute(userFormViewModel)
                }
                is UserFormScreens.InterestsScreen -> {
                    InterestsScreen()
                }
                is UserFormScreens.AddPhotoScreen -> {
                    AddPhotoScreen()
                }
                is UserFormScreens.None -> {
                    toMainScreen()
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            AuthButton(
                modifier = Modifier.padding(16.dp),
                text = stringResource(id = R.string.continue_text)
            ) {
                userFormViewModel?.navigateForward()
            }
        }
    }

    BackHandler(enabled = true) {
        userFormViewModel?.navigateBack()
    }
}

@SuppressLint("ProduceStateDoesNotAssignValue")
@Preview
@Composable
private fun UserFormScreenBirthPreview() {
    UserFormScreen(
        uiState = UserFormScreens.BirthScreen,
        userState = User(),
        userFormViewModel = null,
        toMainScreen = {}
    )
}


@SuppressLint("ProduceStateDoesNotAssignValue")
@Preview
@Composable
private fun UserFormScreenGenderPreview() {
    UserFormScreen(
        uiState = UserFormScreens.GenderScreen,
        userState = User(),
        userFormViewModel = null,
        toMainScreen = {}
    )
}

@SuppressLint("ProduceStateDoesNotAssignValue")
@Preview
@Composable
private fun UserFormScreenInterestsPreview() {
    UserFormScreen(
        uiState = UserFormScreens.InterestsScreen,
        userState = User(),
        userFormViewModel = null,
        toMainScreen = {}
    )
}

@SuppressLint("ProduceStateDoesNotAssignValue")
@Preview
@Composable
private fun UserFormScreenAddPhotoPreview() {
    UserFormScreen(
        uiState = UserFormScreens.AddPhotoScreen,
        userState = User(),
        userFormViewModel = null,
        toMainScreen = {}
    )
}
