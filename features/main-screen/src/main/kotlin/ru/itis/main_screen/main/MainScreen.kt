package ru.itis.main_screen.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.core.ui.R
import ru.itis.core.ui.components.ImageTopAppBar
import ru.itis.core.ui.theme.AppTheme
import ru.itis.main_screen.components.AnimatedBottomBar
import ru.itis.main_screen.main.destinations.MainBottomScreen
import ru.itis.main_screen.messenger.MessengerScreenRoute
import ru.itis.main_screen.profile.ProfileScreenRoute

/**
 * Created by Iskandar on 02.04.2022.
 */

@Composable
fun MainScreenRoute(deps: MainDeps) {

    val mainComponentViewModel = viewModel<MainComponentViewModel>(
        factory = MainComponentViewModelFactory(deps)
    )

    val mainViewModel = viewModel<MainViewModel>(
        factory = mainComponentViewModel.mainComponent.mainViewModel
    )

    val viewState by mainViewModel.navigation.collectAsState()

    MainScreen(
        viewState = viewState,
        onRouteChange = mainViewModel::onRouteChange,
        onHomeRoute = {},
        onMessengerRoute = { MessengerScreenRoute(deps = deps) },
        onProfileRoute = { ProfileScreenRoute(deps = deps) }
    )
}

@Composable
private fun MainScreen(
    viewState: MainBottomScreen,
    onRouteChange: (MainBottomScreen) -> Unit,
    onHomeRoute: @Composable () -> Unit,
    onMessengerRoute: @Composable () -> Unit,
    onProfileRoute: @Composable () -> Unit,
) {

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.backgroundPrimary)
        ) {
            ImageTopAppBar(
                centerImageVector = R.drawable.leaves,
                menuImageVector = if (viewState is MainBottomScreen.Profile)
                    R.drawable.rows else null,
                onMenuClick = {}
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = viewState.resourceId),
                    style = AppTheme.typography.text28R,
                    color = AppTheme.colors.textHighEmphasis
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            when (viewState) {
                MainBottomScreen.Home -> onHomeRoute()
                MainBottomScreen.Messenger -> onMessengerRoute()
                MainBottomScreen.Profile -> onProfileRoute()
            }
        }

        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
        ) {

            AnimatedBottomBar(currentRoute = viewState) {
                onRouteChange(it)
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenProfilePreview() =
    MainScreen(
        viewState = MainBottomScreen.Profile,
        onRouteChange = {},
        onHomeRoute = {},
        onMessengerRoute = {},
        onProfileRoute = {}
    )

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenMessengerPreview() =
    MainScreen(
        viewState = MainBottomScreen.Messenger,
        onRouteChange = {},
        onHomeRoute = {},
        onMessengerRoute = {},
        onProfileRoute = {}
    )
