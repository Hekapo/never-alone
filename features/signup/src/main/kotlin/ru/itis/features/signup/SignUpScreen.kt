@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalPagerApi::class)

package ru.itis.features.signup

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme
import ru.itis.core.ui.utils.EmailPassData
import ru.itis.features.signup.email.EmailRoute
import ru.itis.features.signup.phone.PhoneRoute
import ru.itis.features.signup.utils.Constants.PAGE_COUNT
import ru.itis.features.signup.utils.Constants.PAGE_EMAIL
import ru.itis.features.signup.utils.Constants.PAGE_PHONE

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

@Composable
fun SignUpRoute(
    deps: SignUpDeps,
    onNextWithEmailClick: (EmailPassData) -> Unit,
    onNextWithPhoneClick: () -> Unit,
    onBackClick: () -> Unit,
    onTextSignInClick: () -> Unit
) {

    val signUpComponentViewModel = viewModel<SignUpComponentViewModel>(
        factory = SignUpComponentViewModelFactory(deps)
    )

    val signUpViewModel = viewModel<SignUpViewModel>(
        factory = signUpComponentViewModel.signUpComponent.getViewModelFactory()
    )

    val uiState by signUpViewModel.signUpUIState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(uiState.signUpProcess.signUpSuccess) {
        if (uiState.signUpProcess.signUpSuccess) {
            delay(300)
            onBackClick()
        }
    }

    SignUpScreen(
        uiState = uiState,
        onTextSignInClick = onTextSignInClick,
        onBackClick = onBackClick,
        onTabSelected = signUpViewModel::onTabSelected,
        onPhoneRoute = {
            PhoneRoute(
                uiState = uiState,
                onNextClick = {
                    signUpViewModel.onSendCodeClick(context as ComponentActivity).also {
                        onNextWithPhoneClick()
                    }
                },
                onPhoneChange = signUpViewModel::onPhoneChange
            )
        },
        onEmailRoute = {
            EmailRoute(
                uiState = uiState,
                onNextClick = onNextWithEmailClick,
                onEmailChange = signUpViewModel::onEmailChange
            )
        }
    )
}

@Composable
private fun SignUpScreen(
    uiState: SignUpUIState,
    onTextSignInClick: () -> Unit,
    onBackClick: () -> Unit,
    onTabSelected: (Int) -> Unit,
    onPhoneRoute: @Composable () -> Unit,
    onEmailRoute: @Composable () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val pagerState = rememberPagerState(initialPage = uiState.activeTab)

    LaunchedEffect(key1 = uiState.activeTab) {
        pagerState.animateScrollToPage(uiState.activeTab)
    }

    BackHandler(enabled = uiState.activeTab != PAGE_PHONE) {
        onTabSelected(PAGE_PHONE)
    }

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        IconButton(
            onClick = {
                keyboardController?.hide()
                onBackClick()
            },
            content = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = AppTheme.colors.textHighEmphasis
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.app_name),
                style = AppTheme.typography.text36R,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.textHighEmphasis
            )
            Spacer(modifier = Modifier.height(24.dp))
            Tabs(
                modifier = Modifier.fillMaxWidth(),
                pagerState = pagerState,
                onTabClicked = onTabSelected
            )
            SignUpMethodPager(
                pagerState = pagerState,
                onPhoneRoute = onPhoneRoute,
                onEmailRoute = onEmailRoute
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Row {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = stringResource(id = R.string.have_an_account),
                    color = AppTheme.colors.textMediumEmphasis,
                    style = AppTheme.typography.text14M
                )
                Text(
                    modifier = Modifier.clickable(
                        role = Role.Button,
                        onClick = onTextSignInClick
                    ),
                    text = stringResource(id = R.string.signin),
                    color = AppTheme.colors.textHighEmphasis,
                    style = AppTheme.typography.text14M
                )

            }
        }

    }
}

@Composable
private fun Tabs(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onTabClicked: (Int) -> Unit
) {
    TabRow(
        modifier = modifier,
        backgroundColor = AppTheme.colors.backgroundPrimary,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        repeat(PAGE_COUNT) { index ->
            when (index) {
                PAGE_PHONE -> {
                    Tab(
                        text = {
                            Text(
                                text = stringResource(R.string.phone),
                                style = AppTheme.typography.text14R,
                                color = AppTheme.colors.textLowEmphasis
                            )
                        },
                        selected = pagerState.currentPage == index,
                        onClick = { onTabClicked(PAGE_PHONE) },
                    )
                }
                PAGE_EMAIL -> {
                    Tab(
                        text = {
                            Text(
                                text = stringResource(R.string.email),
                                style = AppTheme.typography.text14R,
                                color = AppTheme.colors.textLowEmphasis
                            )
                        },
                        selected = pagerState.currentPage == index,
                        onClick = { onTabClicked(PAGE_EMAIL) },
                    )
                }
            }
        }
    }
}

@Composable
private fun SignUpMethodPager(
    pagerState: PagerState,
    onPhoneRoute: @Composable () -> Unit,
    onEmailRoute: @Composable () -> Unit
) {
    HorizontalPager(
        count = PAGE_COUNT,
        state = pagerState,
        itemSpacing = 8.dp
    ) { page ->
        when (page) {
            PAGE_PHONE -> {
                onPhoneRoute()
            }
            PAGE_EMAIL -> {
                onEmailRoute()
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignUpPreview() {
    SignUpScreen(
        uiState = SignUpUIState(),
        onTextSignInClick = {},
        onBackClick = {},
        onTabSelected = {},
        onEmailRoute = {},
        onPhoneRoute = {}
    )
}
