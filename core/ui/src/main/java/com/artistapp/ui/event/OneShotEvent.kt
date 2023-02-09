package com.artistapp.ui.event

import androidx.compose.runtime.Composable
import java.util.*

open class OneShotEvent {
    val id: String = UUID.randomUUID().toString()
}

@Composable
fun <T : OneShotEvent> List<T>.Observe(
    handler: @Composable (T) -> Unit,
    eventConsumed: (id: String) -> Unit
) {
    firstOrNull()?.let {
        handler(it)
        eventConsumed(it.id)
    }
}