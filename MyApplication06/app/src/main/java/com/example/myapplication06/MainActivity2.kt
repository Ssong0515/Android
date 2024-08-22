package com.example.myapplication06

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication06.databinding.ActivityMain2Binding

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

        val persons = mutableListOf<Person>()
        val adapter = PersonAdapter(persons)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        for (i in 0..6) {
            persons.add(Person("강감찬", "010-1234-1234"))
        }

        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                println(result.data)
                val data = result.data
                val name = data?.getStringExtra("name") ?: ""
                val phone = data?.getStringExtra("phone") ?: ""
                persons.add(Person(name, phone))
                adapter.notifyDataSetChanged()
            }
        }

        binding.addBtn.setOnClickListener {
            var intent = Intent(this, MainActivity2_sub::class.java)
//            startActivity(intent)
            resultLauncher.launch(intent)
        }

    }
}