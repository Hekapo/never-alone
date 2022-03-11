package ru.itis.core.dispathers

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Copyright (c) 11.03.2022 Created by Iskandar
 */


open class DispatchersProvider {
    open val Main: CoroutineContext = Dispatchers.Main
    open val IO: CoroutineContext = Dispatchers.IO
    open val Default: CoroutineContext = Dispatchers.Default
}