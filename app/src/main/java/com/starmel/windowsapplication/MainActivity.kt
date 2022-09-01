package com.starmel.windowsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.starmel.windowsapplication.ui.theme.*
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Desktop {
                Box(contentAlignment = Alignment.Center) {

                    val windowsManager = remember { WindowsManager() }

                    CompositionLocalProvider(
                        LocalWindowsManager provides windowsManager
                    ) {
                        windowsManager.windows.forEach {
                            it.value()
                        }
                    }

                    LaunchedEffect(Unit) {
                        windowsManager.createWindow {
                            ErrorDialog()
                        }
                        windowsManager.createWindow {
                            WindowDialog()
                        }
                        windowsManager.createWindow {
                            WindowDialog()
                        }
                    }
                }
            }
        }
    }
}

class WindowsManager {

    val windows: SnapshotStateMap<Long, @Composable () -> Unit> = mutableStateMapOf()

    fun createWindow(block: @Composable () -> Unit) {
        val windowId = Random.nextLong()

        windows[windowId] = @Composable {
            CompositionLocalProvider(LocalWindowId provides windowId) {
                block.invoke()
            }
        }
    }

    fun removeWindow(windowId: Long) {
        windows.remove(windowId)
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Column {
        Greeting("Android")
        Spacer(Modifier.height(32.winDp))
        Button("OK") {}
        Spacer(Modifier.height(16.winDp))
        Button("Cancel") {
        }
        Spacer(Modifier.height(16.winDp))
    }
}