package com.example.nhom11_chieu2.QuanTri

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhom11_chieu2.R

class ThemTheLoai_MainActivity : AppCompatActivity() {
    private lateinit var etThemTen: EditText
    private lateinit var btnAdd: Button
    private lateinit var imgExit: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_the_loai_main)
        setControl()
        setEvent()
    }

    private fun setControl() {
        etThemTen = findViewById(R.id.etThemTen)
        btnAdd = findViewById(R.id.btnAdd)
        imgExit = findViewById(R.id.img_exit)
    }

    private fun setEvent() {
        btnAdd.setOnClickListener {
            val tenTheLoai = etThemTen.text.toString().trim()
            if (tenTheLoai.isNotEmpty()) {
                val sharedPref = getSharedPreferences("TheLoaiData", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("newTheLoai", tenTheLoai)
                editor.apply()
                Toast.makeText(this, "Thêm  $tenTheLoai thành công!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Tên thể loại không được để trống!", Toast.LENGTH_SHORT).show()
            }
        }
        imgExit.setOnClickListener {
            finish()
        }
    }
}