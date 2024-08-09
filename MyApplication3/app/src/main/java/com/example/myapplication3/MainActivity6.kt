package com.example.myapplication3


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication3.databinding.ActivityMain6Binding

class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main6)

        val binding = ActivityMain6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Four 라디오 버튼 클릭
//        binding.rdo3.setOnClickListener {
//            val intent = Intent(this@MainActivity6, FourActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.rdo1.setOnClickListener {
//            val intent = Intent(this@MainActivity6, SecondActivity::class.java)
//            startActivity(intent)
//        }

        binding.newBtn1.setOnClickListener {
            var intent: Intent? = null
            if (binding.rdo1.isChecked) {
                intent = Intent(applicationContext, SecondActivity::class.java)
            } else if (binding.rdo2.isChecked) {
                intent = Intent(applicationContext, ThirdActivity::class.java)
                intent.putExtra("name", "song")
                intent.putExtra("key", "keyValue!")
            } else {
                intent = Intent(applicationContext, FourActivity::class.java)
            }
            startActivity(intent)
        }

    }
}