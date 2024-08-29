package com.example.app09_db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app09_db.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    lateinit var sqLiteDatabase: SQLiteDatabase
    lateinit var myDbHelper: MyDbHelper
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myDbHelper = MyDbHelper(this)

        // DB 설정
        sqLiteDatabase = myDbHelper.writableDatabase

        // 오늘 날짜
        val calendar = Calendar.getInstance()
        val cYear = calendar.get(Calendar.YEAR)
        val cMonth = calendar.get(Calendar.MONTH)
        val cDay = calendar.get(Calendar.DAY_OF_MONTH)
        var diaryDate = "$cYear-${cMonth+1}-$cDay"
        binding.edtDiary.setText(readDiary(diaryDate)) // 오늘 날짜
        
        // datePicker2 에서 날짜 선택할 때
        binding.datePicker2.init(cYear, cMonth, cDay){ datePicker, year, month, day ->
            diaryDate = "${year}-${month+1}-${day}"
            binding.edtDiary.setText(readDiary(diaryDate))
        }

        // 쓰기 버튼
        binding.btnWrite.setOnClickListener {
            sqLiteDatabase = myDbHelper.writableDatabase
            var sql = "insert into myDiary values('${diaryDate}','${binding.edtDiary.text}')"

            if (binding.btnWrite.text.equals("수정하기")){
                sql = "update myDiary set content='${binding.edtDiary.text}'" +
                        " where diaryDate = '${diaryDate}'"
            }

            sqLiteDatabase.execSQL(sql)
            sqLiteDatabase.close()
        }
    }

    private fun readDiary(diaryDate: String): String {
        var strResult = ""
        sqLiteDatabase = myDbHelper.readableDatabase
        val sql = "select * from myDiary where diaryDate = '${diaryDate}"
        var cursor: Cursor = sqLiteDatabase.rawQuery(sql, null)
        if (cursor.moveToNext()){
            strResult = cursor.getString(1)
            binding.btnWrite.text = "수정하기"
        } else {
            binding.btnWrite.text = "새로저장"
            binding.edtDiary.setText("")
            binding.edtDiary.hint = "일기 없음"
        }
        return strResult
    }

    // 내부 클래스
    class MyDbHelper(context: Context): SQLiteOpenHelper(context, "myDB", null, 1) {
        override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
            sqLiteDatabase.execSQL("create table if not exists " +
                    "myDiary(diaryDate char(10), " +
                    "content varchar(500))")
        }

        override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, p1: Int, p2: Int) {

        }

    }
}