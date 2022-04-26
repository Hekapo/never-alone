package ru.itis.main_screen.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.itis.core.ui.R
import ru.itis.core.ui.components.ImageTopAppBar
import ru.itis.core.ui.theme.AppTheme
import ru.itis.main_screen.components.AnimatedBottomBar
import ru.itis.main_screen.home.HomeScreenRoute
import ru.itis.main_screen.main.destinations.MainBottomScreen
import ru.itis.main_screen.main.destinations.asTitle
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

    val childNavController = rememberNavController()

    MainScreen(
        childNavController = childNavController,
        onHomeRoute = { HomeScreenRoute(deps = deps) },
        onMessengerRoute = { MessengerScreenRoute(deps = deps) },
        onProfileRoute = { ProfileScreenRoute(deps = deps) }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainScreen(
    childNavController: NavHostController,
    onHomeRoute: @Composable () -> Unit,
    onMessengerRoute: @Composable () -> Unit,
    onProfileRoute: @Composable () -> Unit,
) {
    val navBackStackEntry by childNavController.currentBackStackEntryAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.White)
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Hello I am BottomSheet",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppTheme.colors.backgroundPrimary)
            ) {
                ImageTopAppBar(
                    centerImageVector = R.drawable.leaves,
                    menuImageVector = if (navBackStackEntry?.destination?.route == MainBottomScreen.Profile.route)
                        R.drawable.rows else null,
                    onMenuClick = {
                        coroutineScope.launch {
                            bottomState.show()
                        }
                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    navBackStackEntry?.destination?.route?.asTitle(context)?.let {
                        Text(
                            text = it,
                            style = AppTheme.typography.text28R,
                            color = AppTheme.colors.textHighEmphasis
                        )
                    }
                }
            }
            Box(modifier = Modifier.weight(1f)) {
                NavHost(
                    navController = childNavController,
                    startDestination = MainBottomScreen.Home.route
                ) {

                    composable(route = MainBottomScreen.Home.route) {
                        onHomeRoute()
                    }

                    composable(route = MainBottomScreen.Messenger.route) {
                        onMessengerRoute()
                    }

                    composable(route = MainBottomScreen.Profile.route) {
                        onProfileRoute()
                    }
                }
            }

            Box(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
            ) {

                AnimatedBottomBar(
                    navController = childNavController,
                    navBackStackEntry = navBackStackEntry,
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenProfilePreview() =
    MainScreen(
        onHomeRoute = {},
        onMessengerRoute = {},
        onProfileRoute = {},
        childNavController = rememberNavController()
    )
