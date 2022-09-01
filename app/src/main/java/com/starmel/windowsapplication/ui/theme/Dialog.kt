package com.starmel.windowsapplication.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WindowDialog() {
    Window(
        "Dialog",
        trayEnabled = false,
        fullscreenEnabled = false
    ) {
        Spacer(Modifier.size(16.winDp))
        Text(
            "The NVSVC.EXE file is linked to missing export USER32.DLL:EnumdisplayDevicesA.",
            modifier = Modifier.padding(
                horizontal = 16.winDp,
            )
        )
        Spacer(Modifier.size(16.winDp))
        Row(
            Modifier.padding(
                horizontal = 16.winDp,
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            Button("Cancel") {}
            Spacer(Modifier.size(6.winDp))
            Button("OK") {

            }
        }
        Spacer(Modifier.size(10.winDp))
    }
}

@Composable
fun ErrorDialog() {
    Window(
        "Dialog",
        trayEnabled = false,
        fullscreenEnabled = false
    ) {
        Spacer(Modifier.size(16.winDp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(Modifier.size(16.winDp))
            Image(
                painter = rememberWindowIconPainter(com.starmel.windowsapplication.R.drawable.msg_error_0),
                contentDescription = null,
                modifier = Modifier.size(32.winDp),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.size(19.winDp))
            Text("Buffer overflow.")
            Spacer(Modifier.size(16.winDp))
        }
        Spacer(Modifier.size(16.winDp))
        Row(
            Modifier.padding(
                horizontal = 16.winDp,
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            Button("OK") {

            }
        }
        Spacer(Modifier.size(10.winDp))
    }
}


@Composable
@Preview(showBackground = false)
private fun WindowDialogPreview() {
    Box(Modifier.background(Color.Red)) {
        WindowDialog()

        ErrorDialog()
    }
}