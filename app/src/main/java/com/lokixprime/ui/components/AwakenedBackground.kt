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
fun AwakenedBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF08080C))
        )
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
