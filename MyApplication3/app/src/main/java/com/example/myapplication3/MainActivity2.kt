package com.example.myapplication3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication3.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main2)

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.checkBox1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                binding.textView.visibility = View.VISIBLE
                binding.rgGroup.visibility = View.VISIBLE
                binding.button1.visibility = View.VISIBLE
            } else {
                binding.textView.visibility = View.GONE
                binding.rgGroup.visibility = View.GONE
                binding.button1.visibility = View.GONE
                binding.imgView1.visibility = View.GONE
            }
        }

        binding.button1.setOnClickListener {
            val selectedRadio = binding.rgGroup.checkedRadioButtonId
            var imgName: Int = -1

            when (selectedRadio) {
                R.id.rdo1 -> { imgName = R.drawable.img11 }
                R.id.rdo2 -> { imgName = R.drawable.img2 }
                R.id.rdo3 -> { imgName = R.drawable.img33 }
                else -> { Toast.makeText(this, "선택 해 주세요", Toast.LENGTH_SHORT).show() }
            }

            binding.imgView1.setImageResource(imgName)
            binding.imgView1.visibility = View.VISIBLE
        }
    }
}