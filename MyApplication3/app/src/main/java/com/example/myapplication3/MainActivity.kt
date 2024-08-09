package com.example.myapplication3

import android.content.Context
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { // , CompoundButton.OnCheckedChangeListener {
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

        // 상속 받아서 this로 사용 방식
//        binding.checkBox1.setOnCheckedChangeListener(this)
        // 내부 클래스 방식
        binding.checkBox2.setOnCheckedChangeListener(MyHandler(this@MainActivity))
        // 람다함수 : SAM 방식
        binding.checkBox3.setOnCheckedChangeListener { compoundButton, b ->
            Toast.makeText(this, "세번째 체크박스 선택 람다함수 사용", Toast.LENGTH_LONG).show()
        }
        binding.button1.setOnClickListener {
            Toast.makeText(this, "버튼1 클릭", Toast.LENGTH_LONG).show()
        }
        binding.button2.setOnLongClickListener {
            Toast.makeText(this, "버튼2 롱클릭", Toast.LENGTH_LONG).show()
            true
        }
    }

    class MyHandler(val context: Context) :CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        Toast.makeText(context, "두번째 체크박스 선택 class 사용", Toast.LENGTH_LONG).show()
        }

    }

//    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
//        Toast.makeText(this, "첫번째 체크박스 선택 this 사용", Toast.LENGTH_LONG).show()
//    }
}