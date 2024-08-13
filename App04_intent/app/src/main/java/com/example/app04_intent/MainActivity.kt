package com.example.app04_intent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app04_intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var name: String?
        var age: String?
        var phone: String?

        // intent로부터 리턴값 처리
        // 매개변수 마지막이 람다함수 형식이면, 소괄호 밖으로 뺄 수 있음
        val activityResultLuncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

                if (it.resultCode == RESULT_OK) {
                    it.data?.let { data ->
                        name = data.getStringExtra("name")
                        age = data.getStringExtra("age")
                        phone = data.getStringExtra("phone")

                        Log.d("name: ", name ?: "No name")
                        Log.d("age: ", age ?: "No age")
                        Log.d("phone: ", phone ?: "No phone")
                    }
                } else {
                    Log.d("resultCodeError", "error")
                }
            }

        binding.inputBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity_sub::class.java)
            activityResultLuncher.launch(intent)
        }



//        binding.inputBtn.setOnClickListener {
//            val intent = Intent(this, MainActivity_sub::class.java)
//
//            startActivity(intent)
//        }


    }
}

