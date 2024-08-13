package com.example.myapplication05

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication05.databinding.Activity2MemoBinding

class MainActivity2_memo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity2_memo)
        val binding = Activity2MemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. 데이터 생성
        val data: MutableList<Memo> = loadData()

        // 2. 어뎁터 생성
        var adapter = CustomAdapter()
        adapter.listData = data

        // 3. recycler view 에 어뎁터 연결
        binding.recyclerView.adapter = adapter

        // 4. 리사이클러뷰에 레이아웃 설정
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    // loadData 함수
    fun loadData(): MutableList<Memo> {
        val data: MutableList<Memo> = mutableListOf()
        for (i in 1..100) {
            val title = "RecyclerTest ${i + 1}"
            val date = System.currentTimeMillis()
            var memo = Memo(i, title, date)
            data.add(memo)
        }
        return data
    }

}