package ru.itis.main_screen.home.views.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.theapache64.twyper.Twyper
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
    var data = remember {
        mutableStateOf(viewState.items.toMutableList())
    }

    Box(modifier = Modifier.background(color = AppTheme.colors.backgroundPrimary)) {
        Twyper(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .padding(8.dp),
            paddingBetweenCards = 20f,
            items = viewState.items,
            onItemRemoved = { item, direction ->
                data.value.remove(item)

            },
            onEmpty = {
                fetchMoreUsers()

            }) {
            OneAdvItem(
                model = it,
                onLike = {},
                onClose = {},
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
                    name = "Kamilla",
                    city = "Kazan",
                    interests = listOf("Hard sex", "BDSM", "Spanking")
                ),
                HomeAdvModel(
                    age = 20,
                    name = "Kamilla2",
                    city = "Kazan",
                    interests = listOf("2 children", "Cooking", "Washing")
                ),
            )
        ),
        fetchMoreUsers = {}
    )
}
