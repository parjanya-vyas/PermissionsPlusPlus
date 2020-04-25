package com.parjanya.permissionsplusplus.example.simplemessaging.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parjanya.permissionsplusplus.R
import com.parjanya.permissionsplusplus.example.simplemessaging.data.Message
import kotlinx.android.synthetic.main.activity_message_details.*
import java.text.SimpleDateFormat
import java.util.*

class MessageDetailsActivity : AppCompatActivity(R.layout.activity_message_details) {

    companion object {
        const val MESSAGE_EXTRA = "MESSAGE_EXTRA"

        fun getIntent(context: Context, message: Message): Intent {
            return Intent(context, MessageDetailsActivity::class.java).putExtra(MESSAGE_EXTRA, message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message: Message = intent.getParcelableExtra(MESSAGE_EXTRA)!!
        senderTv.text = message.sender
        dateTv.text = SimpleDateFormat("${NewMessageActivity.DATE_FORMAT} ${NewMessageActivity.TIME_FORMAT}", Locale.getDefault()).format(
            Date(message.date)
        )
        bodyTv.text = message.body
    }

}