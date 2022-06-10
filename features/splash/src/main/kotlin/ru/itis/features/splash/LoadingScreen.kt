package ru.itis.features.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
fun LoadingScreen(
    deps: LoadingDeps,
    toMainScreen: () -> Unit,
    toRegistration: () -> Unit
) {
    val loadingComponentViewModel = viewModel<LoadingComponentViewModel>(
        factory = LoadingComponentViewModelFactory(deps = deps)
    )

    val loadingViewModel = viewModel<LoadingViewModel>(
        factory = loadingComponentViewModel.loadingComponent.viewModeFactory
    )

    val navigationState by loadingViewModel.navigateMain.collectAsState()

    var startAnim by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = navigationState) {
        startAnim = true
        delay(Constants.SPLASH_WAIT_TIME)
        if (navigationState) {
            toMainScreen()
        } else {
            toRegistration()
        }
    }

    Splash(startAnim = startAnim)
}

@Composable
private fun Splash(startAnim: Boolean) {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.backgroundPrimary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = startAnim,
            enter = fadeIn(animationSpec = tween(1500)),
        ) {
            Icon(
                modifier = Modifier.size(196.dp),
                painter = painterResource(id = ru.itis.core.ui.R.drawable.ic_loading),
                tint = AppTheme.colors.textHighEmphasis,
                contentDescription = "Intro Logo"
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SplashPreview() = Splash(startAnim = false)
