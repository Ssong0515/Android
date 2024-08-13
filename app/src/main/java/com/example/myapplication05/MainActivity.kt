// app/src/main/java/com/example/myapplication05/MainActivity.kt
package com.example.myapplication05

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication05.databinding.ActivityMainBinding
import com.example.myapplication05.databinding.DialogInputBinding
import kotlinx.coroutines.selects.select

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btn1.setOnClickListener {
            Log.d("MainActivity", "Button clicked")
            val items = arrayOf("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).apply {
                setTitle("item test")
                setIcon(android.R.drawable.ic_dialog_alert)
                setMultiChoiceItems(
                    items,
                    booleanArrayOf(true, false, true, false), // items 배열의 길이와 일치하도록 수정
                    object : DialogInterface.OnMultiChoiceClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                            Log.d("MultiChoiceItems", "${items[which]}이 ${if (isChecked) "선택 되었습니다" else "선택 해제되었습니다."}")
                        }
                    }
                )
                setPositiveButton("확인", null)
                setNegativeButton("취소", null)
                show() // 다이얼로그를 표시합니다.
            }
        }

        binding.btn2.setOnClickListener {
            val dialogBinding = DialogInputBinding.inflate(layoutInflater)
            var txt = ""
            dialogBinding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == dialogBinding.radioMale.id) {
                    txt == "남자"
                } else {
                    txt == "여자"
                }
                AlertDialog.Builder(this).run {
                    setTitle("custom")
                    setView(dialogBinding.root)
                    setNegativeButton("취소", null)
                    setPositiveButton("확인") { dialog, which ->
                        Log.d("Dialog", "$which")
                        Log.d("custom", "${txt} 선택")
                    }
                    show()
                }
            }
        }
    }
}