package com.example.nhom11_chieu2.QuanTri

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhom11_chieu2.R
import com.google.android.material.textfield.TextInputEditText

class SuaNhanVien_MainActivity : AppCompatActivity() {
    private lateinit var edtTenDangNhap: TextInputEditText
    private lateinit var edtHoTen: TextInputEditText
    private lateinit var edtMatKhau: TextInputEditText
    private lateinit var edtNhiemVu: TextInputEditText
    private lateinit var btnUpdateNhanVien: Button
    private lateinit var exitButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sua_nhan_vien_main)

        setControl()
        setEvent()
    }

    private fun setControl() {
        edtTenDangNhap = findViewById(R.id.suatendangnhap)
        edtHoTen = findViewById(R.id.suahoten)
        edtMatKhau = findViewById(R.id.suamatkhau)
        edtNhiemVu = findViewById(R.id.suanhiemvu)
        btnUpdateNhanVien = findViewById(R.id.btn_updatenhanvien)
        exitButton = findViewById(R.id.img_exit)
        val nhanVien = intent
        edtTenDangNhap.setText(nhanVien.getStringExtra("TEN_DANG_NHAP"))
        edtHoTen.setText(nhanVien.getStringExtra("HO_TEN"))
        edtMatKhau.setText(nhanVien.getStringExtra("MAT_KHAU"))
        edtNhiemVu.setText(nhanVien.getStringExtra("NHIEM_VU"))
    }

    private fun setEvent() {
        btnUpdateNhanVien.setOnClickListener {
            val tenDangNhap = edtTenDangNhap.text.toString().trim()
            val hoTen = edtHoTen.text.toString().trim()
            val matKhau = edtMatKhau.text.toString().trim()
            val nhiemVu = edtNhiemVu.text.toString().trim()

            if (tenDangNhap.isEmpty() || hoTen.isEmpty() || matKhau.isEmpty() || nhiemVu.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultIntent = intent.apply {
                putExtra("TEN_DANG_NHAP", tenDangNhap)
                putExtra("HO_TEN", hoTen)
                putExtra("MAT_KHAU", matKhau)
                putExtra("NHIEM_VU", nhiemVu)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            Toast.makeText(this, "Cập nhật $hoTen thành công!", Toast.LENGTH_SHORT).show()
            finish()
        }
        exitButton.setOnClickListener {
            finish()
        }
    }
}
