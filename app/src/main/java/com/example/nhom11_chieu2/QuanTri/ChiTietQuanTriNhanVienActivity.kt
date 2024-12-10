package com.example.nhom11_chieu2.QuanTri

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.DatabaseHelper

class ChiTietQuanTriNhanVienActivity : AppCompatActivity() {
    private lateinit var tvTieuDeCTQT: TextView
    private lateinit var tvMaCTQT: TextView
    private lateinit var tvHoTenCTQT: TextView
    private lateinit var ivHinhAnhCTQT: ImageView
    private lateinit var tvChucVuCTQT: TextView
    private lateinit var tvEmailCTQT: TextView
    private lateinit var tvTenDangNhapCTQT: TextView
    private lateinit var tvMatKhauCTQT: TextView
    private lateinit var tvQuyenCTQT: TextView
    private lateinit var imgBtnBack: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chi_tiet_quan_tri_nhan_vien)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        imgBtnBack.setOnClickListener { finish() }

        val ma = intent.getIntExtra("ma", -1)
        if (ma != -1) {
            val databaseHelper = DatabaseHelper(this)
            val nhanVien = databaseHelper.getNhanViengByMa(ma)
            if (nhanVien != null) {
                tvTieuDeCTQT.text = "Chi Tiết ${nhanVien.hoTen}"
                tvMaCTQT.text = "Mã: ${nhanVien.ma}"
                tvHoTenCTQT.text = "Họ Tên: ${nhanVien.hoTen}"
                ivHinhAnhCTQT.setImageResource(nhanVien.hinhAnh)
                tvChucVuCTQT.text = "Chức Vụ: ${nhanVien.chucVu}"
                tvEmailCTQT.text = "Email: ${nhanVien.email} "
                tvTenDangNhapCTQT.text = "Tên đăng nhập: ${nhanVien.tenDangNhap}"
                tvMatKhauCTQT.text = "Mật khẩu: ${nhanVien.matKhau}"
                tvQuyenCTQT.text = "Quyền: ${nhanVien.quyen} "
            } else {
                Toast.makeText(this, "Không tìm thấy nhân viên", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        }

    }

    private fun setControl() {
        tvTieuDeCTQT = findViewById(R.id.tvTieuDeCTQT)
        tvMaCTQT = findViewById(R.id.tvMaCTQT)
        tvHoTenCTQT = findViewById(R.id.tvHoTenCTQT)
        ivHinhAnhCTQT = findViewById(R.id.ivHinhAnhCTQT)
        tvChucVuCTQT = findViewById(R.id.tvChucVuCTQT)
        tvEmailCTQT = findViewById(R.id.tvEmailCTQT)
        tvTenDangNhapCTQT = findViewById(R.id.tvTenDangNhapCTQT)
        tvMatKhauCTQT = findViewById(R.id.tvMatKhauCTQT)
        tvQuyenCTQT = findViewById(R.id.tvQuyenCTQT)
        imgBtnBack = findViewById(R.id.imgBtnBack)
    }
}