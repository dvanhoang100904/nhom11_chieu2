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
    private lateinit var rvDanhSachDoUongTT: RecyclerView
    private lateinit var imgBtnChucNang: ImageButton
    private lateinit var imgBtnDangXuat: ImageButton
    private lateinit var tvTieuDeTT: TextView
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
        doUongAdapter = DoUongAdapter(getAllDoUong)
        rvDanhSachDoUongTT.adapter = doUongAdapter
        rvDanhSachDoUongTT.layoutManager = LinearLayoutManager(this)

        val hoTen = databaseHelper.layNhanVienDangNhap()
        tvTieuDeTT.text = "Nhân Viên: $hoTen"

        imgBtnChucNang.setOnClickListener {
            val intentNV = Intent(this, NhanVienOrderActivity::class.java)
            startActivity(intentNV)
        }

        imgBtnDangXuat.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Đăng xuất")
            builder.setMessage("Bạn có chắc chắn muốn đăng xuất không")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                val intentDX = Intent(this, DangNhapActivity::class.java)
                databaseHelper.dangXuat()
                startActivity(intentDX)
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }
    }

    private fun setControl() {
        rvDanhSachDoUongTT = findViewById(R.id.rvDanhSachDoUongTT)
        imgBtnChucNang = findViewById(R.id.imgBtnChucNang)
        imgBtnDangXuat = findViewById(R.id.imgBtnDangXuat)
        tvTieuDeTT = findViewById(R.id.tvTieuDeTT)
    }
}