package com.example.myapplication3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication3.databinding.ActivityMain5Binding

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main5)

        val binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ratingBar1.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            binding.textView.text = "$rating"
        }

        // 증가
        binding.btnInc.setOnClickListener {
            binding.ratingBar2.rating += binding.ratingBar2.stepSize
            binding.ratingBar3.rating += binding.ratingBar3.stepSize
        }

        // 감소
        binding.btnDec.setOnClickListener {
            binding.ratingBar2.rating -= binding.ratingBar2.stepSize
            binding.ratingBar3.rating -= binding.ratingBar3.stepSize
        }

    }
}