package com.lokixprime.ui.components.sidebar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.theme.Inter
import com.lokixprime.ui.theme.LokiCyan
import com.lokixprime.ui.theme.Montserrat
import com.lokixprime.ui.theme.SurfaceVariantDark
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import com.lokixprime.data.db.entity.ChatSessionEntity

@Composable
fun AppSidebar(
    sessions: List<ChatSessionEntity> = emptyList(),
    currentSessionId: String? = null,
    isAwakened: Boolean = false,
    effectSidebar: Boolean = false,
    onCloseSidebar: () -> Unit,
    onSettingsClick: () -> Unit,
    onClearChatClick: () -> Unit,
    onSessionClick: (String) -> Unit = {},
    onSessionDelete: (String) -> Unit = {},
    onSessionPin: (String) -> Unit = {},
    onSessionRename: (String, String) -> Unit = { _, _ -> }
) {
    ModalDrawerSheet(
        drawerContainerColor = SurfaceVariantDark,
        modifier = Modifier.width(300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .statusBarsPadding()
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "LOKI PRIME X",
                    color = Color.White,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    letterSpacing = 2.sp
                )
                IconButton(onClick = onCloseSidebar) {
                    Icon(
                        imageVector = LokiIcons.X,
                        contentDescription = "Close Sidebar",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "TIMELINE",
                color = Color.Gray,
                fontFamily = Inter,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Timeline Items List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                itemsIndexed(
                    items = sessions,
                    key = { _, session -> session.id }
                ) { index, session ->
                    TimelineItem(
                        session = session,
                        isActive = session.id == currentSessionId,
                        isAwakened = isAwakened,
                        effectSidebar = effectSidebar,
                        onClick = onSessionClick,
                        onDelete = onSessionDelete,
                        onPin = onSessionPin,
                        onRename = onSessionRename,
                        index = index
                    )
                }
            }

            HorizontalDivider(color = Color.White.copy(alpha = 0.1f))

            // Bottom Actions
            Column(modifier = Modifier.padding(top = 16.dp)) {
                SidebarActionItem(
                    icon = LokiIcons.Settings,
                    label = "Settings",
                    onClick = onSettingsClick
                )
                SidebarActionItem(
                    icon = LokiIcons.Trash2,
                    label = "Clear Chat",
                    onClick = onClearChatClick,
                    tint = Color(0xFFFF4A4A)
                )
            }
        }
    }
}

@Composable
fun SidebarActionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit,
    tint: Color = Color.White
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            color = tint,
            fontFamily = Inter,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
