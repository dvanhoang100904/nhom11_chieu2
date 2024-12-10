package com.example.nhom11_chieu2.QuanTri

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.model.NhanVien

class ThemNhanVienActivity : AppCompatActivity() {
    private lateinit var imgBtnBack: ImageButton
    private lateinit var edtHoTen: EditText
    private lateinit var edtChucVu: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtTenDangNhap: EditText
    private lateinit var edtMatKhau: EditText
    private lateinit var edtQuyen: EditText
    private lateinit var ivHinhAnh: ImageView
    private lateinit var btnUploadHinhAnh: Button
    private lateinit var btnThemNhanVien: Button
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var hinhAnhChonUpload = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_them_nhan_vien)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        imgBtnBack.setOnClickListener { finish() }

        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    hinhAnhChonUpload = result.data?.getIntExtra("hinhAnhUpload", -1) ?: -1
                    if (hinhAnhChonUpload != -1) {
                        ivHinhAnh.setImageResource(hinhAnhChonUpload)
                    }
                }
            }

        btnUploadHinhAnh.setOnClickListener {
            val intent = Intent(this, UpLoadHinhAnhActivity::class.java)
            imagePickerLauncher.launch(intent)
        }

        val hinhAnhUpload = intent.getIntExtra("hinhAnhUpload", -1)
        if (hinhAnhUpload != -1) {
            ivHinhAnh.setImageResource(hinhAnhUpload)
            hinhAnhChonUpload = hinhAnhUpload
        }

        btnThemNhanVien.setOnClickListener {
            val hoTen = edtHoTen.text.toString().trim()
            val chucVu = edtChucVu.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val tenDangNhap = edtTenDangNhap.text.toString().trim()
            val matKhau = edtMatKhau.text.toString().trim()
            val quyen = edtQuyen.text.toString().trim()
            val hinhAnh = hinhAnhChonUpload

            if (hoTen.isEmpty() || chucVu.isEmpty() || email.isEmpty() ||
                tenDangNhap.isEmpty() || matKhau.isEmpty() || quyen.isEmpty()
            ) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quyenInt = quyen.toIntOrNull()
            if (quyenInt == null) {
                Toast.makeText(this, "Quyền phải là một số hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nhanVien = NhanVien(
                ma = 0,
                hoTen = hoTen,
                hinhAnh = hinhAnh,
                chucVu = chucVu,
                email = email,
                tenDangNhap = tenDangNhap,
                matKhau = matKhau,
                quyen = quyenInt
            )
            val databaseHelper = DatabaseHelper(this)
            val kq = databaseHelper.addNhanVien(nhanVien)
            Log.d("kq", "kq: $kq")
            Toast.makeText(this, "thêm $hoTen thành công", Toast.LENGTH_SHORT).show()
            val intentTNV = Intent(this, QuanTriNhanVienActivity::class.java)
            startActivity(intentTNV)

        }
    }

    private fun setControl() {
        imgBtnBack = findViewById(R.id.imgBtnBack)
        edtHoTen = findViewById(R.id.edtHoTen)
        edtChucVu = findViewById(R.id.edtChucVu)
        edtEmail = findViewById(R.id.edtEmail)
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap)
        edtMatKhau = findViewById(R.id.edtMatKhau)
        edtQuyen = findViewById(R.id.edtQuyen)
        ivHinhAnh = findViewById(R.id.ivHinhAnh)
        btnUploadHinhAnh = findViewById(R.id.btnUploadHinhAnh)
        btnThemNhanVien = findViewById(R.id.btnThemNhanVien)

    }
}