package com.starmel.windowsapplication.ui.theme

import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.get
import com.starmel.windowsapplication.R

@Composable
fun rememberWindowIconPainter(@DrawableRes resId: Int): Painter {

    val resources = LocalContext.current.resources

    return remember {

        val bitmap = BitmapFactory.decodeResource(resources, resId)

        val scale = 2f

        val image = ImageBitmap(
            width = bitmap.width * scale.toInt(),
            height = bitmap.height * scale.toInt()
        )

        Canvas(image).apply {

            val paint = Paint().apply {
                isAntiAlias = false
            }

            for (x in 0 until bitmap.width) {
                for (y in 0 until bitmap.height) {

                    paint.color = Color(bitmap[x, y])

                    drawRect(
                        Rect(
                            Offset(x * scale, y * scale),
                            Size(scale, scale)
                        ),
                        paint
                    )
                }
            }
            save()
        }

        BitmapPainter(image = image)
    }
}

@Composable
fun WindowsIcon(@DrawableRes resId: Int) {
    Image(
        painter = rememberWindowIconPainter(resId),
        contentDescription = null,
    )
}

@Composable
@Preview(showBackground = true)
private fun IconPreview() {
    Image(
        painter = rememberWindowIconPainter(R.drawable.netmeeting_0),
        contentDescription = null,
    )
}