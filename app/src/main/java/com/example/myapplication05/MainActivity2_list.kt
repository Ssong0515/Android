package com.example.myapplication05

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication05.databinding.Activity2ListBinding
import com.example.myapplication05.databinding.CustomDialog2Binding
import com.example.myapplication05.databinding.DialogInputBinding

class MainActivity2_list : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity2_list)

        val binding = Activity2ListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var memoList2: MutableList<Memo2> = mutableListOf(
            Memo2("홍길동", "010-1111-2222")
        )

        val adapter = MemoAdapter2(memoList2)
        binding.recyclerView2.adapter = adapter
        binding.recyclerView2.layoutManager = LinearLayoutManager(this)

        binding.floatingActionButton.setOnClickListener {
            val customDialog = CustomDialog2Binding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setTitle("입력하시오")
                setView(customDialog.root)
                setPositiveButton("확인") {
                    btn, with ->
                    var name: String = customDialog.inputName.text.toString()
                    var phone: String = customDialog.inputPhone.text.toString()
                    memoList2.add(Memo2(name, phone))
                    adapter.notifyDataSetChanged()
                }
                setNegativeButton("취소", null)
                show()
            }
        }
    }
}

data class Memo2(val name: String, val phone: String)