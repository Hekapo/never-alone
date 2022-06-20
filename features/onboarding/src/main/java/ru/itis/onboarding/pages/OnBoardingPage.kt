package ru.itis.onboarding.pages

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import ru.itis.core.ui.R


/**
 * Created by Iskandar on 26.05.2022.
 */

@Stable
sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
) {
    object First : OnBoardingPage(
        image = R.drawable.leaves,
        title = R.string.app_name,
        description = R.string.onboarding_first
    )

    object Second : OnBoardingPage(
        image = R.drawable.people_in_love,
        title = R.string.meet,
        description = R.string.onboarding_second
    )

    object Third : OnBoardingPage(
        image = R.drawable.leaves,
        title = R.string.be_bold,
        description = R.string.onboarding_third
    )
}
