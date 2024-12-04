package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

            // Kiểm tra thông tin đăng nhập (Ví dụ: Kiểm tra với tên và mật khẩu đã lưu trong SharedPreferences)
            val userRole = getUserRole(ten, matKhau)  // Giả sử có phương thức này kiểm tra

            if (userRole != null) {
                // Lưu vai trò người dùng vào SharedPreferences
                saveUserRole(userRole)

                // Đăng nhập thành công
                Toast.makeText(this, "chào mừng ${ten} đã đăng nhập", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TrangChuActivity::class.java)
                startActivity(intent)
            } else {
                // Đăng nhập thất bại
                Toast.makeText(
                    this,
                    "Tên đăng nhập hoặc mật khẩu không chính xác",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getUserRole(ten: String, matKhau: String): String? {
        return if (ten == "daovanhoang" && matKhau == "123456") {
            "nhanvien"
        } else if (ten == "phamducchuan" && matKhau == "123456") {
            "quantri"
        } else if (ten == "huynhngocdan" && matKhau == "123456") {
            "quantri"
        } else {
            null

        }
    }

    private fun saveUserRole(role: String) {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userRole", role)
        editor.apply()
    }

    private fun setControl() {
        edtTen = findViewById(R.id.edtTen)
        edtMatKhau = findViewById(R.id.edtMatKhau)
        btnDangNhap = findViewById(R.id.btnDangNhap)
        ivShowMatKhau = findViewById(R.id.ivShowMatKhau)

    }
}