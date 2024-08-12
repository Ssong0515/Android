package com.example.myapplication04

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.RatingBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication04.databinding.ActivityMainSubBinding
import kotlin.math.floor

class MainActivity_sub : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main_sub)
        val binding = ActivityMainSubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val voteResult = intent.getIntArrayExtra("countVote")
        val imageResult = intent.getStringExtra("imageName")

        var ratingBtns = arrayOf<RatingBar>(
            binding.rbar1,
            binding.rbar2,
            binding.rbar3,
            binding.rbar4,
            binding.rbar5,
            binding.rbar6,
            binding.rbar7,
            binding.rbar8,
            binding.rbar9
        )

        val images = arrayOf<Int>(
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
            R.drawable.pic9
        )

        var maxPos = 0
        if (voteResult != null) {
            for ((idx, voteValue) in voteResult.withIndex()) {
                ratingBtns[idx].rating = voteValue.toFloat()
                if (voteResult[idx] > voteResult[maxPos]) {
                    maxPos = idx
                }
            }
        }

        binding.tvTop.text = "우승그림: Img${maxPos + 1}"
        binding.ivTop.setImageResource(images[maxPos])

        binding.btnReturn.setOnClickListener {
            finish()
        }
    }
}