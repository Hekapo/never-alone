package ru.itis.main_screen.home.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import ru.itis.main_screen.home.models.HomeAdvModel
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 08.04.2022.
 */

@Composable
fun OneAdvItem(
    model: HomeAdvModel,
    onAdvClick: () -> Unit
) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 16.dp, end = 16.dp)
//                .height(500.dp),
//            elevation = 4.dp,
//            shape = RoundedCornerShape(24.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(500.dp)
//                    .background(
//                        brush = Brush.verticalGradient(
//                            startY = -100f,
//                            endY = 400f,
//                            colors = listOf(
//                                AppTheme.colors.textMediumEmphasis,
//                                Color.Transparent
//                            )
//                        )
//                    ),
//            )
//        }
//    }
//    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
//
//    val gradient = Brush.verticalGradient(
//        colors = listOf(Color.Transparent, Color.Black),
//        startY = sizeImage.height.toFloat() / 3,  // 1/3
//        endY = sizeImage.height.toFloat()
//    )
//
//    Box() {
//        Image(painter = painterResource(id = R.drawable.zqbb6yf3l94),
//            contentDescription = "",
//            modifier = Modifier.onGloballyPositioned {
//                sizeImage = it.size
//            })
//        Box(modifier = Modifier
//            .matchParentSize()
//            .background(gradient))
//    }
//    Image(
//        painter = painterResource(id = R.drawable.zqbb6yf3l94),
//        contentDescription = "",
//        modifier = Modifier.drawWithCache {
//            val gradient = Brush.verticalGradient(
//                colors = listOf(Color.DarkGray, Color.Transparent),
//                startY = size.height / 8,
//                endY = size.height
//            )
//            onDrawWithContent {
//                drawContent()
//
//                drawRect(gradient,blendMode = BlendMode.Multiply)
//            }
//        }
//    )

}

@Preview
@Composable
fun OneAdvItemPreview() = OneAdvItem(
    HomeAdvModel(
        age = 22,
        name = "Alexa",
        city = "Moscow"
    ),
    onAdvClick = {}
)
