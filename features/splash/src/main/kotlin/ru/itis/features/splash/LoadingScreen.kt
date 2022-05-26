package ru.itis.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
fun LoadingScreen(
    deps: LoadingDeps,
    toMainScreen: (showOnBoarding: Boolean) -> Unit,
    toRegistration: () -> Unit
) {
    val loadingComponentViewModel = viewModel<LoadingComponentViewModel>(
        factory = LoadingComponentViewModelFactory(deps = deps)
    )

    val loadingViewModel = viewModel<LoadingViewModel>(
        factory = loadingComponentViewModel.loadingComponent.viewModeFactory
    )

    val navigationState by loadingViewModel.navigateMain.collectAsState()

    LaunchedEffect(key1 = navigationState) {
        delay(Constants.SPLASH_WAIT_TIME)
        if (navigationState) {
            toMainScreen(false)
        } else {
            toRegistration()
        }
    }

    Splash()
}

@Composable
private fun Splash() {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.backgroundPrimary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        ImageItem(painter = painterResource(id = ru.itis.core.ui.R.drawable.ic_loading))
    }
}

@Preview
@Composable
fun SplashPreview() {
    Splash()

}
