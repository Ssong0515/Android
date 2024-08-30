package com.example.app10_member

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app10_member.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

interface OnClickListener {
    fun clickItem(member: Member, position: Int)
}

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var memberAdapter: MemberAdapter
    lateinit var launcher: ActivityResultLauncher<Intent>

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

        var memberList = mutableListOf<Member>(
            Member(0, "test", "1234", "123@naver.com"),
            Member(1, "test1", "1234", "123@naver.com"),
            Member(2, "test2", "1234", "123@naver.com"),
            Member(3, "test3", "1234", "123@naver.com"),
        )

        memberAdapter = MemberAdapter(memberList)
        memberAdapter.onClickListener = this;
        MemberClient.retrofit.findAll().enqueue(object : retrofit2.Callback<List<Member>> {
            override fun onResponse(call: Call<List<Member>>, response: Response<List<Member>>) {
                response.body()?.let {
                    memberAdapter.memberList = it.toMutableList()
                    memberAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Member>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        binding.recyclerView.adapter = memberAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            launcher.launch(intent)
        }

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.let { data ->

                        val id = data.getStringExtra("id")?.toLong()
                        val position = data.getStringExtra("position").toString()
                        val name = data.getStringExtra("name").toString()
                        val phone = data.getStringExtra("phone").toString()
                        val email = data.getStringExtra("email").toString()

                        if (data.getStringExtra("btn").toString().equals("수정")) {

                            id?.let {
                                val updatedMember = Member(it, name, phone, email)
                                MemberClient.retrofit.update(it, updatedMember)
                                    .enqueue(object : retrofit2.Callback<Member> {
                                        override fun onResponse(
                                            call: Call<Member>,
                                            response: Response<Member>
                                        ) {
                                            response.body()?.let {
                                                memberAdapter.memberList[position.toInt()] = it
                                                memberAdapter.notifyDataSetChanged()
                                            }
                                        }

                                        override fun onFailure(call: Call<Member>, t: Throwable) {
                                            TODO("Not yet implemented")
                                        }

                                    })
                            }

                        } else {
                            MemberClient.retrofit.save(Member(null, name, phone, email))
                                .enqueue(object : retrofit2.Callback<Member> {
                                    override fun onResponse(
                                        call: Call<Member>,
                                        response: Response<Member>
                                    ) {
                                        response.body()?.let { member ->
                                            memberAdapter.memberList.add(member)
                                            memberAdapter.notifyDataSetChanged()
                                        }
                                    }

                                    override fun onFailure(call: Call<Member>, t: Throwable) {
                                        TODO("Not yet implemented")
                                    }

                                })

                        }


                    }
                }
            }

    }

    override fun clickItem(member: Member, position: Int) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("btn", "수정")
        intent.putExtra("position", position.toString())
        intent.putExtra("id", member.num.toString())
        intent.putExtra("name", member.name)
        intent.putExtra("phone", member.phone)
        intent.putExtra("email", member.email)
        launcher.launch(intent)
    }
}