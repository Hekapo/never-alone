package ru.itis.features.splash

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.itis.core.ui.theme.AppTheme
import ru.itis.neveralone.MainActivity
import ru.itis.neveralone.navigation.Destination

/**
 * Created by Iskandar on 16.03.2022.
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavHostController

    @Test
    fun splash_screen_displays() = runTest {
        composeTestRule.setContent {
            AppTheme {
                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Destination.SplashDestination.key
                ) {
                    composable(Destination.SplashDestination.key) {
                        LoadingScreen {
                            navController.navigate(Destination.ChooseLoginMethod.key)
                        }

                    }
                }
            }
        }
        composeTestRule
            .onNodeWithContentDescription(label = "Logo Icon")
            .assertExists()
    }

}
