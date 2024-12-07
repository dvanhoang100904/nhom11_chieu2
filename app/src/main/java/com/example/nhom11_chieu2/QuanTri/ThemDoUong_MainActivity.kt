package com.example.nhom11_chieu2.QuanTri

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhom11_chieu2.R

class ThemDoUong_MainActivity : AppCompatActivity() {
    private lateinit var edtTheLoai: EditText
    private lateinit var edtTen: EditText
    private lateinit var edtGia: EditText
    private lateinit var edtMoTa: EditText
    private lateinit var imgUpload: ImageView
    private lateinit var btnUpload: Button
    private lateinit var btnAdd: Button
    private lateinit var imgExit: ImageView
    private var selectedImageRes: Int? = null

    companion object {
        const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_do_uong_main)

        setControl()
        setEvent()
    }

    private fun setControl() {
        edtTheLoai = findViewById(R.id.et_themtheLoai)
        edtTen = findViewById(R.id.et_themtenDoUong)
        edtGia = findViewById(R.id.et_themgiaDoUong)
        edtMoTa = findViewById(R.id.et_themmoTa)
        imgUpload = findViewById(R.id.img_themuploadCapNhat)
        btnUpload = findViewById(R.id.btn_insertupload)
        btnAdd = findViewById(R.id.btn_Them)
        imgExit = findViewById(R.id.img_exit)
    }

    private fun setEvent() {
        btnUpload.setOnClickListener {
            val intent = Intent(this, ImagePickerActivity::class.java)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
        imgExit.setOnClickListener {
            finish()
        }
        btnAdd.setOnClickListener {
            val theLoai = edtTheLoai.text.toString()
            val ten = edtTen.text.toString()
            val gia = edtGia.text.toString()
            val moTa = edtMoTa.text.toString()

            if (theLoai.isNotEmpty() && ten.isNotEmpty() && gia.isNotEmpty()) {
                val intent = Intent()
                intent.putExtra("theLoai", theLoai)
                intent.putExtra("ten", ten)
                intent.putExtra("gia", gia)
                intent.putExtra("moTa", moTa)

                selectedImageRes?.let { intent.putExtra("imageRes", it) }

                setResult(Activity.RESULT_OK, intent)
                Toast.makeText(this, "Thêm  $ten  thành công", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageRes = data.getIntExtra("selectedImage", -1)
            if (selectedImageRes != -1) {
                // Hiển thị ảnh đã chọn lên ImageView
                imgUpload.setImageResource(selectedImageRes!!)
            }
        }
    }
}
