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
    private var userRole: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trang_chu)
        setControl()
        setEvent()

        // Lấy vai trò người dùng từ SharedPreferences (hoặc từ Intent)
        userRole = getUserRoleFromPreferences()  // Giả sử có phương thức này

        // Cập nhật giao diện dựa trên vai trò
        updateUIBasedOnRole(userRole)
    }

    private fun setEvent() {

        btnChucNangNhanVien.setOnClickListener {
            if (userRole == "nhanvien") {
                Toast.makeText(this, "Danh sách vị trí bàn", Toast.LENGTH_SHORT).show()
                val intentCNNV = Intent(this, DanhSachViTriBanActivity::class.java)
                startActivity(intentCNNV)
            } else {
                Toast.makeText(
                    this,
                    "Bạn không có quyền truy cập chức năng này",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnChucNangQuanLy.setOnClickListener {
            if (userRole == "quanly") {
                Toast.makeText(this, "Quản lý", Toast.LENGTH_SHORT).show()
                // val intentCNQL = Intent(this, QuanLyActivity::class.java)
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
                Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show()
                val intentDangXuat = Intent(this, DangNhapActivity::class.java)
                startActivity(intentDangXuat)
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }
    }

    // Giả sử bạn có phương thức lấy vai trò người dùng từ SharedPreferences
    private fun getUserRoleFromPreferences(): String? {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        return sharedPreferences.getString("userRole", null)  // Lấy vai trò người dùng đã lưu
    }

    // Cập nhật giao diện trang chủ dựa trên vai trò người dùng
    private fun updateUIBasedOnRole(role: String?) {
        when (role) {
            "nhanvien" -> {
                btnChucNangQuanLy.isEnabled = false  // Vô hiệu hóa nút Quản lý cho nhân viên
            }

            "quanly" -> {
                btnChucNangNhanVien.isEnabled = false  // Vô hiệu hóa nút Nhân viên cho quản lý
            }

            else -> {
                // Nếu không có vai trò hợp lệ, bạn có thể xử lý ở đây
            }
        }
    }

    private fun setControl() {
        btnChucNangNhanVien = findViewById(R.id.btnChucNangNhanVien)
        btnChucNangQuanLy = findViewById(R.id.btnChucNangQuanLy)
        btnDangXuat = findViewById(R.id.btnDangXuat)
    }
}