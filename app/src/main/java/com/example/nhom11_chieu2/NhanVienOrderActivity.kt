package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.model.NhanVien

class NhanVienOrderActivity : AppCompatActivity() {
    private lateinit var btnChonBanOrder: Button
    private lateinit var btnChonOrder: Button
    private lateinit var btnLuuTruThanhToan: Button
    private lateinit var btnDangXuat: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nhan_vien_order)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        btnChonBanOrder.setOnClickListener {
            val intentOB = Intent(this, DanhSachViTriBanActivity::class.java)
            startActivity(intentOB)
        }

        btnChonOrder.setOnClickListener {
            val intentO = Intent(this, DanhSachOrderActivity::class.java)
            startActivity(intentO)
        }

        btnLuuTruThanhToan.setOnClickListener {
            val intentLTTT = Intent(this, LuuTruThanhToanActivity::class.java)
            startActivity(intentLTTT)
        }

        btnDangXuat.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Đăng xuất")
            builder.setMessage("Bạn có chắc chắn muốn đăng xuất không")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                val intentDX = Intent(this, DangNhapActivity::class.java)
                startActivity(intentDX)
                finish()
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }

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


    private fun setControl() {
        btnChonBanOrder = findViewById(R.id.btnChonBanOrder)
        btnChonOrder = findViewById(R.id.btnChonOrder)
        btnLuuTruThanhToan = findViewById(R.id.btnLuuTruThanhToan)
        btnDangXuat = findViewById(R.id.btnDangXuat)
    }
}