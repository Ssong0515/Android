package com.example.app04_intent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app04_intent.databinding.ActivityMain2Binding

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

        val activityResultLuncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    it.data?.let { data ->
                        val addResult = data.getStringExtra("addResult")
                        Log.d("addResult2: ", addResult.toString())

                        Toast.makeText(applicationContext, "result: $addResult", Toast.LENGTH_LONG).show()
                    }
                }
            }

        binding.addBtn.setOnClickListener {
            val intent = Intent(this@MainActivity2, MainActivity2_sub::class.java)
            val first = binding.editTextText1.text.toString()
            val second = binding.editTextText2.text.toString()
            intent.putExtra("first", first)
            intent.putExtra("second", second)
            activityResultLuncher.launch(intent)
        }


    }
}