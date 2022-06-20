package ru.itis.main_screen.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 02.04.2022.
 */

@Composable
internal fun ProfileScreenRoute(
    deps: ProfileDeps,
) {

    val profileComponentViewModel = viewModel<ProfileComponentViewModel>(
        factory = ProfileComponentViewModelFactory(deps)
    )

    val profileViewModel = viewModel<ProfileViewModel>(
        factory = profileComponentViewModel.profileComponent.profileViewModelFactory
    )

    ProfileScreen()

}

@Composable
internal fun ProfileScreen(
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                modifier = Modifier
                    .size(152.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape),
                painter = painterResource(id = R.drawable._7163859),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "Name",
                style = AppTheme.typography.text20M
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "City, Country",
                style = AppTheme.typography.text20M,
                color = AppTheme.colors.textMediumEmphasis
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ProfileRowInfo(
                title = stringResource(id = R.string.full_name),
                mainText = "",
            )
            ProfileRowInfo(
                title = stringResource(id = R.string.email_text),
                mainText = "",
            )
            ProfileRowInfo(
                title = stringResource(id = R.string.social_url),
                mainText = "",
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ProfileScreenPreview() = ProfileScreen()
