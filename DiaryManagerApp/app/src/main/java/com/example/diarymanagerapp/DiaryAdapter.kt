package com.example.diarymanagerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diarymanagerapp.databinding.ItemRecycleBinding

data class Diary(val num: Long?, val title: String, val content: String, val date: String)

class DiaryAdapter(var diaries: List<Diary>): RecyclerView.Adapter<DiaryAdapter.Holder>() {

    class Holder(binding: ItemRecycleBinding): RecyclerView.ViewHolder(binding.root){
        val textNo = binding.textNum
        val textTitle = binding.textTitle
        val textDate = binding.textDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryAdapter.Holder {
        return Holder(ItemRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DiaryAdapter.Holder, position: Int) {
        val diary = diaries[position]
        holder.textNo.text = diary.num?.toString()
        holder.textTitle.text = diary.title
        holder.textDate.text = diary.date
    }

    override fun getItemCount(): Int {
        return diaries.size
    }

    fun updateData(newDiaries: List<Diary>){
        diaries = newDiaries
        notifyDataSetChanged()
    }

}
