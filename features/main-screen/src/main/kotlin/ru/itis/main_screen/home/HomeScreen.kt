package ru.itis.main_screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.main_screen.home.models.HomeEvent
import ru.itis.main_screen.home.models.HomeViewState
import ru.itis.main_screen.home.views.display.HomeViewDisplayUsers

/**
 * Created by Iskandar on 08.04.2022.
 */

@Composable
internal fun HomeScreenRoute(
    deps: HomeDeps
) {

    val homeComponentViewModel = viewModel<HomeComponentViewModel>(
        factory = HomeComponentViewModelFactory(deps)
    )

    val homeViewModel = viewModel<HomeViewModel>(
        factory = homeComponentViewModel.homeComponentViewModel.homeViewModelFactory
    )

    val viewState = homeViewModel.homeViewState.collectAsState()

    when (val state = viewState.value) {
        HomeViewState.Loading -> {}
        HomeViewState.Error -> {}
        HomeViewState.NoInternet -> {}
        is HomeViewState.Display -> {
            HomeViewDisplayUsers(viewState = state, fetchMoreUsers = { homeViewModel.fetchMoreUsers() })
        }
    }
    LaunchedEffect(key1 = viewState, block = {
        homeViewModel.obtainEvent(event = HomeEvent.EnterScreen)
    })
}

@Preview
@Composable
private fun HomeScreenPreview() {

}
