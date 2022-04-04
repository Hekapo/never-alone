package ru.itis.main_screen.main.destinations

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector
import ru.itis.core.ui.R

/**
 * Created by Iskandar on 03.04.2022.
 */

@Stable
internal sealed class MainBottomScreen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Home : MainBottomScreen("home", R.string.title_home, Icons.Outlined.Home)
    object Messenger : MainBottomScreen("messenger", R.string.title_messenger, Icons.Outlined.Menu)
    object Profile : MainBottomScreen("profile", R.string.title_profile, Icons.Outlined.Person)
}
