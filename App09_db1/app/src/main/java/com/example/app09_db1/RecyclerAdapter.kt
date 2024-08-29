package com.example.app09_db1

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app09_db1.databinding.ActivityMainBinding
import com.example.app09_db1.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>() {

    var listData = mutableListOf<Memo>()
    var helper: SqliteHelper? = null
    var mainDelegate: MainDelegate? = null

    inner class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        var mMemo: Memo? = null

        init {
            binding.buttonDelete.setOnClickListener {
                helper?.deleteMemo(mMemo!!)
                listData.remove(mMemo!!)
                notifyDataSetChanged()
            }
        }

        fun setMemo(memo: Memo) {
            binding.textNo.text = "${memo.num}"
            binding.textContent.text = memo.content

            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDateTime.text = "${sdf.format(memo.dateTime)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.Holder {
        return Holder(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
        holder.mMemo = memo

        holder.itemView.setOnClickListener {
            mainDelegate?.selectRow(memo, position)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}