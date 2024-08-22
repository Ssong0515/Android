package com.example.myapplication06

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication06.databinding.PersonItemBinding

class PersonHolder(val bindingView: PersonItemBinding): RecyclerView.ViewHolder(bindingView.root)

class PersonAdapter(val personList: MutableList<Person>): RecyclerView.Adapter<PersonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val personItemView = PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonHolder(personItemView)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.bindingView.tvName.text = personList[position].name
        holder.bindingView.tvPhone.text = personList[position].phone

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "클릭$position", Toast.LENGTH_LONG).show()
        }

        holder.itemView.setOnLongClickListener {
            personList.removeAt(position)
            notifyItemRemoved(position)
            true
        }
    }


}
