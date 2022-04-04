package ru.itis.main_screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 02.04.2022.
 */

@Composable
fun ProfileScreenRoute() {

}

@Composable
internal fun ProfileScreen() {

    androidx.compose.material.Surface(
        color = AppTheme.colors.backgroundPrimary,
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                backgroundColor = AppTheme.colors.backgroundPrimary,
                elevation = 2.dp,
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Icon(
                        modifier = Modifier
                            .height(35.dp)
                            .width(50.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.leaves),
                        contentDescription = "",
                        tint = AppTheme.colors.textHighEmphasis
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Profile", style = AppTheme.typography.text28R)
                Spacer(modifier = Modifier.height(24.dp))
                Image(
                    modifier = Modifier
                        .size(152.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable._7163859),
                    contentDescription = "",

                    )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "Name",
                    style = AppTheme.typography.text20M)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "City, Country",
                    style = AppTheme.typography.text20M,
                    color = AppTheme.colors.textMediumEmphasis
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

            }
        }
    }

}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()

}
