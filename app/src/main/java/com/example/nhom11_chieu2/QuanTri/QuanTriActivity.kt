package com.example.nhom11_chieu2.QuanTri

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nhom11_chieu2.DangNhapActivity
import com.example.nhom11_chieu2.R

class QuanTriActivity : AppCompatActivity() {
    private lateinit var btnQuanLiNhanVien: Button
    private lateinit var btnQuanLiMenu: Button
    private lateinit var btnDangXuat: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quan_tri)
        setControl()
        setEvent()
    }

    private fun setControl() {
        btnQuanLiNhanVien = findViewById(R.id.btnQuanLiNhanVien)
        btnQuanLiMenu = findViewById(R.id.btnQuanLiMenu)
        btnDangXuat = findViewById(R.id.btnDangXuat)

    }

    private fun setEvent() {
        btnQuanLiNhanVien.setOnClickListener {
            val intent = Intent(this, QuanLiNhanVien_MainActivity::class.java)
            startActivity(intent)
        }
        btnQuanLiMenu.setOnClickListener {
            val intent = Intent(this, QuanLiMenu_MainActivity::class.java)
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
}