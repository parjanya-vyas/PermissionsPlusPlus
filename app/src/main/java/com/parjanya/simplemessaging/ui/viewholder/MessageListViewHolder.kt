package com.parjanya.simplemessaging.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class MessageListViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(parentView: ViewGroup, @LayoutRes layoutResId: Int) : this(
        LayoutInflater.from(parentView.context).inflate(
            layoutResId,
            parentView,
            false
        )
    )

}