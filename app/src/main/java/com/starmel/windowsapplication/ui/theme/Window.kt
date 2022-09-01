package com.starmel.windowsapplication.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt
import kotlin.random.Random


fun CacheDrawScope.drawWindowBorder(pixelSize: Float): DrawResult {
    return onDrawWithContent {

        // White line
        drawRect(
            color = WinColor.White,
            topLeft = Offset(pixelSize, pixelSize),
            size = Size(width = size.width - pixelSize, height = pixelSize)
        )
        drawRect(
            color = WinColor.White,
            topLeft = Offset(pixelSize, pixelSize),
            size = Size(width = pixelSize, height = size.height - pixelSize)
        )

        // Dark Gray Line
        drawRect(
            color = WinColor.GraySecondary,
            topLeft = Offset(pixelSize, size.height - pixelSize * 2),
            size = Size(width = size.width - pixelSize, height = pixelSize)
        )
        drawRect(
            color = WinColor.GraySecondary,
            topLeft = Offset(size.width - pixelSize * 2, pixelSize * 1),
            size = Size(width = pixelSize, height = size.height - pixelSize * 3)
        )

        // Remove outline
        drawRect(
            color = WinColor.WindowGrayLight,
            topLeft = Offset(0f, 0f),
            size = Size(width = size.width, height = pixelSize),
        )
        drawRect(
            color = WinColor.WindowGrayLight,
            topLeft = Offset(0f, size.height - pixelSize),
            size = Size(width = size.width, height = pixelSize),
        )
        drawRect(
            color = WinColor.WindowGrayLight,
            topLeft = Offset(0f, 0f),
            size = Size(width = pixelSize, height = size.height),
        )
        drawRect(
            color = WinColor.WindowGrayLight,
            topLeft = Offset(size.width - pixelSize, 0f),
            size = Size(width = pixelSize, height = size.height),
        )

        // Gray fill Background

        drawRect(
            color = WinColor.ButtonBackgroundGray,
            topLeft = Offset(pixelSize * 2, pixelSize * 2),
            size = Size(width = size.width - pixelSize * 4, height = size.height - pixelSize * 4)
        )

        // Black line
        drawRect(
            color = WinColor.Black,
            topLeft = Offset(0f, size.height - pixelSize),
            size = Size(width = size.width - pixelSize, height = pixelSize)
        )
        drawRect(
            color = WinColor.Black,
            topLeft = Offset(size.width - pixelSize, 0f),
            size = Size(width = pixelSize, height = size.height)
        )

        drawContent()
    }
}

@Composable
fun Window(
    title: String,
    modifier: Modifier = Modifier,
    trayEnabled: Boolean = true,
    fullscreenEnabled: Boolean = true,
    closeEnabled: Boolean = true,

    onTrayClick: () -> Unit = {},
    onFullscreenClick: () -> Unit = {},
    onCloseClick: (() -> Unit)? = null,

    content: @Composable ColumnScope.() -> Unit
) {

    val providedWindowId = LocalWindowId.current.takeIf { it != -1L }

    val windowId: Long = providedWindowId ?: remember { Random.nextLong() }

    val windowZ = remember { nextWindowZ() }

    val desktopState = LocalDesktop.current
    val activeWindowId by desktopState?.activeWindowIdState?.collectAsState() ?: derivedStateOf { 0L }

    val isWindowFocused = desktopState == null || activeWindowId == windowId

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val windowsManager = LocalWindowsManager.current

    WindowSurface(
        modifier = Modifier
            .zIndex(windowZ + if (isWindowFocused) 1000 else 0)
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectTapGestures {
                    desktopState?.activeWindowIdState?.tryEmit(windowId)
                }
            }
    ) {
        Column(
            modifier = modifier.width(IntrinsicSize.Min)
        ) {
            WindowToolbar(
                title = title,
                focused = isWindowFocused,
                trayEnabled = trayEnabled,
                fullscreenEnabled = fullscreenEnabled,
                closeEnabled = closeEnabled,
                onTrayClick = onTrayClick,
                onFullscreenClick = onFullscreenClick,
                onCloseClick = onCloseClick ?: {
                    windowsManager?.removeWindow(windowId); Unit
                },
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                            desktopState?.activeWindowIdState?.tryEmit(windowId)
                        }
                    }
            )
            content.invoke(this)
        }
    }

    LaunchedEffect(windowId) {
        desktopState?.activeWindowIdState?.emit(windowId)
    }
}


@Composable
fun WindowSurface(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .drawWithCache {
                val pixelSize = 1.winDp.toPx()
                drawWindowBorder(pixelSize)
            },
    ) {
        Box(Modifier.padding(3.winDp)) {
            content(this)
        }
    }
}

@Composable
fun WindowToolbar(
    title: String,
    modifier: Modifier = Modifier,
    focused: Boolean = true,
    trayEnabled: Boolean = true,
    fullscreenEnabled: Boolean = true,
    closeEnabled: Boolean = true,
    onTrayClick: () -> Unit = {},
    onFullscreenClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
) {
    Box(
        modifier
            .background(
                Brush.linearGradient(
                    if (focused) {
                        listOf(WinColor.BlueDark, WinColor.BlueLight)
                    } else {
                        listOf(Color(0xff7B7B7B), Color(0xFFADADAD))
                    }
                )
            )
            .fillMaxWidth()
            .padding(1.winDp)
    ) {
        Text(title, color = Color.White)
        Text(title, color = Color.White, modifier = Modifier.offset(x = 0.4.dp))

        Row(Modifier.align(Alignment.CenterEnd)) {
            WindowToolbarButton(WindowToolbarIcon.Tray, trayEnabled, onTrayClick)
            WindowToolbarButton(WindowToolbarIcon.Fullscreen, fullscreenEnabled, onFullscreenClick)
            Spacer(Modifier.size(2.winDp))
            WindowToolbarButton(WindowToolbarIcon.Close, closeEnabled, onCloseClick)
        }
    }
}


