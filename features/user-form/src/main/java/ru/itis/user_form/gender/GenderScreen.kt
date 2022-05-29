package ru.itis.user_form.gender

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.components.ClickableCard
import ru.itis.core.ui.theme.AppTheme
import ru.itis.user_form.UserFormViewModel

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

@Composable
internal fun GenderScreenRoute(userFormViewModel: UserFormViewModel) {


    GenderScreen()

}

@Composable
private fun GenderScreen() {

    val cards = listOf("Male", "Female")
    var isSelected by remember {
        mutableStateOf("")
    }

    var selectedOption by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.backgroundPrimary)
            .padding(top = 52.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Я",
            style = AppTheme.typography.text28M,
            textAlign = TextAlign.Start
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 152.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn() {
                itemsIndexed(items = cards) { selected, item ->

                    ClickableCard(
                        modifier = Modifier.fillMaxWidth(),
                        text = item,
                        selectedValue = cards[selectedOption]
                    ) {
                        selectedOption = selected
//                        isSelected = it
                    }
                }
            }
        }


//            ClickableCard(
//                modifier = Modifier.fillMaxWidth(),
//                text = stringResource(id = R.string.woman),
//                isClicked = false
//            ) {
//            }

    }
}

@Preview
@Composable
private fun GenderScreenPreview() {
    GenderScreen()
}
