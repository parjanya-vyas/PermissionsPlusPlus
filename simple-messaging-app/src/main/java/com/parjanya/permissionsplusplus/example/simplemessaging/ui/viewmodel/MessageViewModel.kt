package com.parjanya.permissionsplusplus.example.simplemessaging.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.parjanya.permissionsplusplus.example.simplemessaging.data.Message
import com.parjanya.permissionsplusplus.example.simplemessaging.data.MessageDatabase
import com.parjanya.permissionsplusplus.example.simplemessaging.data.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MessageViewModel public constructor(application: Application) : AndroidViewModel(application) {

    private val repository = MessageRepository(MessageDatabase.getDatabase(application).messageDao())
    val allMessages: LiveData<List<Message>> = repository.allMessages

    fun insertMessage(message: Message) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(message)
    }

    fun deleteMessage(message: Message) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(message)
    }

}