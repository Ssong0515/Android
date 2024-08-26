package com.example.app08_1

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app08_1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val stringList = mutableListOf<String>(
            "뷰A",
            "뷰B",
            "뷰C",
            "뷰D",
        )
        binding.viewPager.adapter = CustomAdapter(stringList)

        val tabBarTexts = listOf("ViewA", "ViewB", "ViewC", "ViewD")
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            val textView = TextView(this@MainActivity)
            textView.text = tabBarTexts[position]
            textView.gravity = Gravity.CENTER
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            textView.setTypeface(null, Typeface.BOLD)
            tab.customView = textView
        }.attach()

    }
}