package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhom11_chieu2.QuanTri.QuanTriActivity
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.model.NhanVien

class DangKyActivity : AppCompatActivity() {
    private lateinit var edtHoTen: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtTenDangNhap: EditText
    private lateinit var edtMatKhau: EditText
    private lateinit var btnTao: Button
    private lateinit var ivShowMatKhau: ImageView
    private lateinit var tvDangNhap: TextView
    private var isPasswordVisible = false // Biến trạng thái hiển thị mật khẩu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        ivShowMatKhau.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                edtMatKhau.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                ivShowMatKhau.setImageResource(R.drawable.imgeyehidden) // Icon tắt mắt
            } else {
                edtMatKhau.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                ivShowMatKhau.setImageResource(R.drawable.imgeyeview) // Icon mở mắt
            }

            // Đảm bảo con trỏ vẫn ở cuối văn bản
            edtMatKhau.setSelection(edtMatKhau.text.length)
        }

        btnTao.setOnClickListener {
            val hoTen = edtHoTen.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val tenDangNhap = edtTenDangNhap.text.toString().trim()
            val matKhau = edtMatKhau.text.toString().trim()

            if (hoTen.isEmpty() || email.isEmpty() || tenDangNhap.isEmpty() || matKhau.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhau.length < 6) {
                Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!tenDangNhap.matches(Regex("[a-zA-Z0-9._-]+"))) {
                Toast.makeText(this, "Tên đăng nhập không hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val databaseHelper = DatabaseHelper(this)
            if (databaseHelper.kiemTraEmail(email)) {
                Toast.makeText(this, "Email đã tồn tại!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (databaseHelper.kiemTraTenDangNhap(tenDangNhap)) {
                Toast.makeText(this, "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            val nhanVien = NhanVien(
                ma = 0,
                hoTen = hoTen,
                hinhAnh = 0,
                chucVu = "Nhân viên",
                email = email,
                tenDangNhap = tenDangNhap,
                matKhau = matKhau,
                quyen = 50
            )
            databaseHelper.addNhanVien(nhanVien)
            Toast.makeText(this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show()
            finish()

        }

        tvDangNhap.setOnClickListener {
            finish()
        }
    }

    private fun setControl() {
        edtHoTen = findViewById(R.id.edtHoTen)
        edtEmail = findViewById(R.id.edtEmail)
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap)
        edtMatKhau = findViewById(R.id.edtMatKhau)
        btnTao = findViewById(R.id.btnTao)
        ivShowMatKhau = findViewById(R.id.ivShowMatKhau)
        tvDangNhap = findViewById(R.id.tvDangNhap)
    }
}