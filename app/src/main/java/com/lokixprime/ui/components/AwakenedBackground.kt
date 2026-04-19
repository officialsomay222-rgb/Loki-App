package com.lokixprime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lokixprime.ui.modifiers.glowPulse
import com.lokixprime.ui.theme.LokiCyan
import com.lokixprime.ui.theme.RgbPurple

@Composable
fun AwakenedBackground(
    isAwakened: Boolean = true,
    bgStyle: String = "nebula"
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isAwakened) Color(0xFF050508) else Color(0xFF08080C))
        )
        if (isAwakened) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .glowPulse(color = LokiCyan)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .glowPulse(color = RgbPurple)
            )
        }
    }
}
