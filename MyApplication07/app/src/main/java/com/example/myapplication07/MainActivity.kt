package com.example.myapplication07

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myapplication07.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var listFragment: ListFragment

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

        setFragment()
        binding.sendBtn.setOnClickListener {
            listFragment.setValue("전달 값!!")
        }
    }

    private fun setFragment() {
        listFragment = ListFragment()
        var bundle = Bundle()
        bundle.putString("key1", "title")
        bundle.putInt("key2", 20240823)
        listFragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, listFragment)
        transaction.commit()
    }

    fun goDetail(){
        val detailFragment = DetailFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun goBack(){
        onBackPressedDispatcher.onBackPressed()
    }

}