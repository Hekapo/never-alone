package ru.itis.user_form.gender

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.ClickableCard
import ru.itis.core.ui.theme.AppTheme
import ru.itis.core.ui.utils.toListOfString
import ru.itis.user_form.UserFormViewModel

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

@Composable
internal fun GenderScreenRoute(
    userFormViewModel: UserFormViewModel,
    onNext: () -> Unit,
    onBack: () -> Unit,
) {

    GenderScreen(
        saveGender = { userFormViewModel.setGender(it) },
        onNext = onNext,
        onBack = onBack
    )

}

@Composable
private fun GenderScreen(
    saveGender: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit,
) {
    val context = LocalContext.current

    val options = remember {
        listOf(R.string.man, R.string.woman).toListOfString(context)
    }
    var selectedOption by remember {
        mutableStateOf(0)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = AppTheme.colors.backgroundPrimary)
    ) {
        IconButton(
            onClick = onBack,
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
                text = "Я",
                color = AppTheme.colors.textHighEmphasis,
                style = AppTheme.typography.text36R,
                textAlign = TextAlign.Start
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LazyColumn {
                        itemsIndexed(items = options) { selected: Int, item: String ->
                            ClickableCard(
                                text = item,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)
                                    .height(46.dp),
                                selectedValue = options[selectedOption],
                                onSelect = {
                                    saveGender(it)
                                    selectedOption = selected
                                }
                            )
                        }
                    }
                }
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
private fun GenderScreenPreview() {
    GenderScreen(
        onNext = {},
        onBack = {},
        saveGender = {}
    )
}
