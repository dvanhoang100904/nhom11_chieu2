package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DangNhapActivity : AppCompatActivity() {
    private lateinit var edtTen: EditText
    private lateinit var btnDangNhap: Button
    private lateinit var edtMatKhau: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        btnDangNhap.setOnClickListener {

            val ten = edtTen.text.toString().trim()
            val matKhau = edtMatKhau.text.toString().trim()

            if (ten.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhau.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhau.length < 6) {
                Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
            val intentDangNhap = Intent(this, TrangChuActivity::class.java)
            startActivity(intentDangNhap)
        }
    }

    private fun setControl() {
        edtTen = findViewById(R.id.edtTen)
        edtMatKhau = findViewById(R.id.edtMatKhau)
        btnDangNhap = findViewById(R.id.btnDangNhap)
    }
}