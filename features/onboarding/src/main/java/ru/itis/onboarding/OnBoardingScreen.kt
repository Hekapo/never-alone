@file:OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)

package ru.itis.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.*
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.theme.AppTheme
import ru.itis.onboarding.pages.OnBoardingPage

/**
 * Created by Iskandar on 26.05.2022.
 */

@Composable
fun OnBoardingRoute(
    deps: OnBoardingDeps,
    onFinishClick: () -> Unit
) {

    val onBoardingComponentViewModel = viewModel<OnBoardingComponentViewModel>(
        factory = OnBoardingComponentViewModelFactory(deps)
    )

    val onBoardingViewModel = viewModel<OnBoardingViewModel>(
        factory = onBoardingComponentViewModel.onBoardingComponent.factory
    )

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            onBoardingViewModel.saveOnBoardingState(completed = true)
            onFinishClick()
        }
    }
}

@Composable
private fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Pager Image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = onBoardingPage.title),
            style = AppTheme.typography.text36R,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.textHighEmphasis
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = stringResource(id = onBoardingPage.description),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.text20M,
            color = AppTheme.colors.textHighEmphasis

        )
    }
}

@Composable
private fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            AuthButton(text = stringResource(id = R.string.fill_out_form)) {
                onClick()
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun SecondOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun ThirdOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}
