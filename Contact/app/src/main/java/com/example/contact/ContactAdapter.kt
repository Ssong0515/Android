package com.example.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.databinding.PersonItemBinding

interface OnContactClickListener {
    fun onContactClick(contact: Contact)
    fun onContactDelete(contact: Contact)
    fun onContactSend(contact: Contact)
}

class ContactAdapter(
    private val contacts: MutableList<Contact>,
    private val listener: OnContactClickListener
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val binding:PersonItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.binding.nameText.text = contact.name
        holder.binding.phoneText.text = contact.phone

        holder.itemView.setOnClickListener {
            listener.onContactClick(contact)
        }

        holder.binding.deleteButton.setOnClickListener {
            listener.onContactDelete(contact)
        }

        holder.binding.sendButton.setOnClickListener {
            listener.onContactSend(contact)
        }

    }

    fun addContact(contact: Contact) {
        contacts.add(contact)
        notifyDataSetChanged()
    }

    fun updateContact(contact: Contact) {
        val index = contacts.indexOfFirst { it.id == contact.id }
        if (index != -1) {
            contacts[index] = contact
            notifyItemChanged(index)
        }
    }

    fun deleteContact(contact: Contact) {
        val index = contacts.indexOfFirst { it.id == contact.id }
        if (index != -1) {
            contacts.removeAt(index)
            notifyItemRemoved(index)
        }
    }

}
