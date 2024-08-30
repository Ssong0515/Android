package com.example.app10_retrofit

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import com.example.app10_retrofit.Phone
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app10_retrofit.databinding.ActivityMainBinding
import com.example.app10_retrofit.databinding.CustomPhoneBinding
import retrofit2.Call
import retrofit2.Response

interface OnClickListener {
    fun longClick(phone: Phone)
}

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var phoneAdapter: PhoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val phoneList = mutableListOf<Phone>()
        phoneAdapter = PhoneAdapter(phoneList)
        phoneAdapter.onClickListener = this
        binding.recyclerView.adapter = phoneAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnList.setOnClickListener {
            PhoneClient.retrofit.findAll().enqueue(object : retrofit2.Callback<List<Phone>> {
                override fun onResponse(call: Call<List<Phone>>, response: Response<List<Phone>>) {
                    response.body()?.let {
                        Log.d("allRes", "${response.body()}")
                        phoneAdapter.phoneList = it.toMutableList()
                        phoneAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<Phone>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }

        binding.btnFloat.setOnClickListener {
            val dialogPhone = CustomPhoneBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setTitle("추가")
                setMessage("여기서 추가 내용을 입력하세요")
                setView(dialogPhone.root)
                setPositiveButton("추가", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        var p = Phone(
                            0,
                            dialogPhone.edtName.text.toString(),
                            dialogPhone.edtPhone.text.toString()
                        )
                        PhoneClient.retrofit.save(p).enqueue(object : retrofit2.Callback<Phone> {
                            override fun onResponse(call: Call<Phone>, response: Response<Phone>) {
                                response.body()?.let { it1 -> phoneAdapter.addItem(it1) }
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

    override fun longClick(phone: Phone) {
//        PhoneClient.retrofit.deleteById(phone.num)
//        phoneAdapter.phoneList.remove(phone)
//        phoneAdapter.notifyDataSetChanged()
    }
}