package com.parjanya.permissionsplusplus.example.simplemessaging.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.parjanya.permissionsplusplus.example.simplemessaging.data.MessageDatabase.Companion.DATABASE_NAME
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Message::class], version = 1)
abstract class MessageDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {

        const val DATABASE_NAME = "message_database"
        const val MESSAGE_TABLE_NAME = "message_table"

        private lateinit var instance: MessageDatabase

        fun getDatabase(context: Context): MessageDatabase {
            if (!this::instance.isInitialized) {
                instance = Room.databaseBuilder(
                        context.applicationContext,
                        MessageDatabase::class.java,
                        DATABASE_NAME
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance
        }
    }

}