package com.example.myapplication06

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication06.databinding.Activity2SubBinding

class MainActivity2_sub : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity2_sub)

        val binding = Activity2SubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.confirmBtn.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("name", binding.edtName.text.toString())
            resultIntent.putExtra("phone", binding.edtPhone.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        binding.cancelBtn.setOnClickListener {
            finish()
        }

    }
}