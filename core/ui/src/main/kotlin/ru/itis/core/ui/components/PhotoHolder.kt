package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 29.05.2022.
 */

@Composable
fun PhotoHolder(
    onClick: () -> Unit,
    image: String = ""
) {

    val context = LocalContext.current
    Card(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .padding(4.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .size(height = 152.dp, width = 100.dp)
                .background(color = AppTheme.colors.textFieldOnPrimary)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true),
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            if (image.isBlank()) {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(12.dp))
                        .size(20.dp)
                        .background(color = AppTheme.colors.buttonOnPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "",
                        tint = AppTheme.colors.backgroundOnSecondary
                    )

                }
            } else {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(context)
                        .data(Uri.parse(image))
                        .build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PhotoHolderPreview() {
    PhotoHolder(onClick = {}, image = "")
}
