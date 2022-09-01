 package com.starmel.windowsapplication.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.tooling.preview.Preview

data class WindowToolbarIcon(
    val matrix: List<List<Int>>,
    val offsetX: Int,
    val offsetY: Int
) {

    companion object {

        val Close = WindowToolbarIcon(
            matrix = listOf(
                listOf(1, 1, 0, 0, 0, 0, 1, 1),
                listOf(0, 1, 1, 0, 0, 1, 1, 0),
                listOf(0, 0, 1, 1, 1, 1, 0, 0),
                listOf(0, 0, 0, 1, 1, 0, 0, 0),
                listOf(0, 0, 1, 1, 1, 1, 0, 0),
                listOf(0, 1, 1, 0, 0, 1, 1, 0),
                listOf(1, 1, 0, 0, 0, 0, 1, 1),
            ),
            offsetX = 4,
            offsetY = 3
        )

        val Tray = WindowToolbarIcon(
            matrix = listOf(
                listOf(1, 1, 1, 1, 1, 1),
                listOf(1, 1, 1, 1, 1, 1),
            ),
            offsetX = 4,
            offsetY = 9
        )

        val Fullscreen = WindowToolbarIcon(
            matrix = listOf(
                listOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                listOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
                listOf(1, 0, 0, 0, 0, 0, 0, 0, 1),
                listOf(1, 0, 0, 0, 0, 0, 0, 0, 1),
                listOf(1, 0, 0, 0, 0, 0, 0, 0, 1),
                listOf(1, 0, 0, 0, 0, 0, 0, 0, 1),
                listOf(1, 0, 0, 0, 0, 0, 0, 0, 1),
                listOf(1, 0, 0, 0, 0, 0, 0, 0, 1),
                listOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
            ),
            offsetX = 3,
            offsetY = 2
        )
    }
}

@Composable
fun WindowToolbarButton(
    icon: WindowToolbarIcon,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val interaction by lastInteractionState(interactionSource)

    Box(Modifier
        .size(16.winDp, 14.winDp)
        .drawWithContent {

            val pixelSize = 1.winDp.toPx()

            when {
                interaction is PressInteraction.Press && enabled -> drawPressedBorder(pixelSize)
                else -> drawBorder(pixelSize)
            }
            if (!enabled) {
                drawMatrix(
                    icon = icon.copy(
                        offsetX = icon.offsetX + 1,
                        offsetY = icon.offsetY + 1
                    ),
                    pixelSize = pixelSize,
                    color = Color.White
                )
            }
            drawMatrix(
                icon = icon,
                pixelSize = pixelSize,
                color = if (enabled) Color.Black else WinColor.SomeDarkGray
            )
        }
        .clickable(interactionSource, null, onClick = onClick)
    ) {

    }
}

@Composable
@Preview(showBackground = true)
private fun WindowCloseButtonPreview() {
    Column {
        Row {
            WindowToolbarButton(WindowToolbarIcon.Tray) {}
            WindowToolbarButton(WindowToolbarIcon.Fullscreen) {}
            WindowToolbarButton(WindowToolbarIcon.Close) {}
        }
        Spacer(Modifier.size(10.winDp))
        Row {
            WindowToolbarButton(WindowToolbarIcon.Tray, enabled = false) {}
            WindowToolbarButton(WindowToolbarIcon.Fullscreen, enabled = false) {}
            WindowToolbarButton(WindowToolbarIcon.Close, enabled = false) {}
        }
    }
}

private fun ContentDrawScope.drawMatrix(
    icon: WindowToolbarIcon,
    pixelSize: Float,
    color: Color
) {
    for (y in icon.matrix.indices) {
        for (x in 0 until icon.matrix[y].size) {
            if (icon.matrix[y][x] == 1) {
                drawRect(
                    color = color,
                    topLeft = Offset(
                        x = x * pixelSize + icon.offsetX * pixelSize,
                        y = y * pixelSize + icon.offsetY * pixelSize
                    ),
                    size = Size(pixelSize, pixelSize)
                )
            }
        }
    }
}

private fun ContentDrawScope.drawBorder(pixelSize: Float) {
    drawRect(
        color = WinColor.White,
        topLeft = Offset(0f, 0f),
        size = Size(width = size.width - pixelSize, height = pixelSize)
    )
    drawRect(
        color = WinColor.White,
        topLeft = Offset(0f, 0f),
        size = Size(width = pixelSize, height = size.height - pixelSize)
    )
    drawRect(
        color = WinColor.WindowGrayLight,
        topLeft = Offset(pixelSize, pixelSize),
        size = Size(width = size.width - pixelSize * 3, height = pixelSize)
    )
    drawRect(
        color = WinColor.WindowGrayLight,
        topLeft = Offset(pixelSize, pixelSize),
        size = Size(width = pixelSize, height = size.height - pixelSize * 3)
    )
    drawRect(
        color = WinColor.SomeDarkGray,
        topLeft = Offset(pixelSize, size.height - pixelSize * 2),
        size = Size(width = size.width - pixelSize, height = pixelSize)
    )
    drawRect(
        color = WinColor.SomeDarkGray,
        topLeft = Offset(size.width - pixelSize * 2, pixelSize * 1),
        size = Size(width = pixelSize, height = size.height - pixelSize * 3)
    )
    drawRect(
        color = WinColor.ButtonBackgroundGray,
        topLeft = Offset(pixelSize * 2, pixelSize * 2),
        size = Size(width = size.width - pixelSize * 4, height = size.height - pixelSize * 4)
    )
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
}