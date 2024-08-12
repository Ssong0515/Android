package com.example.app04_intent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app04_intent.databinding.ActivityMainActivity2SubBinding

class MainActivity2_sub : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main_activity2_sub)

        val binding = ActivityMainActivity2SubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val intent = intent
        val first = intent.getStringExtra("first")
        val second = intent.getStringExtra("second")

        binding.subAddBtn.setOnClickListener {
            if (first != null && second != null) {
                var result = first.toInt() + second.toInt()
                intent.putExtra("addResult", result.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }

    }
}