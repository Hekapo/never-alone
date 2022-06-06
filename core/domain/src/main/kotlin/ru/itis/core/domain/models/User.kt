package ru.itis.core.domain.models

import ru.itis.core.database_constants.DatabaseConstants.CHILD_AGE
import ru.itis.core.database_constants.DatabaseConstants.CHILD_CITY
import ru.itis.core.database_constants.DatabaseConstants.CHILD_EMAIL
import ru.itis.core.database_constants.DatabaseConstants.CHILD_ID
import ru.itis.core.database_constants.DatabaseConstants.CHILD_INTERESTS
import ru.itis.core.database_constants.DatabaseConstants.CHILD_PHONE
import ru.itis.core.database_constants.DatabaseConstants.CHILD_SEX
import ru.itis.core.database_constants.DatabaseConstants.CHILD_USERNAME

/**
 * Created by Iskandar on 10.04.2022.
 */

data class User(
    val id: String? = "",
    val age: Long = -1,
    val name: String = "",
    val city: String = "",
    val interests: List<String> = emptyList(),
    val sex: String = "",
    val email: String = "",
    val phone: String = ""
) {
    fun toMap(): Map<String, Any?> = mapOf(
        Pair(CHILD_ID, id),
        Pair(CHILD_USERNAME, name),
        Pair(CHILD_AGE, age),
        Pair(CHILD_EMAIL, email),
        Pair(CHILD_PHONE, phone),
        Pair(CHILD_SEX, sex),
        Pair(CHILD_CITY, city),
        Pair(CHILD_INTERESTS, interests)
    )
}
