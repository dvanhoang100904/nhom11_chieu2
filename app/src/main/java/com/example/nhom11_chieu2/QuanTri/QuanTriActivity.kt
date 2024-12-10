package com.example.nhom11_chieu2.QuanTri

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nhom11_chieu2.DangNhapActivity
import com.example.nhom11_chieu2.R

class QuanTriActivity : AppCompatActivity() {
    private lateinit var imgBtnQTNhanVien: ImageButton
    private lateinit var imgBtnQTDoUong: ImageButton
    private lateinit var btnQTNhanVien: Button
    private lateinit var btnQTDoUong: Button
    private lateinit var btnDangXuat: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quan_tri)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        imgBtnQTNhanVien.setOnClickListener {
            val intent = Intent(this, QuanTriNhanVienActivity::class.java)
            startActivity(intent)
        }

        imgBtnQTDoUong.setOnClickListener {
            val intent = Intent(this, QuanTriDoUongActivity::class.java)
            startActivity(intent)
        }

        btnQTNhanVien.setOnClickListener {
            val intent = Intent(this, QuanTriDoUongActivity::class.java)
            startActivity(intent)
        }

        btnQTDoUong.setOnClickListener {
            val intent = Intent(this, QuanTriNhanVienActivity::class.java)
            startActivity(intent)
        }

        btnDangXuat.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Đăng xuất")
            builder.setMessage("Bạn có chắc chắn muốn đăng xuất không?")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                val intentDX = Intent(this, DangNhapActivity::class.java)
                startActivity(intentDX)
                finish()
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }
    }

    private fun setControl() {
        imgBtnQTNhanVien = findViewById(R.id.imgBtnQTNhanVien)
        imgBtnQTDoUong = findViewById(R.id.imgBtnQTDoUong)
        btnQTNhanVien = findViewById(R.id.btnQTNhanVien)
        btnQTDoUong = findViewById(R.id.btnQTDoUong)
        btnDangXuat = findViewById(R.id.btnDangXuat)

    }
}