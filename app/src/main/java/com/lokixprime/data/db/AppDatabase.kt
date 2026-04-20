package com.lokixprime.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lokixprime.data.db.dao.ChatDao
import com.lokixprime.data.db.dao.SettingsDao
import com.lokixprime.data.db.entity.ChatSessionEntity
import com.lokixprime.data.db.entity.MessageEntity
import com.lokixprime.data.db.entity.SettingsEntity

@Database(entities = [ChatSessionEntity::class, MessageEntity::class, SettingsEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "loki_chat_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
