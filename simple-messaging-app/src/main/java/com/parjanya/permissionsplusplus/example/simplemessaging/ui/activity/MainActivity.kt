package com.parjanya.permissionsplusplus.example.simplemessaging.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parjanya.permissionsplusplus.R
import com.parjanya.permissionsplusplus.example.simplemessaging.data.Message
import com.parjanya.permissionsplusplus.example.simplemessaging.ui.messagelist.MessageListAdapter
import com.parjanya.permissionsplusplus.example.simplemessaging.ui.viewmodel.MessageViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var messageViewModel: MessageViewModel
    private lateinit var adapter: MessageListAdapter
    private val NEW_MESSAGE_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
        setupViewModel()
        setupOnClickListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_MESSAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let { messageViewModel.insertMessage(it.getParcelableExtra(NewMessageActivity.MESSAGE_EXTRA) as Message) }
        }
    }

    private fun setupOnClickListeners() {
        newMessageFab.setOnClickListener {
            val intent = Intent(this, NewMessageActivity::class.java)
            startActivityForResult(intent, NEW_MESSAGE_REQUEST_CODE)
        }
    }

    private fun setupViewModel() {
        messageViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(MessageViewModel::class.java)
        messageViewModel.allMessages.observe(this, Observer {messages ->
            messages?.let { adapter.messageList = messages }
        })
    }

    private fun setupAdapter() {
        adapter = MessageListAdapter { message -> messageViewModel.deleteMessage(message) }
        messageListRv.adapter = adapter
        messageListRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}