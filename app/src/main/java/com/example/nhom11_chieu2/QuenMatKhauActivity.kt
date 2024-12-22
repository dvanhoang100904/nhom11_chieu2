package com.example.nhom11_chieu2

import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhom11_chieu2.model.DatabaseHelper
import java.util.Random

class QuenMatKhauActivity : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var btnGuiMa: Button
    private lateinit var edtMaXacThuc: EditText
    private lateinit var btnXacNhan: Button
    private lateinit var edtMatKhauMoi: EditText
    private lateinit var tvDangNhap: TextView
    private lateinit var ivShowMatKhauMoi: ImageView
    private var luuMaXacThuc: String? = null
    private var luuEmail: String? = null
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quen_mat_khau)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        tvDangNhap.setOnClickListener {
            finish()
        }
        ivShowMatKhauMoi.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                edtMatKhauMoi.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                ivShowMatKhauMoi.setImageResource(R.drawable.imgeyehidden)
            } else {
                edtMatKhauMoi.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                ivShowMatKhauMoi.setImageResource(R.drawable.imgeyeview)
            }
        }

        btnGuiMa.setOnClickListener {
            val email = edtEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val databaseHelper = DatabaseHelper(this)
            if (databaseHelper.kiemTraEmail(email)) {
                luuMaXacThuc = taoNgauNhienMaXacThuc()
                luuEmail = email
                Toast.makeText(this, "Mã xác thực: $luuMaXacThuc", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Email không tồn tại", Toast.LENGTH_SHORT).show()
            }
        }

        btnXacNhan.setOnClickListener {
            val maXacThuc = edtMaXacThuc.text.toString().trim()
            val matKhauMoi = edtMatKhauMoi.text.toString().trim()

            if (maXacThuc.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mã xác thực", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhauMoi.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mật khẩu mới", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhauMoi.length < 6) {
                Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (maXacThuc == luuMaXacThuc) {
                val databaseHelper = DatabaseHelper(this)
                databaseHelper.capNhatMatKhau(luuEmail!!, matKhauMoi)
                Toast.makeText(this, "Mật khẩu đã được cập nhật", Toast.LENGTH_LONG).show()
                finish()

            } else {
                Toast.makeText(this, "Mã xác thực chưa đúng!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun taoNgauNhienMaXacThuc(): String {
        val rd = Random()
        return (100000 + rd.nextInt(900000)).toString()
    }

    private fun setControl() {
        edtEmail = findViewById(R.id.edtEmail)
        edtMatKhauMoi = findViewById(R.id.edtMatKhauMoi)
        btnGuiMa = findViewById(R.id.btnGuiMa)
        edtMaXacThuc = findViewById(R.id.edtMaXacThuc)
        btnXacNhan = findViewById(R.id.btnXacNhan)
        tvDangNhap = findViewById(R.id.tvDangNhap)
        ivShowMatKhauMoi = findViewById(R.id.ivShowMatKhauMoi)
    }
}