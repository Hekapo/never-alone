package ru.itis.core.common

/**
 * Created by Iskandar on 01.06.2022.
 */

infix fun String.buildPath(endPoint: String): String {
    return this.plus("/${endPoint}")
}
