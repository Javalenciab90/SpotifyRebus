package com.java90apps.spotifyrebus.domain.models

import kotlin.coroutines.CoroutineContext

data class CoroutineContextProvider(
    val mainContext: CoroutineContext,
    val backgroundContext: CoroutineContext
)