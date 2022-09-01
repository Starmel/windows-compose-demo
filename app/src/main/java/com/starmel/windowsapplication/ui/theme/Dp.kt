package com.starmel.windowsapplication.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp

const val windowsScale = 1.5f

@Stable
inline val Int.winDp: Dp get() = Dp(value = this.toFloat() * windowsScale)

@Stable
inline val Double.winDp: Dp get() = Dp(value = this.toFloat() * windowsScale)