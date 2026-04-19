package com.lokixprime.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lokixprime.R
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.modifiers.rgbAura
import com.lokixprime.ui.theme.BackgroundDark
import com.lokixprime.ui.theme.LokiCyan
import com.lokixprime.ui.theme.Montserrat

@Composable
fun TopNavigationBar(
    onSettingsClick: () -> Unit,
    onMenuClick: () -> Unit,
    isAwakenedMode: Boolean,
    onAvatarClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(64.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left side: Menu trigger
        IconButton(onClick = onMenuClick) {
            Icon(
                imageVector = LokiIcons.Menu,
                contentDescription = "Menu",
                tint = Color.White
            )
        }

        // Center: Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "LOKI",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = Montserrat,
                fontSize = 18.sp,
                letterSpacing = 4.sp
            )

            HeaderInfinityLogo(modifier = Modifier.size(width = 48.dp, height = 24.dp).padding(horizontal = 8.dp))

            Surface(
                color = LokiCyan.copy(alpha = 0.1f),
                shape = RoundedCornerShape(6.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, LokiCyan.copy(alpha = 0.5f)),
                modifier = Modifier.shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(6.dp),
                    spotColor = LokiCyan.copy(alpha = 0.3f)
                )
            ) {
                Text(
                    text = "PRIME",
                    color = LokiCyan,
                    fontWeight = FontWeight.Black,
                    fontFamily = Montserrat,
                    fontSize = 10.sp,
                    letterSpacing = 2.sp,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        // Right side: Avatar
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            val scale by animateFloatAsState(
                targetValue = if (isPressed) 1.15f else 1f,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                label = "avatarScale"
            )

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .scale(scale)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onAvatarClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isAwakenedMode) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .size(48.dp)
                            .clip(CircleShape)
                            .rgbAura()
                    )
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = BackgroundDark,
                            shape = CircleShape
                        )
                ) {
                    AsyncImage(
                        model = "https://i.ibb.co/ns3LTFwp/Picsart-26-02-28-11-29-26-443.jpg",
                        contentDescription = "Commander Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
