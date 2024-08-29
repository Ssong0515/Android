package com.example.diarymanagerapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.diarymanagerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DiaryAddedListener {

    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var diaryDatabaseHelper: DiaryDatabaseHelper
    lateinit var diaryAdapter: DiaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(
            this,
            binding.main,
            R.string.drawer_open,
            R.string.drawer_close,
            )
        supportActionBar?.title = "Diary2"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toggle.syncState()

        diaryDatabaseHelper = DiaryDatabaseHelper(this)
        diaryAdapter = DiaryAdapter(emptyList())
        binding.recycleView.adapter = diaryAdapter

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddDiaryActivity::class.java))
        }

        loadDiaries()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDiaryAdded() {
        loadDiaries()
    }

    private fun loadDiaries(){

        var cursor = diaryDatabaseHelper.getAllDiaries()
        val diaris = mutableListOf<Diary>()

        with(cursor){
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow("id"))
                val title = getString(getColumnIndexOrThrow("title"))
                val content = getString(getColumnIndexOrThrow("content"))
                val date = getString(getColumnIndexOrThrow("date"))
                diaris.add(Diary(id, title, content, date))
            }
        }

        diaryAdapter.updateData(diaris)

    }

}