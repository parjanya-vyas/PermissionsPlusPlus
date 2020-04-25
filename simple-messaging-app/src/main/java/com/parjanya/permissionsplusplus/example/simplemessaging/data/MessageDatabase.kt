package com.parjanya.permissionsplusplus.example.simplemessaging.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Message::class], version = 1)
abstract class MessageDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {

        private lateinit var instance: MessageDatabase

        fun getDatabase(context: Context): MessageDatabase {
            if (!this::instance.isInitialized) {
                instance = Room.databaseBuilder(
                        context.applicationContext,
                        MessageDatabase::class.java,
                        "message_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance
        }
    }

}