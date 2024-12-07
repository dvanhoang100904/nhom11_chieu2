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

class SuaDoUong_MainActivity : AppCompatActivity() {
    private lateinit var etTheLoai: EditText
    private lateinit var etTen: EditText
    private lateinit var etGia: EditText
    private lateinit var etMoTa: EditText
    private lateinit var imgDrink: ImageView
    private lateinit var btnSave: Button
    private lateinit var btnChangeImage: Button
    private lateinit var imgExit: ImageView

    private var position: Int = -1
    private var selectedImageRes: Int = R.drawable.imgcaphe1

    companion object {
        const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sua_do_uong_main)

        setControl()
        setEvent()

        // Nhận dữ liệu từ Intent
        position = intent.getIntExtra("position", -1)
        val theLoai = intent.getStringExtra("theLoai") ?: ""
        val ten = intent.getStringExtra("ten") ?: ""
        val gia = intent.getStringExtra("gia") ?: ""
        val moTa = intent.getStringExtra("moTa") ?: ""
        selectedImageRes = intent.getIntExtra("hinhAnh", R.drawable.imgcaphe1)

        // Hiển thị dữ liệu lên giao diện
        etTheLoai.setText(theLoai)
        etTen.setText(ten)
        etGia.setText(gia)
        etMoTa.setText(moTa)
        imgDrink.setImageResource(selectedImageRes)
    }

    private fun setControl() {
        etTheLoai = findViewById(R.id.et_suatheLoai)
        etTen = findViewById(R.id.et_suatenDoUong)
        etGia = findViewById(R.id.et_suagiaSuaDoUong)
        etMoTa = findViewById(R.id.et_suamoTa)
        imgDrink = findViewById(R.id.img_suauploadCapNhat)
        btnSave = findViewById(R.id.btn_capNhatDoUong)
        btnChangeImage = findViewById(R.id.btn_updateupload)
        imgExit = findViewById(R.id.img_exit)
    }

    private fun setEvent() {
        imgExit.setOnClickListener {
            finish()
        }
        btnChangeImage.setOnClickListener {
            // Mở màn hình chọn ảnh
            val intent = Intent(this, ImagePickerActivity::class.java)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        btnSave.setOnClickListener {
            // Thu thập dữ liệu chỉnh sửa
            val editedTheLoai = etTheLoai.text.toString()
            val editedTen = etTen.text.toString()
            val editedGia = etGia.text.toString()
            val editedMoTa = etMoTa.text.toString()

            if (editedTheLoai.isNotEmpty() && editedTen.isNotEmpty() && editedGia.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("position", position)
                resultIntent.putExtra("theLoai", editedTheLoai)
                resultIntent.putExtra("ten", editedTen)
                resultIntent.putExtra("gia", editedGia)
                resultIntent.putExtra("moTa", editedMoTa)
                resultIntent.putExtra("hinhAnh", selectedImageRes)

                setResult(Activity.RESULT_OK, resultIntent)
                Toast.makeText(this, "Cập nhật $editedTen thành công", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val newImageRes = data.getIntExtra("selectedImage", -1)
            if (newImageRes != -1) {
                selectedImageRes = newImageRes
                imgDrink.setImageResource(selectedImageRes)
            }
        }
    }
}
