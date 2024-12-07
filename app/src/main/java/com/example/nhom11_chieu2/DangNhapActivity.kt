package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nhom11_chieu2.QuanTri.QuanTriActivity
import com.example.nhom11_chieu2.model.DatabaseHelper

class DangNhapActivity : AppCompatActivity() {
    private lateinit var edtTen: EditText
    private lateinit var btnDangNhap: Button
    private lateinit var edtMatKhau: EditText
    private lateinit var ivShowMatKhau: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        var isPasswordVisible = false

        ivShowMatKhau.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            // Đổi inputType của EditText để hiển thị hoặc ẩn mật khẩu
            edtMatKhau.inputType = if (isPasswordVisible) {
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            // Đảm bảo con trỏ vẫn ở cuối văn bản
            edtMatKhau.setSelection(edtMatKhau.text.length)

            // Thay đổi hình ảnh của ImageView dựa trên trạng thái
            ivShowMatKhau.setImageResource(
                if (isPasswordVisible) R.drawable.imgeyehidden else R.drawable.imgeyeview
            )
        }

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

            if (!ten.matches(Regex("[a-zA-Z0-9._-]+"))) {
                Toast.makeText(this, "Tên đăng nhập không hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val databaseHelper = DatabaseHelper(this)
            val quyen = databaseHelper.dangNhap(ten, matKhau)
            if (quyen != null) {
                if (quyen == 50) {
                    val intent = Intent(this, NhanVienOrderActivity::class.java)
                    startActivity(intent)
                } else if (quyen == 100) {
                    val intent = Intent(this, QuanTriActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setControl() {
        edtTen = findViewById(R.id.edtTen)
        edtMatKhau = findViewById(R.id.edtMatKhau)
        btnDangNhap = findViewById(R.id.btnDangNhap)
        ivShowMatKhau = findViewById(R.id.ivShowMatKhau)

    }
}