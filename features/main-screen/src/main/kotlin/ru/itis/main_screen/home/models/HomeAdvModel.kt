package ru.itis.main_screen.home.models

import androidx.compose.runtime.Stable
import ru.itis.core.domain.models.User

/**
 * Created by Iskandar on 08.04.2022.
 */

@Stable
data class HomeAdvModel(
    val id: String? = "",
    val age: Long,
    val name: String?,
    val city: String,
    val interests: List<String>,
    val sex: String = "",
    val email: String? = "",
    val phone: String? = ""
)

fun User.fromUser(): HomeAdvModel {
    return HomeAdvModel(
        this.id,
        this.age, this.name, this.city, this.interests, this.sex, this.email, this.phone
    )
}
