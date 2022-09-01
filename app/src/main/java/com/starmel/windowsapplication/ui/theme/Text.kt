package com.starmel.windowsapplication.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.starmel.windowsapplication.R


@Suppress("NOTHING_TO_INLINE")
@Composable
inline fun Text(
    text: String,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = 11.sp,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        if (!enabled) {
            BasicText(
                text = text,
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.win)
                    ),
                    color = Color.White,
                    fontSize = fontSize * windowsScale
                ),
                modifier = Modifier.offset(
                    x = 1.winDp,
                    y = 1.winDp
                )
            )
        }
        BasicText(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.win)
                ),
                color = if (enabled) color else WinColor.SomeDarkGray,
                fontSize = fontSize * windowsScale
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun TextPreview() {
    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
}