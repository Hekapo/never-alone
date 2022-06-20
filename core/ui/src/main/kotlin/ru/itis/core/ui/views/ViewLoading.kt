package ru.itis.core.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.statusBarsPadding
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 04.04.2022.
 */

@Composable
fun ViewLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = AppTheme.colors.textMediumEmphasis
        )
    }
}

@Preview
@Composable
fun ViewLoadingPreview() = ViewLoading()
