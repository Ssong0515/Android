package com.example.myapplication07.threeFrag

import android.os.Bundle
import android.view.SurfaceControl.Transaction
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myapplication07.ListFragment
import com.example.myapplication07.R
import com.example.myapplication07.databinding.ActivityMain2Binding
import com.example.myapplication07.databinding.FragmentThreeBinding

class MainActivity2 : AppCompatActivity() {

    val binding by lazy { ActivityMain2Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listener = ClickHandler()
//        binding.firstBtn.setOnClickListener(listener)
//        binding.secondBtn.setOnClickListener(listener)
//        binding.thirdBtn.setOnClickListener(listener)

        with(binding) {
            firstBtn.setOnClickListener(listener)
            secondBtn.setOnClickListener(listener)
            thirdBtn.setOnClickListener(listener)
        }

//        binding.firstBtn.setOnClickListener {
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.add(R.id.fragmentContainer, ThreeFragment())
//            transaction.commit()
//        }
//
//        binding.secondBtn.setOnClickListener {
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragmentContainer, ThreeFragment2())
//            transaction.commit()
//        }
//
//        binding.thirdBtn.setOnClickListener {
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragmentContainer, ThreeFragment3())
//            transaction.commit()
//        }
    }

    inner class ClickHandler(): View.OnClickListener {
        var fr:Fragment? = null
        override fun onClick(p0: View?) {
            when(p0!!.id) {
                binding.firstBtn.id -> fr = ThreeFragment()
                binding.secondBtn.id -> fr = ThreeFragment2()
                binding.thirdBtn.id -> fr = ThreeFragment3()
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fr!!)
                .addToBackStack(null)
                .commit()

        }

    }

}