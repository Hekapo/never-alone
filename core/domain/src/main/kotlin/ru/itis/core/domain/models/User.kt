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
    val id: String? = null,
    val age: Long? = null,
    val name: String? = null,
    val city: String? = null,
    val interests: List<String>? = null,
    val sex: String? = null,
    val email: String? = null,
    val phone: String? = null
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
