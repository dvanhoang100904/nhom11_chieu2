package com.example.nhom11_chieu2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
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

    private var luuMaXacThuc: String? = null
    private var luuEmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quen_mat_khau)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        btnGuiMa.setOnClickListener {
            val email = edtEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val databaseHelper = DatabaseHelper(this)
            if (databaseHelper.kiemTraEmail(email)) {
                luuMaXacThuc = taoNgauNhienMaXacThuc()
                luuEmail = email
                Toast.makeText(this, "Mã xác thực: $luuMaXacThuc", Toast.LENGTH_LONG).show()
                edtMaXacThuc.visibility = View.VISIBLE
                edtMatKhauMoi.visibility = View.VISIBLE
                btnXacNhan.visibility = View.VISIBLE
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

            if (maXacThuc == luuMaXacThuc) {
                Toast.makeText(
                    this,
                    "Mã xác thực đã đúng, vui lòng cập nhật mật khẩu mới",
                    Toast.LENGTH_LONG
                ).show()
                if (matKhauMoi.isEmpty()) {
                    Toast.makeText(this, "Vui lòng nhập mật khẩu mới", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
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
    }
}