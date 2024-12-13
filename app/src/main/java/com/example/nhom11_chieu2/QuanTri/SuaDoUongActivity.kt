package com.example.nhom11_chieu2.QuanTri

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.DatabaseHelper

class SuaDoUongActivity : AppCompatActivity() {
    private lateinit var imgBtnBack: ImageButton
    private lateinit var edtTen: EditText
    private lateinit var edtGia: EditText
    private lateinit var edtMoTa: EditText
    private lateinit var edtLoai: EditText
    private lateinit var ivHinhAnh: ImageView
    private lateinit var btnUploadHinhAnh: Button
    private lateinit var btnLuuDoUong: Button
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var hinhAnhChonUpload = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sua_do_uong)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        imgBtnBack.setOnClickListener { finish() }
        val ten = intent.getStringExtra("ten") ?: ""
        val gia = intent.getDoubleExtra("gia", 0.0)
        val hinhAnh = intent.getIntExtra("hinhAnh", 0)
        val moTa = intent.getStringExtra("moTa") ?: ""
        val loai = intent.getStringExtra("loai") ?: ""

        edtTen.setText(ten)
        edtGia.setText(gia.toString())
        ivHinhAnh.setImageResource(hinhAnh)
        edtMoTa.setText(moTa)
        edtLoai.setText(loai)

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

        btnLuuDoUong.setOnClickListener {
            val tenMoi = edtTen.text.toString().trim()
            val giaMoi = edtGia.text.toString().trim()
            val moTaMoi = edtMoTa.text.toString().trim()
            val loaiMoi = edtLoai.text.toString().trim()
            val hinhAnhMoi = if (hinhAnhChonUpload != -1) hinhAnhChonUpload else hinhAnh

            if (tenMoi.isEmpty() || giaMoi.isEmpty() || moTaMoi.isEmpty() || loaiMoi.isEmpty() || hinhAnhMoi == -1) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val giaMoiDouble = giaMoi.toDoubleOrNull()
            if (giaMoiDouble == null) {
                Toast.makeText(this, "Giá phải là một số hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val databaseHelper = DatabaseHelper(this)
            val ma = intent.getIntExtra("ma", -1)
            if (ma != -1) {
                databaseHelper.updateDoUongByMa(
                    ma = ma,
                    ten = tenMoi,
                    hinhAnh = hinhAnhMoi,
                    gia = giaMoiDouble,
                    moTa = moTaMoi,
                    loai = loaiMoi,
                )
                Toast.makeText(this, "Cập nhật $tenMoi thành công!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, QuanTriDoUongActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setControl() {
        imgBtnBack = findViewById(R.id.imgBtnBack)
        edtTen = findViewById(R.id.edtTen)
        edtGia = findViewById(R.id.edtGia)
        edtMoTa = findViewById(R.id.edtMoTa)
        edtLoai = findViewById(R.id.edtLoai)
        ivHinhAnh = findViewById(R.id.ivHinhAnh)
        btnUploadHinhAnh = findViewById(R.id.btnUploadHinhAnh)
        btnLuuDoUong = findViewById(R.id.btnLuuDoUong)

    }
}