package com.example.myapplication05

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication05.databinding.ItemMemoBinding
import java.text.SimpleDateFormat

class CustomAdapter:RecyclerView.Adapter<CustomAdapter.Holder>() {
    var listData = mutableListOf<Memo>()

    // 중첩 클래스
    class Holder(val binding: ItemMemoBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                Toast.makeText(binding.root.context, "클릭 메모 = ${binding.textTitle.text}", Toast.LENGTH_LONG).show()
            }
        }

        fun setMemo(memo: Memo){
            binding.textNo.text = "${memo.no}"
            binding.textTitle.text = "${memo.title}"

            var sdf = SimpleDateFormat("yyyy/MM/dd")
            var formatDate = sdf.format(memo.timestamp)
            binding.textDate.text = formatDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.binding.textNo.text = listData[position].no.toString()
//        holder.binding.textTitle.text = listData[position].title
//        holder.binding.textDate.text = listData[position].timestamp.toString()
        holder.setMemo(listData.get(position))
    }
}