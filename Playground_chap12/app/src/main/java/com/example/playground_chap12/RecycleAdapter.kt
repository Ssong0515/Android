package com.example.playground_chap12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.playground_chap12.databinding.RecycleItemBinding

class RecycleAdapter(val recycleDatas: MutableList<String>) : RecyclerView.Adapter<RecycleAdapter.Holder>() {

    inner class Holder(val binding: RecycleItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdapter.Holder {
        return Holder(RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecycleAdapter.Holder, position: Int) {
        holder.binding.titleTextView.text = recycleDatas[position]
    }

    override fun getItemCount(): Int {
        return recycleDatas.size
    }


}
