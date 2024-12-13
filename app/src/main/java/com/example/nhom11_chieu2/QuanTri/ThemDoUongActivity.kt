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

import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.model.DoUong

class ThemDoUongActivity : AppCompatActivity() {
    private lateinit var imgBtnBack: ImageButton
    private lateinit var edtTen: EditText
    private lateinit var edtGia: EditText
    private lateinit var edtMoTa: EditText
    private lateinit var edtLoai: EditText
    private lateinit var ivHinhAnh: ImageView
    private lateinit var btnThemDoUong: Button
    private lateinit var btnUploadHinhAnh: Button
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var hinhAnhChonUpload = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_do_uong)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        imgBtnBack.setOnClickListener {
            val intent = Intent(this, QuanTriDoUongActivity::class.java)
            startActivity(intent)
        }

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

        btnThemDoUong.setOnClickListener {
            val ten = edtTen.text.toString().trim()
            val gia = edtGia.text.toString().trim()
            val moTa = edtMoTa.text.toString().trim()
            val loai = edtLoai.text.toString().trim()
            val hinhAnh = hinhAnhChonUpload

            if (ten.isEmpty() || gia.isEmpty() || moTa.isEmpty() || loai.isEmpty() || hinhAnh == -1) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val giaDouBle = gia.toDoubleOrNull()
            if (giaDouBle == null) {
                Toast.makeText(this, "Giá phải là một số hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val doUong = DoUong(
                ma = 0,
                ten = ten,
                gia = giaDouBle,
                moTa = moTa,
                loai = loai,
                hinhAnh = hinhAnh
            )
            val databaseHelper = DatabaseHelper(this)
            databaseHelper.addDoUong(doUong)
            Toast.makeText(this, "thêm $ten thành công", Toast.LENGTH_SHORT).show()
            edtTen.setText("")
            edtGia.setText("")
            edtMoTa.setText("")
            edtLoai.setText("")
            ivHinhAnh.setImageResource(0)
            edtTen.requestFocus()

        }
    }

    private fun setControl() {
        imgBtnBack = findViewById(R.id.imgBtnBack)
        edtTen = findViewById(R.id.edtTen)
        edtGia = findViewById(R.id.edtGia)
        edtMoTa = findViewById(R.id.edtMoTa)
        edtLoai = findViewById(R.id.edtLoai)
        ivHinhAnh = findViewById(R.id.ivHinhAnh)
        btnThemDoUong = findViewById(R.id.btnThemDoUong)
        btnUploadHinhAnh = findViewById(R.id.btnUploadHinhAnh)
    }

}