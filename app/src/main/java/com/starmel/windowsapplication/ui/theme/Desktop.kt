@file:OptIn(InternalCoroutinesApi::class)

package com.starmel.windowsapplication.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

val LocalDesktop: ProvidableCompositionLocal<DesktopState?> = staticCompositionLocalOf { null }

class DesktopState {

    val activeWindowIdState = MutableStateFlow(0L)
}

@Composable
fun Desktop(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color(0xff0E6E6E))
            .padding(8.winDp)
    ) {
        CompositionLocalProvider(
            LocalDesktop provides DesktopState()
        ) {
            content(this)
        }
    }
}

private var windowsCount = 0

fun nextWindowZ() = synchronized(DesktopState::class) {
    (windowsCount++).toFloat()
}

