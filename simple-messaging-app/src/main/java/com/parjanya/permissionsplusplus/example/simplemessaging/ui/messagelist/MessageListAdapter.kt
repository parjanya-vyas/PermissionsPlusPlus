package com.parjanya.permissionsplusplus.example.simplemessaging.ui.messagelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parjanya.permissionsplusplus.R
import kotlinx.android.synthetic.main.row_message_list.view.*

class MessageListAdapter(private val messageInfoList: List<MessageInfo>) : RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.row_message_list, parent, false)
        return MessageListViewHolder(
            rowView
        )
    }

    override fun getItemCount() = messageInfoList.size

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        holder.itemView.dateTv.text = messageInfoList[position].date
        holder.itemView.senderTv.text = messageInfoList[position].senderName
    }

    class MessageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}