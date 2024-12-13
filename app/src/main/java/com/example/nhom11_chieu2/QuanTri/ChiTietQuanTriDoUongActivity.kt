package com.example.nhom11_chieu2.QuanTri

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.DatabaseHelper

class ChiTietQuanTriDoUongActivity : AppCompatActivity() {
    private lateinit var imgBtnBack: ImageButton
    private lateinit var tvTieuDeCTQT: TextView
    private lateinit var tvMaCTQTDoUong: TextView
    private lateinit var ivHinhAnhCTQTDoUong: ImageView
    private lateinit var tvTenCTQTDoUong: TextView
    private lateinit var tvGiaCTQTDoUong: TextView
    private lateinit var tvLoaiCTQTDoUong: TextView
    private lateinit var tvMoTaCTQTDoUong: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_quan_tri_do_uong)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        imgBtnBack.setOnClickListener { finish() }
        val ma = intent.getIntExtra("ma", -1)
        if (ma != -1) {
            val databaseHelper = DatabaseHelper(this)
            val doUong = databaseHelper.getDoUongByMa(ma)
            if (doUong != null) {
                tvTieuDeCTQT.text = "Chi Tiết ${doUong.ten}"
                ivHinhAnhCTQTDoUong.setImageResource(doUong.hinhAnh)
                tvMaCTQTDoUong.text = "Mã: ${doUong.ma}"
                tvTenCTQTDoUong.text = "Tên: ${doUong.ten}"
                tvGiaCTQTDoUong.text = "Giá: ${formatGia(doUong.gia)}"
                tvLoaiCTQTDoUong.text = "Loại: ${doUong.loai}"
                tvMoTaCTQTDoUong.text = "Mô tả: ${doUong.moTa}"
            } else {
                Toast.makeText(this, "Không tìm thấy đồ uống", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun formatGia(gia: Double): String {
        val decimalFormat = java.text.DecimalFormat("#,###")
        return decimalFormat.format(gia) + " VNĐ"
    }

    private fun setControl() {
        imgBtnBack = findViewById(R.id.imgBtnBack)
        tvTieuDeCTQT = findViewById(R.id.tvTieuDeCTQT)
        tvMaCTQTDoUong = findViewById(R.id.tvMaCTQTDoUong)
        ivHinhAnhCTQTDoUong = findViewById(R.id.ivHinhAnhCTQTDoUong)
        tvTenCTQTDoUong = findViewById(R.id.tvTenCTQTDoUong)
        tvGiaCTQTDoUong = findViewById(R.id.tvGiaCTQTDoUong)
        tvLoaiCTQTDoUong = findViewById(R.id.tvLoaiCTQTDoUong)
        tvMoTaCTQTDoUong = findViewById(R.id.tvMoTaCTQTDoUong)
    }
}