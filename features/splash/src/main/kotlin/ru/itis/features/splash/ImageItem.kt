package ru.itis.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
internal fun ImageItem(
    modifier: Modifier = Modifier,
    painter: Painter
) {
    Image(
        modifier = modifier.size(196.dp),
        painter = painter,
        contentDescription = "Logo Icon",
    )
}
