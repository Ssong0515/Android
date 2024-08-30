package com.example.app10_member

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app10_member.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding
    lateinit var position: String
    lateinit var num: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAddEdit.setText(intent.getStringExtra("btn") ?: "추가")
        binding.editName.setText(intent.getStringExtra("name"))
        binding.editPhone.setText(intent.getStringExtra("phone"))
        binding.editTextEmail.setText(intent.getStringExtra("email"))

        position = intent.getStringExtra("position").toString()

        binding.btnAddEdit.setOnClickListener {
            val name = binding.editName.text.toString()
            val phone = binding.editPhone.text.toString()
            val email = binding.editTextEmail.text.toString()

            val returnIntent = Intent()
            returnIntent.putExtra("btn", binding.btnAddEdit.text.toString())
            returnIntent.putExtra("position", intent.getStringExtra("position"))
            returnIntent.putExtra("id", intent.getStringExtra("id"))
            returnIntent.putExtra("name", name)
            returnIntent.putExtra("phone", phone)
            returnIntent.putExtra("email", email)
            setResult(RESULT_OK, returnIntent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

    }
}