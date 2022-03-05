package ru.itis.neveralone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import ru.itis.core.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NeverAloneApp() }
    }

    @Composable
    private fun NeverAloneApp() {
        AppTheme {

        }
    }
}

