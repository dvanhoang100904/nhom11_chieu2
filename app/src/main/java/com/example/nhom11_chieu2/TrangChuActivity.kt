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
    private lateinit var btnChucNangQuanTri: Button
    private lateinit var btnDangXuat: Button
    private var userRole: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trang_chu)
        setControl()
        setEvent()
        userRole = getUserRole()

        updateRole(userRole)
    }

    private fun setEvent() {
        btnChucNangNhanVien.setOnClickListener {
            if (userRole == "nhanvien") {
                val intentCNNV = Intent(this, NhanVienOrderActivity::class.java)
                startActivity(intentCNNV)
            } else {
                Toast.makeText(
                    this,
                    "Bạn không có quyền truy cập chức năng này",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnChucNangQuanTri.setOnClickListener {
            if (userRole == "quantri") {
                Toast.makeText(this, "Quản Trị", Toast.LENGTH_SHORT).show()
                // val intentCNQT = Intent(this, QuanTriActivity::class.java)
                // startActivity(intentCNQL)
            } else {
                Toast.makeText(
                    this,
                    "Bạn không có quyền truy cập chức năng này",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnDangXuat.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Đăng Xuất")
            builder.setMessage("Bạn có chắc chắn muốn đăng xuất không?")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                val intentDangXuat = Intent(this, DangNhapActivity::class.java)
                startActivity(intentDangXuat)
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }
    }

    private fun getUserRole(): String? {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        return sharedPreferences.getString("userRole", null)  // Lấy vai trò người dùng đã lưu
    }

    private fun updateRole(role: String?) {
        when (role) {
            "nhanvien" -> {
                btnChucNangQuanTri.isEnabled = false
            }

            "quanly" -> {
                btnChucNangNhanVien.isEnabled = false
            }

            else -> {
                // Nếu không có vai trò hợp lệ
            }
        }
    }

    private fun setControl() {
        btnChucNangNhanVien = findViewById(R.id.btnChucNangNhanVien)
        btnChucNangQuanTri = findViewById(R.id.btnChucNangQuanTri)
        btnDangXuat = findViewById(R.id.btnDangXuat)
    }
}