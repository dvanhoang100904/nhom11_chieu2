package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.adapter.DoUongAdapter
import com.example.nhom11_chieu2.model.DatabaseHelper

class TrangChuActivity : AppCompatActivity() {
    private lateinit var doUongAdapter: DoUongAdapter
    private lateinit var rvDanhSachDoUong: RecyclerView
    private lateinit var tvTieuDeTT: TextView
    private lateinit var imgBtnChonBan: ImageButton
    private lateinit var imgBtnLuuTru: ImageButton
    private lateinit var imgBtnDangXuat: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trang_chu)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)
        val limit = 6
        val getAllDoUong = databaseHelper.getAllDoUongNgauNhien(limit)
        doUongAdapter = DoUongAdapter(getAllDoUong) { doUong ->
            val intentCT = Intent(this, ChiTietActivity::class.java).apply {
                putExtra("maDoUong", doUong.ma)
            }
            startActivity(intentCT)
        }
        rvDanhSachDoUong.adapter = doUongAdapter
        rvDanhSachDoUong.layoutManager = LinearLayoutManager(this)

        val hoTen = databaseHelper.layNhanVienDangNhap()
        tvTieuDeTT.text = "Nhân Viên: $hoTen"

        imgBtnChonBan.setOnClickListener {
            val intentDSVTB = Intent(this, DanhSachViTriBanActivity::class.java)
            startActivity(intentDSVTB)
        }

        imgBtnLuuTru.setOnClickListener {
            val intentLTTT = Intent(this, LuuTruThanhToanActivity::class.java)
            startActivity(intentLTTT)
        }

        imgBtnDangXuat.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Đăng xuất")
            builder.setMessage("Bạn có chắc chắn muốn đăng xuất không")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                val intentDN = Intent(this, DangNhapActivity::class.java)
                databaseHelper.dangXuat()
                startActivity(intentDN)
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }
    }

    private fun setControl() {
        rvDanhSachDoUong = findViewById(R.id.rvDanhSachDoUong)
        tvTieuDeTT = findViewById(R.id.tvTieuDeTT)
        imgBtnChonBan = findViewById(R.id.imgBtnChonBan)
        imgBtnLuuTru = findViewById(R.id.imgBtnLuuTru)
        imgBtnDangXuat = findViewById(R.id.imgBtnDangXuat)

    }
}