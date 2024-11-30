package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TrangChuActivity : AppCompatActivity() {
    private lateinit var btnChucNangNhanVien: Button
    private lateinit var btnChucNangQuanLy: Button
    private lateinit var btnDangXuat: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trang_chu)
        setControl()
        setEvent()
    }

    private fun setEvent() {

        btnChucNangNhanVien.setOnClickListener {
            Toast.makeText(this, "Danh sách vị trí bàn", Toast.LENGTH_SHORT).show()
            val intentCNNV = Intent(this, DanhSachViTriBanActivity::class.java)
            startActivity(intentCNNV)
        }

        btnChucNangQuanLy.setOnClickListener {
            Toast.makeText(this, "Quản lý", Toast.LENGTH_SHORT).show()
//            val intentCNQL = Intent(this, QuanLyActivity::class.java)
//            startActivity(intentCNQL)
        }

        btnDangXuat.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Đăng Xuất")
            builder.setMessage("Bạn có chắc chắn muốn đăng xuất không?")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show()
                val intentDangXuat = Intent(this, DangNhapActivity::class.java)
                startActivity(intentDangXuat)
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }
    }

    private fun setControl() {
        btnChucNangNhanVien = findViewById(R.id.btnChucNangNhanVien)
        btnChucNangQuanLy = findViewById(R.id.btnChucNangQuanLy)
        btnDangXuat = findViewById(R.id.btnDangXuat)
    }
}