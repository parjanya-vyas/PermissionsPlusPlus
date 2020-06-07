package com.parjanya.permissionsplusplus.example.simplemessaging.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {

    @Query("SELECT * from ${MessageDatabase.MESSAGE_TABLE_NAME}")
    fun getAllMessages(): LiveData<List<Message>>

    @Query("SELECT * from ${MessageDatabase.MESSAGE_TABLE_NAME} WHERE sender = :sender")
    fun getMessagesFromSender(sender: String): LiveData<List<Message>>

    @Query("SELECT * from ${MessageDatabase.MESSAGE_TABLE_NAME} WHERE body LIKE :search")
    fun getMessageContainingWord(search: String): LiveData<List<Message>>

    @Query("SELECT * from ${MessageDatabase.MESSAGE_TABLE_NAME} WHERE date >= :startDate AND date <= :endDate")
    fun getMessageOnDates(startDate: Long, endDate: Long): LiveData<List<Message>>

    @Insert
    fun insertMessage(message: Message)

    @Delete
    fun deleteMessage(message: Message)
}