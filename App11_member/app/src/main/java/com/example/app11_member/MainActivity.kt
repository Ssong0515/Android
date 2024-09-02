package com.example.app11_member

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app11_member.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var memberList = mutableListOf<Member>()
        val memberAdapter = MemberAdapter(memberList)
        binding.recyclerView.adapter = memberAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

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

        val activityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val button = it.data?.getStringExtra("button")
                    val id = it.data?.getLongExtra("id", 0) ?: 0
                    val name = it.data?.getStringExtra("name") ?: ""
                    val phone = it.data?.getStringExtra("phone") ?: ""
                    val email = it.data?.getStringExtra("email") ?: ""
                    val position = it.data?.getIntExtra("position", 0) ?: 0

                    var member = Member(id, name, phone, email)

                    if (button == "추가") {
                        MemberClient.retrofit.save(member)
                            .enqueue(object : retrofit2.Callback<Member> {
                                override fun onResponse(
                                    call: Call<Member>,
                                    response: Response<Member>
                                ) {
                                    response.body()?.let {
                                        memberAdapter.addItem(it)
                                    }
                                }

                                override fun onFailure(call: Call<Member>, t: Throwable) {
                                    TODO("Not yet implemented")
                                }

                            })

                    } else {
                        MemberClient.retrofit.update(id, member).enqueue(object :retrofit2.Callback<Member>{
                            override fun onResponse(
                                call: Call<Member>,
                                response: Response<Member>
                            ) {
                                memberAdapter.updateItem(member, position!!)
                            }

                            override fun onFailure(call: Call<Member>, t: Throwable) {
                                TODO("Not yet implemented")
                            }

                        })
                    }


                }
            }

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            intent.putExtra("button", "add")
            activityResult.launch(intent)
        }

        memberAdapter.onItemClickListener = object : MemberAdapter.OnItemClickListener {
            override fun onItemClick(member: Member, position: Int) {
                val intent = Intent(this@MainActivity, InputActivity::class.java)
                intent.putExtra("id", member.id)
                intent.putExtra("name", member.name)
                intent.putExtra("phone", member.phone)
                intent.putExtra("email", member.email)
                intent.putExtra("position", position)
                intent.putExtra("button", "update")

                activityResult.launch(intent)
            }

        }

    }
}