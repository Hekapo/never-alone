package ru.itis.neveralone

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import ru.itis.core.ui.theme.AppTheme
import ru.itis.neveralone.navigation.AppNavGraph

/**
 * Copyright (c) 11.04.2022 Created by Iskandar
 */

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LoginScreen() }
    }

    @Composable
    private fun LoginScreen() {
        AppTheme {
            ProvideWindowInsets(consumeWindowInsets = true) {
                val navController = rememberNavController()
                val intent = Intent(this, MainActivity::class.java)
                AppNavGraph(
                    navController = navController,
                    appComponent = (application as App).appComponent,
                    toMainScreen = {
                        startActivity(intent).also {
                            finish()
                        }
                    }
                )
            }
        }
    }
}
