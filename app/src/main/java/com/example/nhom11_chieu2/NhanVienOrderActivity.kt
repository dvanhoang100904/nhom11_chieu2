package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class NhanVienOrderActivity : AppCompatActivity() {
    private lateinit var btnTrangChu: Button
    private lateinit var btnChonBanOrder: Button
    private lateinit var btnChonOrder: Button
    private lateinit var btnLuuTruThanhToan: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nhan_vien_order)
        setControl()
        setEvent()
    }

    private fun setEvent() {


        btnTrangChu.setOnClickListener {
            val intentTT = Intent(this, TrangChuActivity::class.java)
            startActivity(intentTT)
        }

        btnChonBanOrder.setOnClickListener {
            val intentCBO = Intent(this, DanhSachViTriBanActivity::class.java)
            startActivity(intentCBO)
        }

        btnChonOrder.setOnClickListener {
            val intentCO = Intent(this, DanhSachOrderActivity::class.java)
            startActivity(intentCO)
        }

        btnLuuTruThanhToan.setOnClickListener {
            val intentLTTT = Intent(this, LuuTruThanhToanActivity::class.java)
            startActivity(intentLTTT)
        }

    }

    private fun setControl() {
        btnTrangChu = findViewById(R.id.btnTrangChu)
        btnChonBanOrder = findViewById(R.id.btnChonBanOrder)
        btnChonOrder = findViewById(R.id.btnChonOrder)
        btnLuuTruThanhToan = findViewById(R.id.btnLuuTruThanhToan)
    }
}