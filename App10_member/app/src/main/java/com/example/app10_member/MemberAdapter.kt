package com.example.app10_member

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.app10_member.databinding.ActivityMainBinding
import com.example.app10_member.databinding.ItemMemberBinding
import retrofit2.Call
import retrofit2.Response
import com.example.app10_member.OnClickListener

class MemberAdapter(var memberList: MutableList<Member>) :
    RecyclerView.Adapter<MemberAdapter.Holder>() {

    var onClickListener: OnClickListener? = null

    class Holder(val binding: ItemMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        val idTextView = binding.txtNo
        val nameTextView = binding.txtName
        val phoneTextView = binding.txtPhone
        val emailTextView = binding.txtEmail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = memberList[position]
        holder.idTextView.text = member.num.toString()
        holder.nameTextView.text = member.name
        holder.phoneTextView.text = member.phone
        holder.emailTextView.text = member.email

        holder.itemView.setOnClickListener {
            onClickListener?.clickItem(member, holder.adapterPosition)
        }

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(it.context).run {
                setTitle("정말 삭제 할까요?")
                setPositiveButton("삭제", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        MemberClient.retrofit.delete(member.num!!)
                            .enqueue(object : retrofit2.Callback<Void> {
                                override fun onResponse(
                                    call: Call<Void>,
                                    response: Response<Void>
                                ) {
                                    memberList.remove(member)
                                    notifyDataSetChanged()
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    TODO("Not yet implemented")
                                }

                            })
                    }
                })
                setNegativeButton("취소", null)
                show()
            }
            false
        }
    }


}