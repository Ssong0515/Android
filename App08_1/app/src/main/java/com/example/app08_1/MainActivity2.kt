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
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.app08_1.databinding.ActivityMain2Binding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val customAdapter2: FragmentStateAdapter = CustomAdapter2(this)
        binding.viewPage.adapter = customAdapter2

        val tabBarTexts = listOf("ViewA", "ViewB", "ViewC", "ViewD")
        TabLayoutMediator(binding.tabLayout2, binding.viewPage){ tab, position ->
            val textView = TextView(this@MainActivity2)
            textView.text = tabBarTexts[position]
            textView.gravity = Gravity.CENTER
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            textView.setTypeface(null, Typeface.BOLD)
            tab.customView = textView
        }.attach()

    }
}