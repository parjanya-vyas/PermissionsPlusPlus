package com.parjanya.permissionsplusplus.example.simplemessaging.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class MessageRepository(private val messageDao: MessageDao) {

    val allMessages:LiveData<List<Message>> = messageDao.getAllMessages()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(message: Message) {
        messageDao.insertMessage(message)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(message: Message) {
        messageDao.deleteMessage(message)
    }

}