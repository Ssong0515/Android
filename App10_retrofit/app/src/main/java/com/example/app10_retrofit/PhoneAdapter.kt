package com.example.app10_retrofit

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.app10_retrofit.databinding.ItemPhoneBinding
import com.example.app10_retrofit.Phone
import com.example.app10_retrofit.databinding.CustomPhoneBinding
import retrofit2.Call
import retrofit2.Response

class PhoneAdapter(var phoneList: MutableList<Phone>) :
    RecyclerView.Adapter<PhoneAdapter.Holder>() {

    var onClickListener: OnClickListener? = null

    class Holder(val binding: ItemPhoneBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView = binding.txtName
        val phoneTextView = binding.txtPhone
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemPhoneBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var phone = phoneList[position]
        holder.nameTextView.text = phone.name
        holder.phoneTextView.text = phone.phone

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(it.context).run {
                setTitle("정말 삭제할까요?")
                setPositiveButton("삭제", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        PhoneClient.retrofit.deleteById(phone.num)
                            .enqueue(object : retrofit2.Callback<Void> {
                                override fun onResponse(
                                    call: Call<Void>,
                                    response: Response<Void>
                                ) {
                                    removeItem(holder.adapterPosition)
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    TODO("Not yet implemented")
                                }

                            })
                    }
                })
                setNegativeButton("닫기", null)
                show()
            }
            false
        }

        holder.itemView.setOnClickListener {
            val dialogPhone = CustomPhoneBinding.inflate(LayoutInflater.from(it.context))
            AlertDialog.Builder(it.context).run {
                setTitle("수정")
                setView(dialogPhone.root)
                dialogPhone.edtName.setText(phone.name)
                dialogPhone.edtPhone.setText(phone.phone)
                setPositiveButton("수정", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                        val newPhone = phone
                        newPhone.name = dialogPhone.edtName.text.toString()
                        newPhone.phone = dialogPhone.edtPhone.text.toString()

                        PhoneClient.retrofit.update(newPhone.num, newPhone)
                            .enqueue(object : retrofit2.Callback<Phone> {
                                override fun onResponse(
                                    call: Call<Phone>,
                                    response: Response<Phone>
                                ) {
                                    updateItem(holder.adapterPosition, response.body())
                                }

                                override fun onFailure(call: Call<Phone>, t: Throwable) {
                                    TODO("Not yet implemented")
                                }

                            })
                    }
                })
                setNegativeButton("닫기", null)
                show()
            }
        }

    }

    fun addItem(phone: Phone) {
        phoneList.add(phone)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        phoneList.removeAt(position)
        notifyDataSetChanged()
    }

    fun updateItem(position: Int, newValue: Phone?) {

        newValue?.let { it ->
            phoneList[position] = newValue
            notifyDataSetChanged()
        }

    }

}