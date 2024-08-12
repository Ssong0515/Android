package com.example.myapplication04

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.ImageViewCompat
import com.example.myapplication04.databinding.ActivityMainBinding

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

        val countVote = IntArray(9)
        val imageViews = arrayOf<ImageView>(
            binding.img1,
            binding.img2,
            binding.img3,
            binding.img4,
            binding.img5,
            binding.img6,
            binding.img7,
            binding.img8,
            binding.img9
        )

        for ((idx, img) in imageViews.withIndex()) {
            img.setOnClickListener {
                countVote[idx]++
                Toast.makeText(this, "그림${idx + 1}: 총 ${countVote[idx]} 표", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnResult.setOnClickListener {
            val intent = Intent(this, MainActivity_sub::class.java)

            intent.putExtra("countVote", countVote)

            startActivity(intent)
        }
    }
}