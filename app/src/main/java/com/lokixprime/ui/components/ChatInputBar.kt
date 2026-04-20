package com.lokixprime.ui.components

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.modifiers.glassmorphism
import com.lokixprime.ui.modifiers.pulsingGlowShadow
import com.lokixprime.ui.modifiers.rgbSpinningBorder
import com.lokixprime.ui.theme.*
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale

@Composable
fun ChatInputBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit,
    isAwakenedMode: Boolean,
    modelMode: String = "fast",
    onModelModeChange: (String) -> Unit = {},
    attachments: List<String> = emptyList(),
    onAddAttachmentClick: () -> Unit = {},
    onRemoveAttachment: (Int) -> Unit = {}
) {
    val context = LocalContext.current
    var isFocused by remember { mutableStateOf(false) }
    var isOptionsOpen by remember { mutableStateOf(false) }
    var isModelMenuOpen by remember { mutableStateOf(false) }
    var isImageMode by remember { mutableStateOf(false) }
    var thinkingMode by remember { mutableStateOf(false) }
    var searchGrounding by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        val outerShape = RoundedCornerShape(32.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .let {
                    if (isAwakenedMode) {
                        it.padding(2.dp).pulsingGlowShadow() // Space for the RGB border and glow
                    } else it
                }
        ) {
            // Main Input Container
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .let {
                        if (isAwakenedMode) {
                            it.glassmorphism(
                                shape = outerShape,
                                backgroundColor = Color(0xFF050505).copy(alpha = 0.9f)
                            )
                            .rgbSpinningBorder(shape = outerShape)
                        } else {
                            it.background(Color.White.copy(alpha = 0.05f), outerShape)
                              .border(1.dp, Color.White.copy(alpha = 0.1f), outerShape)
                        }
                    }
                    .padding(4.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    if (attachments.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            attachments.take(10).forEachIndexed { index, url ->
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                ) {
                                    AsyncImage(
                                        model = url,
                                        contentDescription = "Attachment",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                    Box(
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .background(Color.Black.copy(alpha = 0.5f))
                                            .align(Alignment.TopEnd)
                                            .clickable { onRemoveAttachment(index) },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = LokiIcons.Trash2,
                                            contentDescription = "Remove",
                                            tint = Color.White,
                                            modifier = Modifier.size(12.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    // Text Field
                    TextField(
                        value = text,
                        onValueChange = onTextChange,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = if (isAwakenedMode) Color(0xFF00F2FF) else Color.White,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        placeholder = {
                            Text(
                                text = "Ask AI...",
                                color = Color.Gray,
                                fontFamily = Inter,
                                fontSize = 16.sp
                            )
                        },
                        maxLines = 6,
                        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
                    )

                    // Bottom Actions Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Plus Icon
                        IconButton(onClick = { onAddAttachmentClick() }) {
                            Icon(
                                imageVector = LokiIcons.Plus,
                                contentDescription = "Actions",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Box {
                            IconButton(onClick = { isOptionsOpen = !isOptionsOpen }) {
                                Icon(
                                    imageVector = LokiIcons.SlidersHorizontal,
                                    contentDescription = "Advanced Options",
                                    tint = if (isOptionsOpen || isImageMode || thinkingMode || searchGrounding) Color.White else Color.Gray,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            DropdownMenu(
                                expanded = isOptionsOpen,
                                onDismissRequest = { isOptionsOpen = false },
                                modifier = Modifier
                                    .background(Color(0xFF1E1F20))
                                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                    .padding(8.dp)
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Deep Search", color = if(thinkingMode) Color.White else Color.Gray, fontFamily = Inter, fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                                    onClick = { thinkingMode = !thinkingMode },
                                    leadingIcon = { Icon(LokiIcons.Sparkles, contentDescription = null, modifier = Modifier.size(16.dp), tint = if(thinkingMode) Color.White else Color.Gray) }
                                )
                                DropdownMenuItem(
                                    text = { Text("Web Grounding", color = if(searchGrounding) Color.White else Color.Gray, fontFamily = Inter, fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                                    onClick = { searchGrounding = !searchGrounding },
                                    leadingIcon = { Icon(LokiIcons.Globe, contentDescription = null, modifier = Modifier.size(16.dp), tint = if(searchGrounding) Color.White else Color.Gray) }
                                )
                                DropdownMenuItem(
                                    text = { Text("Image Mode", color = if(isImageMode) Color.White else Color.Gray, fontFamily = Inter, fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                                    onClick = { isImageMode = !isImageMode },
                                    leadingIcon = { Icon(LokiIcons.ImageIcon, contentDescription = null, modifier = Modifier.size(16.dp), tint = if(isImageMode) Color.White else Color.Gray) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        // Model Selector
                        Box {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                    .clickable { isModelMenuOpen = !isModelMenuOpen }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = modelMode.replaceFirstChar { it.uppercase() },
                                        color = Color.LightGray,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = Inter
                                    )
                                    Icon(
                                        imageVector = LokiIcons.ChevronDown,
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.size(14.dp).padding(start = 4.dp)
                                    )
                                }
                            }

                            DropdownMenu(
                                expanded = isModelMenuOpen,
                                onDismissRequest = { isModelMenuOpen = false },
                                modifier = Modifier
                                    .background(Color(0xFF1E1F20))
                                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                    .padding(8.dp)
                            ) {
                                val models = listOf(
                                    Triple("fast", "Fast", LokiIcons.Zap),
                                    Triple("pro", "Pro", LokiIcons.Brain),
                                    Triple("happy", "Happy", LokiIcons.Smile)
                                )
                                models.forEach { (id, label, icon) ->
                                    DropdownMenuItem(
                                        text = { Text(label, color = if(modelMode == id) Color.White else Color.Gray, fontFamily = Inter, fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                                        onClick = {
                                            onModelModeChange(id)
                                            isModelMenuOpen = false
                                        },
                                        leadingIcon = { Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp), tint = if(modelMode == id) Color.White else Color.Gray) }
                                    )
                                }
                            }
                        }
                    }
                }

                // Mic / Send / Stop Button
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp, end = 8.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            if (text.isNotEmpty()) {
                                if (isAwakenedMode) Color.White.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.1f)
                            } else Color.Transparent
                        )
                        .let {
                            if (text.isEmpty()) it.border(1.dp, Color.White.copy(alpha = 0.1f), CircleShape) else it
                        }
                        .clickable {
                            if (text.isNotEmpty()) onSendClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (text.isEmpty()) {
                        Icon(
                            imageVector = LokiIcons.Mic,
                            contentDescription = "Voice",
                            tint = Color.LightGray,
                            modifier = Modifier.size(22.dp)
                        )
                    } else {
                        Icon(
                            imageVector = LokiIcons.Send,
                            contentDescription = "Send",
                            tint = if (isAwakenedMode) Color(0xFF00F2FF) else Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
