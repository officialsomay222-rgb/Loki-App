package com.lokixprime.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "sessions")
data class ChatSessionEntity(
    @PrimaryKey val id: String,
    val title: String,
    val updatedAt: Long,
    val isPinned: Boolean = false,
    val modelMode: String? = null,
    val draftText: String? = null,
    val draftAttachmentsJson: String? = null
)
