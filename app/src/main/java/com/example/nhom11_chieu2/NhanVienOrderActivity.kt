package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

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
    }

    private fun setControl() {
        btnChonBanOrder = findViewById(R.id.btnChonBanOrder)
        btnChonOrder = findViewById(R.id.btnChonOrder)
        btnLuuTruThanhToan = findViewById(R.id.btnLuuTruThanhToan)
        btnDangXuat = findViewById(R.id.btnDangXuat)
    }
}