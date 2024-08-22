package com.example.app06

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app06.databinding.MessageViewBinding

interface MessageInter {
    fun handleTap(position: Int)
    fun handleLongTap(position: Int)
}

class MessageAdapter(val messages: MutableList<Person2>) :
    RecyclerView.Adapter<MessageAdapter.PersonHolder>() {
    inner class PersonHolder(val messageView: MessageViewBinding) :
        RecyclerView.ViewHolder(messageView.root)

    var messageInter: MessageInter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val messageView =
            MessageViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonHolder(messageView)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.messageView.nameText.text = messages[position].name
        holder.messageView.messageText.text = messages[position].message

        holder.itemView.setOnClickListener {
            messageInter?.handleTap(position)
        }

        holder.itemView.setOnLongClickListener {
            messageInter?.handleLongTap(position)
            notifyDataSetChanged()
            true
        }
    }


}