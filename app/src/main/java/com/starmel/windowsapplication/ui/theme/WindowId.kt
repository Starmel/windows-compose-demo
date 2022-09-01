package com.starmel.windowsapplication.ui.theme

import androidx.compose.runtime.compositionLocalOf
import com.starmel.windowsapplication.WindowsManager

val LocalWindowId = compositionLocalOf<Long> { 0 }

val LocalWindowsManager = compositionLocalOf<WindowsManager?> { null }