package ru.itis.user_form.interests

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.ClickableCard
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

@Composable
internal fun InterestsScreenRoute(
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    InterestsScreen(
        onNext = onNext,
        onBack = onBack
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun InterestsScreen(
    onNext: () -> Unit,
    onBack: () -> Unit
) {

    val categories = listOf(
        "Спорт",
        "Фильмы",
        "Петь",
        "Рисовать",
        "Танцы",
        "Музыка",
        "Мемы",
        "Искусство",
        "Путешествия",
        "Соцсети",
        "Пиво",
        "Бег по утрам",
        "Программирование",
        "Лыжи",
        "Готовить",
        "Учеба",
        "Сериалы",
        "Подкасты",
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = AppTheme.colors.backgroundPrimary)
    ) {
        IconButton(
            onClick = {
                onBack()
            },
            content = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = AppTheme.colors.textHighEmphasis
                )
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 22.dp, start = 16.dp),
                text = stringResource(id = R.string.interests),
                style = AppTheme.typography.text36R,
                color = AppTheme.colors.textHighEmphasis,
                textAlign = TextAlign.Start
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                    items(categories.size) { index ->
                        ClickableCard(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .padding(vertical = 6.dp, horizontal = 6.dp),
                            text = categories[index],
                            selectedValue = categories[0],
                            onSelect = {}
                        )
                    }
                }
                Spacer(modifier = Modifier.height(56.dp))

            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                AuthButton(
                    text = stringResource(id = R.string.continue_text),
                    onClick = onNext
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun InterestsScreenPreview() {
    InterestsScreenRoute(onNext = {}, onBack = {})
}
