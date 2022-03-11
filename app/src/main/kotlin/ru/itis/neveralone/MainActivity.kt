package ru.itis.neveralone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import ru.itis.core.ui.theme.AppTheme
import ru.itis.neveralone.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NeverAloneApp() }
    }

    @Composable
    private fun NeverAloneApp() {
        AppTheme {
            ProvideWindowInsets(consumeWindowInsets = true) {
                val navController = rememberNavController()
                AppNavGraph(
                    navController = navController,
                    appComponent = (application as App).appComponent
                )
            }

        }
    }
}

