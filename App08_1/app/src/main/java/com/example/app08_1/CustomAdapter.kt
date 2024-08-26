package com.example.app08_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app08_1.databinding.ItemViewpagerBinding

class CustomAdapter(val fragmentList: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(val binding: ItemViewpagerBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val customHolder = holder as ViewHolder
        customHolder.binding.textView.text = fragmentList[position]
    }

}
