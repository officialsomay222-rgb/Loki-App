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

@Composable
fun AppSidebar(
    onCloseSidebar: () -> Unit,
    onSettingsClick: () -> Unit,
    onAppsClick: () -> Unit,
    onClearChatClick: () -> Unit
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

            // Timeline Items List Placeholder (Future impl)
            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    color = Color.White.copy(alpha = 0.05f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Current Session",
                        color = LokiCyan,
                        fontFamily = Inter,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            HorizontalDivider(color = Color.White.copy(alpha = 0.1f))

            // Bottom Actions
            Column(modifier = Modifier.padding(top = 16.dp)) {
                SidebarActionItem(
                    icon = LokiIcons.Rocket,
                    label = "Try Our Apps",
                    onClick = onAppsClick
                )
                SidebarActionItem(
                    icon = LokiIcons.Settings,
                    label = "Settings",
                    onClick = onSettingsClick
                )
                SidebarActionItem(
                    icon = LokiIcons.X, // Using X for "Clear" as placeholder
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
