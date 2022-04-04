package ru.itis.main_screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.main_screen.main.destinations.MainBottomScreen
import ru.itis.main_screen.profile.ProfileScreen

/**
 * Created by Iskandar on 02.04.2022.
 */

@Composable
fun MainScreenRoute() {


}

@Composable
private fun MainScreen() {
    var routes by remember { mutableStateOf<MainBottomScreen>(MainBottomScreen.Home) }

    Column {
        Box(modifier = Modifier.weight(1f)) {
            when (routes) {
                MainBottomScreen.Home -> {}
                MainBottomScreen.Messenger -> {}
                MainBottomScreen.Profile -> {
                    ProfileScreen()
                }
            }
        }

        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
        ) {
            AnimatedBottomBar(currentRoute = routes.route) {
                routes = it
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() = MainScreen()
