package com.example.diarymanagerapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diarymanagerapp.databinding.ActivityAddDiaryBinding

interface DiaryAddedListener {
    fun onDiaryAdded()
}

class AddDiaryActivity : AppCompatActivity() {

    private lateinit var diaryDatabaseHelper: DiaryDatabaseHelper
    private var diaryAddedListener: DiaryAddedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAddDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        diaryDatabaseHelper = DiaryDatabaseHelper(this)
        diaryAddedListener = MainActivity()

        binding.btnAdd.setOnClickListener {
            diaryDatabaseHelper.addDiary(
                binding.textTitle.text.toString(),
                binding.textContent.text.toString(),
                binding.editDate.text.toString()
            )
            diaryAddedListener?.onDiaryAdded()
            finish()
        }

    }
}