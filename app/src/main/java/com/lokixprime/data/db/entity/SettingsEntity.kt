package com.lokixprime.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey val id: Int = 1, // Single row for settings
    val theme: String = "system",
    val bgStyle: String = "default",
    val commanderName: String = "Somay",
    val commanderEmail: String = "devsomay.21@gmail.com",
    val avatarUrl: String = "https://i.ibb.co/ns3LTFwp/Picsart-26-02-28-11-29-26-443.jpg",
    val modelMode: String = "pro",
    val tone: String = "custom",
    val systemInstruction: String = "You are LOKI PRIME, an advanced AI system...",
    val temperature: Float = 0.7f,
    val enterToSend: Boolean = true,
    val bubbleStyle: String = "glass",
    val fontSize: String = "medium",
    val fontStyle: String = "sans",
    val soundEnabled: Boolean = true,
    val messageAnimation: Boolean = true,
    val autoScroll: Boolean = true,
    val isAwakened: Boolean = false,
    val effectInputBox: Boolean = true,
    val effectMessageBubbles: Boolean = true,
    val effectSidebar: Boolean = true,
    val effectBackground: Boolean = true,
    val effectAvatar: Boolean = true,
    val sidebarPosition: String = "left",
    val appWidth: String = "normal",
    val apiAuthToken: String = ""
)
