package com.example.myapplication3

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication3.databinding.ActivityMain7Binding

class MainActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main7)

        val binding = ActivityMain7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.switchBtn.setOnCheckedChangeListener { btn, isOn ->
            if (isOn) {
                binding.questionTextView.visibility = View.VISIBLE
                binding.rg.visibility = View.VISIBLE
                binding.showBtn.visibility = View.VISIBLE
            } else {
                binding.rg.clearCheck()
                binding.questionTextView.visibility = View.INVISIBLE
                binding.rg.visibility = View.INVISIBLE
                binding.showBtn.visibility = View.INVISIBLE
            }
        }



    }
}