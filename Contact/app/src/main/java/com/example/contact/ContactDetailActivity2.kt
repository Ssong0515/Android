package com.example.contact

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contact.databinding.ActivityContactDetailBinding

class ContactDetailActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contact = intent.getSerializableExtra("contact") as? Contact

        binding.nameTextView.text = contact?.name
        binding.phoneTextView.text = contact?.phone
    }
}