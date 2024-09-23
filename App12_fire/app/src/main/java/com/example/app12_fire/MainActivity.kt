package com.example.app12_fire

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app12_fire.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

//        val database = Firebase.database("https://fullstack405-default-rtdb.firebaseio.com/")
    val database = Firebase.database("https://fullstack405jun-default-rtdb.firebaseio.com/")
    val userRef = database.getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSignUp.setOnClickListener {
            with(binding) {
                val id = editId.text.toString()
                val password = editPassword.text.toString()
                val name = editNick.text.toString()

                if (id.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                    userRef.child(id).get().addOnSuccessListener {
                        if (it.exists()) {
                            Toast.makeText(this@MainActivity, "아이디 존재", Toast.LENGTH_LONG).show()
                        } else {
                            val id = userRef.push().key!!
                            val user = User(id, password, name)
//                            userRef.child(id).setValue(user)
                            userRef.setValue(user)
                            signIn()
                        }
                    }

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "아이디 비밀번호 별명을 모두 입력 해야 합니다",
                        Toast.LENGTH_LONG
                    ).show()
                }


            }

        }

        binding.btnSign.setOnClickListener {
            signIn()
        }

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // 데이터가 변경될 때마다 이 메소드가 호출됨
                val value = snapshot.getValue(User::class.java)
                Log.d("Firebase", "Data updated: ${value?.id}, ${value?.password}, ${value?.name}")

                value?.let {
                    binding.editId.setText(it.id)
                    binding.editPassword.setText(it.name)
                    binding.editNick.setText(it.password)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // 데이터 로드 실패 시 처리
                Log.e("Firebase", "Failed to read value.", error.toException())
            }
        })

    }

    fun signIn() {
        with(binding) {
            val id = editId.text.toString()
            val password = editPassword.text.toString()
            if (id.isNotEmpty() && password.isNotEmpty()) {
                userRef.child(id).get().addOnSuccessListener {
                    if (it.exists()) {
                        it.getValue(User::class.java)?.let { user ->
                            if (user.password == password) {
                                binding.editNick.setText(user.name)
                                Toast.makeText(this@MainActivity, "로그인 성공.", Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                Toast.makeText(this@MainActivity, "비밀번호가 다릅니다", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "아이디가 없습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}