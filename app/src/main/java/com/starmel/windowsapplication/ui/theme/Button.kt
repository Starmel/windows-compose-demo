package com.starmel.windowsapplication.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.sp


enum class ButtonState {
    Regular,
    Pressed,
    Focused,
    Disabled,
}

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val interaction by lastInteractionState(interactionSource)

    val state by remember(interaction) {
        derivedStateOf {
            when {
                !enabled -> ButtonState.Disabled
                interaction is PressInteraction.Press -> ButtonState.Pressed
                interaction is FocusInteraction.Focus -> ButtonState.Focused
                else -> ButtonState.Regular
            }
        }
    }

    Box(
        modifier = modifier
            .size(76.winDp, 24.winDp)
            .graphicsLayer { this.alpha = 0.99f }
            .drawWithCache {
                val pixelSize = 1.winDp.toPx()

                onDrawWithContent {
                    when (state) {
                        ButtonState.Regular -> drawUnpressedBorder(pixelSize)
                        ButtonState.Pressed -> drawPressedBorder(pixelSize)
                        ButtonState.Focused -> drawFocusBorder(pixelSize)
                        ButtonState.Disabled -> drawUnpressedBorder(pixelSize)
                    }
                    drawContent()
                }
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 12.sp, enabled = enabled)
    }
}

@Composable
fun lastInteractionState(interactionSource: InteractionSource): State<Interaction?> {
    val lastInteraction: MutableState<Interaction?> = remember { mutableStateOf(null) }
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            lastInteraction.value = interaction
        }
    }
    return lastInteraction
}