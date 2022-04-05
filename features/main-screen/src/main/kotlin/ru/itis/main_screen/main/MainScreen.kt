package ru.itis.main_screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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

    MainScreen(deps = deps, viewState, onRouteChange = mainViewModel::onRouteChange)
}

@Composable
private fun MainScreen(
    deps: MainDeps,
    viewState: MainBottomScreen,
    onRouteChange: (MainBottomScreen) -> Unit
) {

    Column {
        Box(modifier = Modifier.weight(1f)) {
            when (viewState) {
                MainBottomScreen.Home -> {}
                MainBottomScreen.Messenger -> {
                    MessengerScreenRoute(deps = deps)
                }
                MainBottomScreen.Profile -> {
                    ProfileScreenRoute(deps = deps, onMenuClick = {})
                }
            }
        }

        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
        ) {

            AnimatedBottomBar(currentRoute = viewState.route) {
                onRouteChange(it)
            }
        }
    }
}

//@Preview
//@Composable
//private fun MainScreenPreview() = MainScreen()
