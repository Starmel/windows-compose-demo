package com.starmel.windowsapplication.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope

fun ContentDrawScope.drawUnpressedBorder(pixelSize: Float) {

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

    // Black line
    drawRect(
        color = WinColor.Black,
        topLeft = Offset(0f, size.height - pixelSize - pixelSize),
        size = Size(width = size.width - pixelSize - pixelSize, height = pixelSize)
    )
    drawRect(
        color = WinColor.Black,
        topLeft = Offset(size.width - pixelSize * 2, 0f),
        size = Size(width = pixelSize, height = size.height - pixelSize)
    )

    // Light Gray Line
    drawRect(
        color = WinColor.GrayMain,
        topLeft = Offset(pixelSize * 2, pixelSize * 2),
        size = Size(width = size.width - pixelSize * 4, height = pixelSize)
    )
    drawRect(
        color = WinColor.GrayMain,
        topLeft = Offset(pixelSize * 2, pixelSize * 2),
        size = Size(width = pixelSize, height = size.height - pixelSize * 4)
    )

    // Dark Gray Line
    drawRect(
        color = WinColor.GraySecondary,
        topLeft = Offset(pixelSize * 2, size.height - pixelSize * 3),
        size = Size(width = size.width - pixelSize * 4, height = pixelSize)
    )
    drawRect(
        color = WinColor.GraySecondary,
        topLeft = Offset(size.width - pixelSize * 3, pixelSize * 2),
        size = Size(width = pixelSize, height = size.height - pixelSize * 4)
    )

    // Remove outline
    drawRect(
        color = Color.Transparent,
        topLeft = Offset(0f, 0f),
        size = Size(width = size.width, height = pixelSize),
        blendMode = BlendMode.Clear
    )
    drawRect(
        color = Color.Transparent,
        topLeft = Offset(0f, size.height - pixelSize),
        size = Size(width = size.width, height = pixelSize),
        blendMode = BlendMode.Clear
    )
    drawRect(
        color = Color.Transparent,
        topLeft = Offset(0f, 0f),
        size = Size(width = pixelSize, height = size.height),
        blendMode = BlendMode.Clear
    )
    drawRect(
        color = Color.Transparent,
        topLeft = Offset(size.width - pixelSize, 0f),
        size = Size(width = pixelSize, height = size.height),
        blendMode = BlendMode.Clear
    )

    // Gray fill Background

    drawRect(
        color = WinColor.ButtonBackgroundGray,
        topLeft = Offset(pixelSize * 3, pixelSize * 3),
        size = Size(width = size.width - pixelSize * 6, height = size.height - pixelSize * 6)
    )
}

fun ContentDrawScope.drawPressedBorder(pixelSize: Float) {

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

    // Black line
    drawRect(
        color = WinColor.Black,
        topLeft = Offset(0f, size.height - pixelSize - pixelSize),
        size = Size(width = size.width - pixelSize - pixelSize, height = pixelSize)
    )
    drawRect(
        color = WinColor.Black,
        topLeft = Offset(size.width - pixelSize * 2, 0f),
        size = Size(width = pixelSize, height = size.height - pixelSize)
    )

    // Light Gray Line
    drawRect(
        color = WinColor.GrayMain,
        topLeft = Offset(pixelSize * 2, pixelSize * 2),
        size = Size(width = size.width - pixelSize * 4, height = pixelSize)
    )
    drawRect(
        color = WinColor.GrayMain,
        topLeft = Offset(pixelSize * 2, pixelSize * 2),
        size = Size(width = pixelSize, height = size.height - pixelSize * 4)
    )

    // Dark Gray Line
    drawRect(
        color = WinColor.GraySecondary,
        topLeft = Offset(pixelSize * 2, size.height - pixelSize * 3),
        size = Size(width = size.width - pixelSize * 4, height = pixelSize)
    )
    drawRect(
        color = WinColor.GraySecondary,
        topLeft = Offset(size.width - pixelSize * 3, pixelSize * 2),
        size = Size(width = pixelSize, height = size.height - pixelSize * 4)
    )

    // Remove outline
    drawRect(
        color = Color.Black,
        topLeft = Offset(0f, 0f),
        size = Size(width = size.width, height = pixelSize),
    )
    drawRect(
        color = Color.Black,
        topLeft = Offset(0f, size.height - pixelSize),
        size = Size(width = size.width, height = pixelSize),
    )
    drawRect(
        color = Color.Black,
        topLeft = Offset(0f, 0f),
        size = Size(width = pixelSize, height = size.height),
    )
    drawRect(
        color = Color.Black,
        topLeft = Offset(size.width - pixelSize, 0f),
        size = Size(width = pixelSize, height = size.height),
    )

    // Gray fill Background

    drawRect(
        color = WinColor.ButtonBackgroundGray,
        topLeft = Offset(pixelSize * 3, pixelSize * 3),
        size = Size(width = size.width - pixelSize * 6, height = size.height - pixelSize * 6)
    )
}

fun ContentDrawScope.drawFocusBorder(pixelSize: Float) {

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

    // Black line
    drawRect(
        color = WinColor.Black,
        topLeft = Offset(0f, size.height - pixelSize - pixelSize),
        size = Size(width = size.width - pixelSize - pixelSize, height = pixelSize)
    )
    drawRect(
        color = WinColor.Black,
        topLeft = Offset(size.width - pixelSize * 2, 0f),
        size = Size(width = pixelSize, height = size.height - pixelSize)
    )

    // Light Gray Line
    drawRect(
        color = WinColor.GrayMain,
        topLeft = Offset(pixelSize * 2, pixelSize * 2),
        size = Size(width = size.width - pixelSize * 4, height = pixelSize)
    )
    drawRect(
        color = WinColor.GrayMain,
        topLeft = Offset(pixelSize * 2, pixelSize * 2),
        size = Size(width = pixelSize, height = size.height - pixelSize * 4)
    )

    // Dark Gray Line
    drawRect(
        color = WinColor.GraySecondary,
        topLeft = Offset(pixelSize * 2, size.height - pixelSize * 3),
        size = Size(width = size.width - pixelSize * 4, height = pixelSize)
    )
    drawRect(
        color = WinColor.GraySecondary,
        topLeft = Offset(size.width - pixelSize * 3, pixelSize * 2),
        size = Size(width = pixelSize, height = size.height - pixelSize * 4)
    )

    // Remove outline
    drawRect(
        color = Color.Black,
        topLeft = Offset(0f, 0f),
        size = Size(width = size.width, height = pixelSize),
    )
    drawRect(
        color = Color.Black,
        topLeft = Offset(0f, size.height - pixelSize),
        size = Size(width = size.width, height = pixelSize),
    )
    drawRect(
        color = Color.Black,
        topLeft = Offset(0f, 0f),
        size = Size(width = pixelSize, height = size.height),
    )
    drawRect(
        color = Color.Black,
        topLeft = Offset(size.width - pixelSize, 0f),
        size = Size(width = pixelSize, height = size.height),
    )

    // Gray fill Background

    drawRect(
        color = WinColor.ButtonBackgroundGray,
        topLeft = Offset(pixelSize * 3, pixelSize * 3),
        size = Size(width = size.width - pixelSize * 6, height = size.height - pixelSize * 6)
    )

    val stepsX = (size.width - pixelSize * 8) / pixelSize
    val stepsY = (size.height - pixelSize * 9) / pixelSize

    for (i in 0..stepsX.toInt()) {
        if (i % 2 == 0) {
            drawRect(
                color = Color.Black,
                topLeft = Offset(i * pixelSize + pixelSize * 4, pixelSize * 5),
                size = Size(width = pixelSize, height = pixelSize),
            )
            drawRect(
                color = Color.Black,
                topLeft = Offset(i * pixelSize + pixelSize * 4, size.height - pixelSize * 5),
                size = Size(width = pixelSize, height = pixelSize),
            )
        }
    }

    for (i in 0..stepsY.toInt()) {
        if (i % 2 == 0) {
            drawRect(
                color = Color.Black,
                topLeft = Offset(pixelSize * 4, pixelSize * 5 + i * pixelSize),
                size = Size(width = pixelSize, height = pixelSize),
            )
            drawRect(
                color = Color.Black,
                topLeft = Offset(size.width - pixelSize * 4, pixelSize * 5 + i * pixelSize),
                size = Size(width = pixelSize, height = pixelSize),
            )
        }
    }
}