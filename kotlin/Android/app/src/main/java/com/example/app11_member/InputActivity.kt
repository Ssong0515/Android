package com.example.app11_member

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app11_member.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        if (intent.getStringExtra("button").toString() == "add") {
            binding.btnAdd.text = "추가"
        } else {
            binding.btnAdd.text = "수정"
            binding.editName.setText(intent.getStringExtra("name"))
            binding.editPhone.setText(intent.getStringExtra("phone"))
            binding.editEmail.setText(intent.getStringExtra("email"))
        }

        binding.btnAdd.setOnClickListener {

            val intent = Intent()
            if (binding.btnAdd.text == "수정") {
                intent.putExtra("button", "수정")
                intent.putExtra("position", intent.getIntExtra("position", 0))

            } else {
                intent.putExtra("button", "추가")
            }

            intent.putExtra("name", binding.editName.text.toString())
            intent.putExtra("phone", binding.editPhone.text.toString())
            intent.putExtra("email", binding.editEmail.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

    }
}