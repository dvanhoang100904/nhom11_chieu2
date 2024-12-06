package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NhanVienOrderActivity : AppCompatActivity() {
    private lateinit var imgBtnThoat: ImageButton
    private lateinit var btnChonBanOrder: Button
    private lateinit var btnChonOrder: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nhan_vien_order)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        imgBtnThoat.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Thoát")
            builder.setMessage("Bạn có chắc chắn muốn thoát không?")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                val intentThoat = Intent(this, TrangChuActivity::class.java)
                startActivity(intentThoat)
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }
        btnChonBanOrder.setOnClickListener {
            val intentOB = Intent(this, DanhSachViTriBanActivity::class.java)
            startActivity(intentOB)
        }

        btnChonOrder.setOnClickListener {
            val intentO = Intent(this, DanhSachOrderActivity::class.java)
            startActivity(intentO)
        }

    }

    private fun setControl() {
        imgBtnThoat = findViewById(R.id.imgBtnThoat)
        btnChonBanOrder = findViewById(R.id.btnChonBanOrder)
        btnChonOrder = findViewById(R.id.btnChonOrder)
    }
}