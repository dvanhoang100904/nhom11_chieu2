package com.example.nhom11_chieu2.QuanTri

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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

class SuaNhanVienActivity : AppCompatActivity() {
    private lateinit var imgBtnBack: ImageButton
    private lateinit var edtHoTen: EditText
    private lateinit var edtChucVu: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtTenDangNhap: EditText
    private lateinit var edtMatKhau: EditText
    private lateinit var edtQuyen: EditText
    private lateinit var ivHinhAnh: ImageView
    private lateinit var btnUploadHinhAnh: Button
    private lateinit var btnLuuNhanVien: Button
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var hinhAnhChonUpload = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sua_nhan_vien)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        imgBtnBack.setOnClickListener { finish() }

        val hoTen = intent.getStringExtra("hoTen") ?: ""
        val chucVu = intent.getStringExtra("chucVu") ?: ""
        val hinhAnh = intent.getIntExtra("hinhAnh", 0)
        val email = intent.getStringExtra("email") ?: ""
        val tenDangNhap = intent.getStringExtra("tenDangNhap") ?: ""
        val matKhau = intent.getStringExtra("matKhau") ?: ""
        val quyen = intent.getIntExtra("quyen", 0)

        edtHoTen.setText(hoTen)
        edtChucVu.setText(chucVu)
        ivHinhAnh.setImageResource(hinhAnh)
        edtEmail.setText(email)
        edtTenDangNhap.setText(tenDangNhap)
        edtMatKhau.setText(matKhau)
        edtQuyen.setText(quyen.toString())

        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    hinhAnhChonUpload = result.data?.getIntExtra("hinhAnhChonUpload", -1) ?: -1
                    if (hinhAnhChonUpload != -1) {
                        ivHinhAnh.setImageResource(hinhAnhChonUpload)
                    } else {
                        Toast.makeText(
                            this,
                            "Không thể tải ảnh, vui lòng thử lại",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        btnUploadHinhAnh.setOnClickListener {
            val intent = Intent(this, UpLoadHinhAnhActivity::class.java)
            imagePickerLauncher.launch(intent)
        }

        val hinhAnhUpload = intent.getIntExtra("hinhAnhChonUpload", -1)
        if (hinhAnhUpload != -1) {
            ivHinhAnh.setImageResource(hinhAnhUpload)
            hinhAnhChonUpload = hinhAnhUpload
        }

        btnLuuNhanVien.setOnClickListener {
            val hoTenMoi = edtHoTen.text.toString().trim()
            val chucVuMoi = edtChucVu.text.toString().trim()
            val emailMoi = edtEmail.text.toString().trim()
            val tenDangNhapMoi = edtTenDangNhap.text.toString().trim()
            val matKhauMoi = edtMatKhau.text.toString().trim()
            val quyenMoi = edtQuyen.text.toString().trim()
            val hinhAnhMoi = if (hinhAnhChonUpload != -1) hinhAnhChonUpload else hinhAnh

            if (hoTenMoi.isEmpty() || chucVuMoi.isEmpty() || emailMoi.isEmpty() ||
                tenDangNhapMoi.isEmpty() || matKhauMoi.isEmpty() || quyenMoi == null || hinhAnhMoi == -1
            ) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailMoi).matches()) {
                Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quyenMoiInt = quyenMoi.toIntOrNull()
            if (quyenMoiInt == null) {
                Toast.makeText(this, "Quyền phải là một số hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val databaseHelper = DatabaseHelper(this)
            val ma = intent.getIntExtra("ma", -1)
            if (ma != -1) {
                databaseHelper.updateNhanVienByMa(
                    ma = ma,
                    hoTen = hoTenMoi,
                    hinhAnh = hinhAnhMoi,
                    chucVu = chucVuMoi,
                    email = emailMoi,
                    tenDangNhap = tenDangNhapMoi,
                    matKhau = matKhauMoi,
                    quyen = quyenMoiInt
                )
                Toast.makeText(this, "Cập nhật $hoTenMoi thành công!", Toast.LENGTH_SHORT).show()
                val intentCNNV = Intent(this, QuanTriNhanVienActivity::class.java)
                startActivity(intentCNNV)
            }
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
        btnLuuNhanVien = findViewById(R.id.btnLuuNhanVien)
    }
}