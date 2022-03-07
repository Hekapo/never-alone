package ru.itis.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
fun LoadingScreen(onNavigate: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = coroutineScope) {
        delay(Constants.SPLASH_WAIT_TIME)
        onNavigate()
    }

    Splash()

}

@Composable
internal fun Splash() {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.backgroundPrimary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        ImageItem(painter = painterResource(id = ru.itis.core.ui.R.drawable.ic_loading))
    }
}
