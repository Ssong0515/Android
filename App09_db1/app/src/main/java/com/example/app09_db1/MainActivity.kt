package com.example.app09_db1

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app09_db1.databinding.ActivityMainBinding
import java.io.Console

interface MainDelegate {
    fun selectRow(memo: Memo, position: Int)
}

class MainActivity : AppCompatActivity(), MainDelegate {

    lateinit var helper: SqliteHelper
    lateinit var binding: ActivityMainBinding
    private var updateMemo: Memo? = null
    private var updatePosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        helper = SqliteHelper(this, "memo", 1)

        val adapter = RecyclerAdapter()
        adapter.listData = helper.selectMemo()
        adapter.helper = helper
        adapter.mainDelegate = this
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)


        binding.buttonSave.setOnClickListener {
            if (binding.editMemo.text.toString().isNotEmpty() && updateMemo == null) {
                val memo = Memo(
                    null,
                    binding.editMemo.text.toString(),
                    System.currentTimeMillis()
                )

                helper.insertMemo(memo)

                adapter.listData.clear()
                adapter.listData.addAll(helper.selectMemo())
                adapter.notifyDataSetChanged()

                binding.editMemo.setText("")
            } else {

                val newMemo = Memo(
                    updateMemo?.num,
                    binding.editMemo.text.toString(),
                    System.currentTimeMillis()
                )
                helper.updateMemo(newMemo)

                adapter.listData.clear()
                adapter.listData.addAll(helper.selectMemo())
                adapter.notifyDataSetChanged()

                binding.editMemo.setText("")
                binding.buttonSave.text = "저장"
            }
        }
    }

    override fun selectRow(memo: Memo, position: Int) {
        binding.editMemo.setText(memo.content)
        binding.buttonSave.text = "수정"
        updateMemo = memo
        updatePosition = position
    }
}