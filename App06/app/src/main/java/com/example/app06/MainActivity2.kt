package com.example.app06

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app06.databinding.ActivityMain2Binding

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

        val person2List = mutableListOf<Person2>()
        for (i in 0..6) {
            person2List.add(Person2("${i}번째 사람", "${i}번째 메세지"))
        }

        var messageAdapter = MessageAdapter(person2List)
        binding.recycleView.adapter = messageAdapter
        binding.recycleView.layoutManager = LinearLayoutManager(this)

        messageAdapter.messageInter = object :MessageInter{
            override fun handleTap(position: Int) {
                Toast.makeText(application, "friend 클릭${position}", Toast.LENGTH_SHORT).show()
            }

            override fun handleLongTap(position: Int) {
                person2List.removeAt(position)
            }

        }

    }
}