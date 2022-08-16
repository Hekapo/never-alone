package ru.itis.main_screen.home.views.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.theapache64.twyper.Twyper
import com.github.theapache64.twyper.rememberTwyperController
import ru.itis.core.ui.theme.AppTheme
import ru.itis.main_screen.home.models.HomeAdvModel
import ru.itis.main_screen.home.models.HomeViewState
import ru.itis.main_screen.home.views.OneAdvItem

/**
 * Created by Iskandar on 02.06.2022.
 */

@Composable
internal fun HomeViewDisplayUsers(
    viewState: HomeViewState.Display,
    fetchMoreUsers: () -> Unit
) {
    val items = remember {
        mutableStateListOf(*viewState.items.toTypedArray())
    }

    val twyperController = rememberTwyperController()

    Box(modifier = Modifier.background(color = AppTheme.colors.backgroundPrimary)) {
        Twyper(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .padding(8.dp),
            twyperController = twyperController,
            paddingBetweenCards = 20f,
            items = viewState.items,
            onItemRemoved = { item, direction ->
                items.remove(item)
            },
            onEmpty = {
//                fetchMoreUsers()
            }
        ) {
            OneAdvItem(
                model = it,
                onLike = {
                    twyperController.swipeRight()
                },
                onClose = {
                    twyperController.swipeLeft()
                    items.remove(it)
                },
                onAdvClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun HomeViewDisplayUsersPreview(
) {
    HomeViewDisplayUsers(
        viewState = HomeViewState.Display(
            items = listOf(
                HomeAdvModel(
                    age = 19,
                    name = "cat",
                    city = "Kazan",
                    interests = listOf("mouse", "meat", "sleep")
                ),
                HomeAdvModel(
                    age = 20,
                    name = "cat2",
                    city = "Kazan",
                    interests = listOf("birds", "meat", "sleep")
                ),
            )
        ),
        fetchMoreUsers = {}
    )
}
