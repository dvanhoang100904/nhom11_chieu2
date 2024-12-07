package com.example.nhom11_chieu2.QuanTri

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.example.nhom11_chieu2.R


class ThemNhanVien_MainActivity : AppCompatActivity() {
    private lateinit var edtTenDangNhap: TextInputEditText
    private lateinit var edtHoTen: TextInputEditText
    private lateinit var edtMatKhau: TextInputEditText
    private lateinit var edtNhiemVu: TextInputEditText
    private lateinit var exitButton: ImageView
    private lateinit var btnCreateNhanVien: androidx.appcompat.widget.AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_nhan_vien_main)

        setControl()
        setEvent()
    }

    private fun setControl() {
        edtTenDangNhap = findViewById(R.id.themtendangnhap)
        edtHoTen = findViewById(R.id.themhoten)
        edtMatKhau = findViewById(R.id.themmatkhau)
        edtNhiemVu = findViewById(R.id.themnhiemvu)
        btnCreateNhanVien = findViewById(R.id.btn_createnhanvien)
        exitButton = findViewById(R.id.img_exit)
    }

    private fun setEvent() {
        btnCreateNhanVien.setOnClickListener {
            val tenDangNhap = edtTenDangNhap.text.toString().trim()
            val hoTen = edtHoTen.text.toString().trim()
            val matKhau = edtMatKhau.text.toString().trim()
            val nhiemVu = edtNhiemVu.text.toString().trim()

            if (tenDangNhap.isEmpty() || hoTen.isEmpty() || matKhau.isEmpty() || nhiemVu.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent().apply {
                putExtra("TEN_DANG_NHAP", tenDangNhap)
                putExtra("HO_TEN", hoTen)
                putExtra("MAT_KHAU", matKhau)
                putExtra("NHIEM_VU", nhiemVu)
            }
            setResult(Activity.RESULT_OK, intent)
            Toast.makeText(this, "Thêm $hoTen thành công!", Toast.LENGTH_SHORT).show()
            finish()
        }
        exitButton.setOnClickListener {
            finish()
        }
    }

}