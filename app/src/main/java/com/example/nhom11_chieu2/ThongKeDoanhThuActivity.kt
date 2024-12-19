package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.adapter.ThongKeAdapter
import com.example.nhom11_chieu2.model.DatabaseHelper

class ThongKeDoanhThuActivity : AppCompatActivity() {
    private lateinit var rvDanhSachThongKe: RecyclerView
    private lateinit var thongKeAdapter: ThongKeAdapter
    private lateinit var btnHienThiThongKe: Button
    private lateinit var imgBtnThoat: ImageButton
    private lateinit var tvTongDoanhThu: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thong_ke_doanh_thu)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)

        val tongDoanhThu = databaseHelper.getTongDoanhThu()
        val tongDoanhThuFormat = String.format("%,.0f", tongDoanhThu)
        tvTongDoanhThu.text = "Tổng Doanh Thu: $tongDoanhThuFormat VNĐ"

        btnHienThiThongKe.setOnClickListener {
            val getThongKeDoanhThu = databaseHelper.getThongKeDoanhThu()
            thongKeAdapter = ThongKeAdapter(getThongKeDoanhThu)
            rvDanhSachThongKe.adapter = thongKeAdapter
            rvDanhSachThongKe.layoutManager = LinearLayoutManager(this)
            thongKeAdapter.notifyDataSetChanged()
        }

        imgBtnThoat.setOnClickListener {
            val intentTT = Intent(this, TrangChuActivity::class.java)
            startActivity(intentTT)
        }
    }

    private fun setControl() {
        rvDanhSachThongKe = findViewById(R.id.rvDanhSachThongKe)
        btnHienThiThongKe = findViewById(R.id.btnHienThiThongKe)
        imgBtnThoat = findViewById(R.id.imgBtnThoat)
        tvTongDoanhThu = findViewById(R.id.tvTongDoanhThu)
    }
}