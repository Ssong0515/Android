package com.example.app11_member

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.app11_member.databinding.ItemMemberBinding
import retrofit2.Call
import retrofit2.Response

class MemberAdapter(var memberList: MutableList<Member>) :
    RecyclerView.Adapter<MemberAdapter.Holder>() {

    class Holder(val binding: ItemMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        val idText = binding.txtId
        val nameText = binding.txtName
        val phoneText = binding.txtPhone
        val emailText = binding.txtEmail
    }

    interface OnItemClickListener {
        fun onItemClick(member: Member, position: Int)
    }

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = memberList[position]
        holder.idText.text = member.id.toString()
        holder.nameText.text = member.name
        holder.phoneText.text = member.phone
        holder.emailText.text = member.email

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(member, holder.adapterPosition)
        }

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(it.context).run {
                setTitle("정말 삭제 할까요?")
                setPositiveButton("삭제") { _, _ ->
                    MemberClient.retrofit.deleteById(member.id)
                        .enqueue(object : retrofit2.Callback<Void> {
                            override fun onResponse(
                                call: Call<Void>,
                                response: Response<Void>
                            ) {
                                deleteItem(holder.adapterPosition)
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })

                }
                setNegativeButton("닫기", null)
                show()
            }

            false
        }


    }

    fun addItem(member: Member) {
        memberList.add(member)
        notifyDataSetChanged()
    }

    fun updateItem(member: Member, position: Int) {
        memberList[position] = member
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        memberList.removeAt(position)
        notifyDataSetChanged()
    }

}