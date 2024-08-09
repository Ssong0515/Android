package com.example.myapplication3

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication3.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main3)

        val binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.showSwitch.setOnCheckedChangeListener { btn, on ->
            if (on) {
                binding.firstTextView.visibility = View.VISIBLE
                binding.rdoGroup.visibility = View.VISIBLE
                binding.imgView1.visibility = View.VISIBLE

            } else {
                binding.firstTextView.visibility = View.GONE
                binding.rdoGroup.visibility = View.GONE
                binding.imgView1.visibility = View.GONE
                binding.rdoGroup.clearCheck()
            }
        }

        val radioArray: Array<RadioButton> = arrayOf(binding.rdo1, binding.rdo2, binding.rdo3)
        val imgArray: Array<Int> = arrayOf(R.drawable.img11, R.drawable.img2, R.drawable.img33)
        for ((index, rdo) in radioArray.withIndex()) {
//            Log.d("radioArray i ::", rdo.toString())
            rdo.setOnClickListener {
                binding.imgView1.setImageResource(imgArray[index])
            }
        }

//        binding.rdoGroup.setOnCheckedChangeListener { btn, id ->
//
//            var imgSrc = R.drawable.img1
//
//            when (id) {
//                R.id.rdo1 -> imgSrc = R.drawable.img1
//                R.id.rdo2 -> imgSrc = R.drawable.img2
//                R.id.rdo3 -> imgSrc = R.drawable.img3
//            }
//
//            binding.imgView1.setImageResource(imgSrc)
//        }


    }
}