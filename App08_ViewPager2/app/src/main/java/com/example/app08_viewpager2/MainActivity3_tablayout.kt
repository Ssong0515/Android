package com.example.app08_viewpager2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.app08_viewpager2.databinding.Activity3TablayoutBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity3_tablayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = Activity3TablayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contentAdapter3: FragmentStateAdapter = ContentAdapter3(this)
        binding.viewPager3.adapter = contentAdapter3

        val tabElement: List<String> = mutableListOf("tab1", "tab2", "tab3")
        TabLayoutMediator(binding.tabLayout, binding.viewPager3){ tab, position ->
//            val textView = TextView(this@MainActivity3_tablayout)
//            textView.text = tabElement[position]
//            tab.customView = textView
            tab.text = binding.tabLayout.getTabAt(position)?.text
        }.attach()

    }
}