package com.example.app06

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
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

        val personList = mutableListOf<Person>()

        for (i in 0..6) {
            personList.add(Person("강감찬$i", "1231412321"))
        }

        val personAdapter = PersonAdapter(personList)
        binding.recyclerView.adapter = personAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        var position: Int = 0

        // 어뎁터에 있는 리스너를 호출
        personAdapter.onItemClickListener = object : OnItemClickListener{
            override fun onItemClick(pos: Int) {
                position = pos
                binding.edtName.setText(personList[pos].name)
                binding.edtPhone.setText(personList[pos].phone)
//                binding.addBtn.isEnabled = true
//                binding.deleteBtn.isEnabled = true
                Toast.makeText(application, "taped: $pos", Toast.LENGTH_SHORT).show()
            }

        }

        binding.addBtn.setOnClickListener {
            val name = binding.edtName.text.toString()
            val phone = binding.edtPhone.text.toString()
//            personList.add(Person(name, phone))
//            personAdapter.notifyDataSetChanged()
            personAdapter.addItem(Person(name, phone))
            binding.edtName.setText("")
            binding.edtPhone.setText("")
        }

        binding.editBtn.setOnClickListener {
            val name = binding.edtName.text.toString()
            val phone = binding.edtPhone.text.toString()
            personAdapter.editItem(Person(name, phone), position)
            binding.edtName.setText("")
            binding.edtPhone.setText("")
        }


        binding.deleteBtn.setOnClickListener {
            personAdapter.deleteItem(position)
            binding.edtName.setText("")
            binding.edtPhone.setText("")
        }



    }
}