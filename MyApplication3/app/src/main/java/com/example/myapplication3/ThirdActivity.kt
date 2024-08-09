package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intent = intent
        val nameValue = intent.getStringExtra("name")
        val keyValue = intent.getStringExtra("key")

        findViewById<Button>(R.id.returnBtn4).setOnClickListener {
            val intent = Intent(this@ThirdActivity, FourActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.returnBtn2).setOnClickListener {
            val intent = Intent(this@ThirdActivity, MainActivity6::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.textView).text = "name: $nameValue, key: $keyValue"
    }
}