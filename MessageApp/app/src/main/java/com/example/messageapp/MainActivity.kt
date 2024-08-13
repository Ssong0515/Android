package com.example.messageapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.messageapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val addminId = "song"
        val addminPw = "good123"


        binding.loginBtn.setOnClickListener {
            if ((binding.idText.text.toString() == addminId) && (binding.pwText.text.toString() == addminPw)) {
                val intent = Intent(this, ChatActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "xxx", Toast.LENGTH_SHORT).show()
            }

        }

    }
}