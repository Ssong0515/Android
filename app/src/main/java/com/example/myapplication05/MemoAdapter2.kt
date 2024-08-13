package com.example.myapplication05

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication05.databinding.Memoholder2Binding

class Holder(val memoView2: Memoholder2Binding): RecyclerView.ViewHolder(memoView2.root)

class MemoAdapter2(val memoList2: MutableList<Memo2>): RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(Memoholder2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return memoList2.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.memoView2.textName.text = memoList2.get(position).name
        holder.memoView2.textPhone.text = memoList2.get(position).phone

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "클릭 $position", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnLongClickListener {
            memoList2.removeAt(position)
            notifyDataSetChanged()
            true
        }

    }

}