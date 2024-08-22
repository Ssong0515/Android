package com.example.app06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app06.databinding.PersonItemBinding

interface OnItemClickListener {
    fun onItemClick(pos: Int)
}

class PersonAdapter(val personList: MutableList<Person>): RecyclerView.Adapter<PersonAdapter.PersonHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    // 추가 메서드
    fun addItem(person: Person) {
        personList.add(person)
        notifyDataSetChanged()
    }
    // 수정 메서드
    fun editItem(person: Person, position: Int) {
        var editPerson = person
        personList[position] = person
        notifyDataSetChanged()
    }
    // 삭제 메서드
    fun deleteItem(position: Int) {
        personList.removeAt(position)
        notifyDataSetChanged()
    }

    inner class PersonHolder(val personItemView: PersonItemBinding): RecyclerView.ViewHolder(personItemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val itemView = PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonHolder(itemView)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.personItemView.nameText.text = personList[position].name
        holder.personItemView.phoneText.text = personList[position].phone

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }

    }


}