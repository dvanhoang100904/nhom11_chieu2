package com.example.nhom11_chieu2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NhanVienActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nhan_vien)
        setEvent()
    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)
        val kiemTraNhanVien = databaseHelper.getAllNhanVien()
        if (kiemTraNhanVien.isEmpty()) {
            val danhSachNhanVien = getDanhSachNhanVien()
            for (nhanVien in danhSachNhanVien) {
                databaseHelper.addNhanVien(nhanVien)
            }
            Log.d("db", "Danh sách nhân viên: ${databaseHelper.getAllNhanVien().size}")
        }
    }

    private fun getDanhSachNhanVien(): List<NhanVien> {
        return listOf(
            NhanVien(
                1,
                "Đào Văn Hoàng",
                "Nhân Viên",
                "daovanhoang11@gmail.com",
                "daovanhoang11",
                "123456",
                50
            ),
            NhanVien(
                2,
                "Huỳnh Ngọc Dân",
                "Quản Trị",
                "huynhngodan11@gmail.com",
                "huynhngocdan11",
                "123456",
                100
            ),
        )
    }

}