package com.example.myapplication04

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication04.databinding.ActivityDialog2Binding
import com.example.myapplication04.databinding.ActivityDialogBinding
import com.example.myapplication04.databinding.ActivityMain2Binding
import com.example.myapplication04.databinding.ProgressbarBinding

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

        binding.btnBasic.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("기본 Alert Dialog")
                setMessage("안드로이드에서 제공하는 기본 Alert Dialog 사용하기")
                setPositiveButton("확인", null)
                show()
            }
        }

        binding.btnCustom.setOnClickListener {
            val dialogBinding = ActivityDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setTitle("커스텀다이얼로그")
                setView(dialogBinding.root)
                setPositiveButton("확인", { btn, btnKind ->

                    var msg = "여자"

                    if (dialogBinding.manBtn.isChecked) {
                        msg = "남자"
                    }
                    Toast.makeText(application, "${msg}가 선택 되었습니다", Toast.LENGTH_SHORT).show()
                })
                show()
            }
        }

        binding.btnCustom2.setOnClickListener {
            val dialogBinding = ActivityDialog2Binding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setTitle("커스텀 다이얼로그")
                setView(dialogBinding.root)
                setNegativeButton("취소", null)
                setPositiveButton("확인", { btn, btnKind ->

                    var result1 = dialogBinding.editText1.text
                    var result2 = dialogBinding.editText2.text

                    binding.result1.text = result1
                    binding.result2.text = result2

                })
                show()
            }
        }

        // 데이트 픽커 다이어로그
        binding.btnDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            var listener = DatePickerDialog.OnDateSetListener {
                datePicker, i, i2, i3 ->
                binding.result1.text = "현재 날짜: ${i}년 ${i2+1}월 ${i3}일"

            }
            var picker = DatePickerDialog(this, listener, year, month, day)
            picker.show()
        }

        // 타임 픽커 다이어로그
        binding.btnTimePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)
            val second = calendar.get(Calendar.SECOND)
            var listener = TimePickerDialog.OnTimeSetListener {
                timePicker, i, i2 ->
                binding.result2.text = "${i}시 ${i2}분"
            }
            var picker = TimePickerDialog(this, listener, hour, minute, false)
            picker.show()
        }

        // 프로그래스바 ==> AlertDialog 에 띄우기
        var builder = AlertDialog.Builder(this)
        builder.setTitle("프로그래스바")
        builder.setIcon(R.mipmap.ic_launcher)
        val pbinding = ProgressbarBinding.inflate(layoutInflater)
        builder.setView(pbinding.root)
        builder.show()

    }
}