package com.parjanya.permissionsplusplus.example.simplemessaging.ui.messagelist

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parjanya.permissionsplusplus.R
import com.parjanya.permissionsplusplus.example.simplemessaging.data.Message
import com.parjanya.permissionsplusplus.example.simplemessaging.ui.activity.MessageDetailsActivity
import com.parjanya.permissionsplusplus.example.simplemessaging.ui.activity.NewMessageActivity
import kotlinx.android.synthetic.main.row_message_list.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MessageListAdapter(val onDeleteMessageListener: (Message) -> Unit) : RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder>() {

    var messageList: List<Message> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.row_message_list, parent, false)
        return MessageListViewHolder(rowView)
    }

    override fun getItemCount() = messageList.size

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        val date = Date(messageList[position].date)
        holder.itemView.dateTv.text = SimpleDateFormat("${NewMessageActivity.DATE_FORMAT} ${NewMessageActivity.TIME_FORMAT}", Locale.getDefault()).format(date)
        holder.itemView.senderTv.text = messageList[position].sender
        holder.itemView.setOnClickListener {
            it.context.startActivity(MessageDetailsActivity.getIntent(it.context, messageList[position]))
        }
        holder.itemView.setOnLongClickListener {
            val context = it.context
            AlertDialog.Builder(context)
                .setTitle("Delete Message")
                .setMessage("Do you want to delete this message?")
                .setPositiveButton("Yes") { dialog, _ ->
                    onDeleteMessageListener(messageList[position])
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                .show()

            return@setOnLongClickListener true
        }
    }

    class MessageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}