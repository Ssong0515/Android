package com.example.diarymanagerapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DiaryDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "diaryTest", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTableSQL = """
            create table diaryTest (
            id integer primary key autoincrement,
            title text,
            content text,
            date text
            )
        """.trimIndent()

        p0?.execSQL(createTableSQL)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists diaryTest")
        onCreate(p0)
    }

    fun addDiary(title: String, content: String, date: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
            put("date", date)
        }
        return db.insert("diaryTest", null, values)
    }

    fun getAllDiaries(): Cursor {
        val db = readableDatabase
        val getAllSQL = """
            select * from diaryTest
            order by date desc
        """.trimIndent()
        return db.rawQuery(getAllSQL, null)
    }
}