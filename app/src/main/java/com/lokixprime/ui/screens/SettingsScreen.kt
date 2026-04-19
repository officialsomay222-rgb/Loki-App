package com.lokixprime.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.theme.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lokixprime.viewmodel.ChatViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.lokixprime.ui.components.HeaderInfinityLogo

@Composable
fun SettingsScreen(
    isAwakenedMode: Boolean,
    onToggleAwakenedMode: () -> Unit,
    onClose: () -> Unit,
    viewModel: ChatViewModel = viewModel()
) {
    val settings by viewModel.settings.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isAwakenedMode) Color(0xFF050508) else BackgroundDark)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = LokiIcons.X,
                        contentDescription = "Close",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Text(
                    text = "System Settings",
                    color = Color.White,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 16.dp).weight(1f)
                )

                HeaderInfinityLogo(modifier = Modifier.size(width = 40.dp, height = 20.dp))
            }

            HorizontalDivider(color = Color.White.copy(alpha = 0.05f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                // Profile Section
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White.copy(alpha=0.1f), CircleShape)
                    ) {
                        AsyncImage(
                            model = settings.avatarUrl,
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = settings.commanderName,
                        color = Color.White,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Text(
                        text = settings.commanderEmail,
                        color = Color.Gray,
                        fontFamily = Inter,
                        fontSize = 12.sp
                    )
                }

                // AI CONFIGURATION
                SettingsGroup(title = "AI CONFIGURATION") {
                    SettingItem(
                        icon = LokiIcons.Monitor,
                        title = "Model Mode",
                        value = settings.modelMode.uppercase(),
                        showChevron = true
                    )
                    SettingItem(
                        icon = LokiIcons.Zap,
                        title = "Tone",
                        value = settings.tone.uppercase(),
                        showChevron = true
                    )
                    SettingItemWithSwitch(
                        icon = LokiIcons.Zap,
                        title = "Awakened Mode",
                        checked = isAwakenedMode,
                        onCheckedChange = { onToggleAwakenedMode() }
                    )
                }

                // INTERFACE
                SettingsGroup(title = "INTERFACE") {
                    SettingItem(
                        icon = LokiIcons.Monitor,
                        title = "Theme",
                        value = settings.theme.uppercase(),
                        showChevron = true
                    )
                    SettingItem(
                        icon = LokiIcons.Zap, // Placeholder for BubbleStyle
                        title = "Bubble Style",
                        value = settings.bubbleStyle.uppercase(),
                        showChevron = true
                    )
                    SettingItem(
                        icon = LokiIcons.Zap, // Placeholder for FontSize
                        title = "Font Size",
                        value = settings.fontSize.uppercase(),
                        showChevron = true
                    )
                }

                // SYSTEM
                SettingsGroup(title = "SYSTEM") {
                    SettingItemWithSwitch(
                        icon = LokiIcons.Volume2,
                        title = "Sound Effects",
                        checked = settings.soundEnabled,
                        onCheckedChange = { viewModel.updateSettings(settings.copy(soundEnabled = it)) }
                    )
                    SettingItemWithSwitch(
                        icon = LokiIcons.Zap, // Placeholder for Animations
                        title = "Message Animations",
                        checked = settings.messageAnimation,
                        onCheckedChange = { viewModel.updateSettings(settings.copy(messageAnimation = it)) }
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Version Info
                Text(
                    text = "LOKI PRIME X VERSION 1.0.0",
                    color = Color.Gray.copy(alpha = 0.5f),
                    fontFamily = JetBrainsMono,
                    fontSize = 10.sp,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Composable
fun SettingsGroup(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Text(
            text = title,
            color = Color.Gray,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.5.sp,
            modifier = Modifier.padding(bottom = 12.dp, start = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White.copy(alpha = 0.03f))
                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(20.dp))
        ) {
            content()
        }
    }
}

@Composable
fun SettingItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String = "",
    showChevron: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = title,
            color = Color.White,
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp).weight(1f)
        )

        if (value.isNotEmpty()) {
            Text(
                text = value,
                color = Color.Gray,
                fontFamily = Inter,
                fontSize = 15.sp
            )
        }

        if (showChevron) {
            Icon(
                imageVector = LokiIcons.ChevronRight,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.padding(start = 8.dp).size(16.dp)
            )
        }
    }
}

@Composable
fun SettingItemWithSwitch(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = title,
            color = Color.White,
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp).weight(1f)
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color.White,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.DarkGray
            )
        )
    }
}
