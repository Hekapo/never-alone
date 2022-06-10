package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 04.04.2022 Created by Iskandar
 */

@Composable
fun ImageTopAppBar(
    modifier: Modifier = Modifier,
    centerImageVector: Int,
    menuImageVector: Int? = null,
    onMenuClick: (() -> Unit)? = null,
    backArrow: ImageVector = Icons.Default.ArrowBack,
    backArrowClick: (() -> Unit)? = null
) {
    Surface(elevation = 1.dp) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(AppTheme.colors.backgroundPrimary),

            ) {

            val (icon, menuIcon, arrowBack) = createRefs()

            Box(modifier = Modifier.constrainAs(icon) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = centerImageVector),
                    contentDescription = "",
                    tint = AppTheme.colors.textHighEmphasis
                )
            }
            menuImageVector?.let {
                Box(modifier = Modifier.constrainAs(menuIcon) {
                    end.linkTo(parent.end, margin = 8.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }, contentAlignment = Alignment.CenterEnd) {
                    if (onMenuClick != null) {
                        IconButton(
                            content = {
                                AppBarIcon(
                                    imageVector = ImageVector.vectorResource(id = it),
                                    contentDescription = "",
                                    tint = AppTheme.colors.textHighEmphasis,
                                )
                            },
                            onClick = onMenuClick
                        )
                    }
                }
            }
            Box(modifier = Modifier.constrainAs(arrowBack) {
                start.linkTo(parent.start, margin = 8.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }, contentAlignment = Alignment.Center) {
                if (backArrowClick != null) {
                    IconButton(
                        content = {
                            AppBarIcon(
                                imageVector = backArrow,
                                contentDescription = "back",
                                tint = AppTheme.colors.textHighEmphasis
                            )
                        },
                        onClick = backArrowClick
                    )
                }

            }

        }
    }
}

@Composable
private fun AppBarIcon(
    imageVector: ImageVector,
    contentDescription: String,
    tint: Color
) = Icon(imageVector = imageVector, contentDescription = contentDescription, tint = tint)

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ImageTopAppBarPreview() {
    ImageTopAppBar(
        modifier = Modifier,
        centerImageVector = R.drawable.leaves,
        menuImageVector = R.drawable.rows,
        onMenuClick = {}
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ImageTopAppBarWithoutMenuIconPreview() {
    ImageTopAppBar(
        modifier = Modifier,
        centerImageVector = R.drawable.leaves,
        backArrowClick = {}
    )
}
