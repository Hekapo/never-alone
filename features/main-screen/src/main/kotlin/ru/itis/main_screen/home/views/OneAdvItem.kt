package ru.itis.main_screen.home.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.itis.core.ui.theme.AppTheme
import ru.itis.core.ui.utils.Gradients
import ru.itis.main_screen.home.models.HomeAdvModel

/**
 * Created by Iskandar on 08.04.2022.
 */

@Composable
fun OneAdvItem(
    model: HomeAdvModel,
    onClose: () -> Unit,
    onLike: () -> Unit,
    onAdvClick: () -> Unit
) {
    var isInterestsVisible by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onAdvClick()
            }
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (name, age, interests, like, hide, image, bottomBar) = createRefs()
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom, margin = 40.dp)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)

                    },
                contentScale = ContentScale.Crop,
                painter = painterResource(id = ru.itis.core.ui.R.drawable.sad_cat),
                contentDescription = ""
            )

            Text(
                modifier = Modifier.constrainAs(name) {
                    start.linkTo(image.start, margin = 16.dp)
                    bottom.linkTo(bottomBar.top, margin = 8.dp)

                },
                text = model.name,
                color = AppTheme.colors.backgroundOnSecondary,
                style = AppTheme.typography.text28M
            )
            Text(
                modifier = Modifier.constrainAs(age) {
                    start.linkTo(name.end, margin = 4.dp)
                    bottom.linkTo(bottomBar.top, margin = 9.dp)

                },
                text = model.age.toString(),
                color = AppTheme.colors.backgroundOnSecondary,
                style = AppTheme.typography.text20M
            )

            Row(
                modifier = Modifier
                    .background(
                        brush = Gradients.blackGradient
                    )
                    .padding(bottom = 8.dp)
                    .height(104.dp)
                    .fillMaxWidth()
                    .constrainAs(bottomBar) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                IconButton(
                    modifier = Modifier
                        .size(48.dp),
                    onClick = { onClose() }
                ) {
                    Image(
                        painter = painterResource(id = ru.itis.core.ui.R.drawable.close),
                        contentDescription = ""
                    )
                }
                IconButton(
                    modifier = Modifier
                        .size(48.dp),
                    onClick = { onLike() }
                ) {
                    Image(
                        painter = painterResource(id = ru.itis.core.ui.R.drawable.heart),
                        contentDescription = ""
                    )
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(interests) {
                    top.linkTo(bottomBar.top)
                    start.linkTo(parent.start, margin = 16.dp)
                }) {
                repeat(3) {
                    InterestsCard(text = model.interests[it])
                }
            }
        }
    }
}

@Composable
private fun InterestsCard(text: String) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 8.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Color.DarkGray.copy(0.8f)),
        contentAlignment = Alignment.Center,

        ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
            text = text,
            color = Color.White,
            style = AppTheme.typography.text16R
        )
    }
}

@Preview
@Composable
private fun InterestsCardPreview() {
    InterestsCard(text = "photo")

}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OneAdvItemPreview() {
    Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()) {
        OneAdvItem(
            HomeAdvModel(
                age = 22,
                name = "Alexa",
                city = "Moscow",
                interests = listOf("Photo", "Reading", "Sport")
            ),
            onAdvClick = {},
            onClose = {},
            onLike = {}
        )
    }
}
