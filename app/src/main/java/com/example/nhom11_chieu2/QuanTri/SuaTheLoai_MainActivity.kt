package com.example.nhom11_chieu2.QuanTri

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhom11_chieu2.R

class SuaTheLoai_MainActivity : AppCompatActivity() {
    private lateinit var edtTenTheLoai: EditText
    private lateinit var btnUpdateTheLoai: AppCompatButton
    private lateinit var imgExit: ImageView
    private var theLoaiId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sua_the_loai_main)

        val id = intent?.getIntExtra("id", -1) ?: -1
        val tenTheLoai = intent?.getStringExtra("tenTheLoai") ?: ""

        if (id == -1 || tenTheLoai.isEmpty()) {
            Toast.makeText(this, "Dữ liệu không hợp lệ!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        setControl()
        setEvent()
    }

    private fun setControl() {
        edtTenTheLoai = findViewById(R.id.etSuaTenTheLoai)
        btnUpdateTheLoai = findViewById(R.id.btnUpdateTheLoai)

        theLoaiId = intent.getIntExtra("id", -1)
        val tenTheLoai = intent.getStringExtra("tenTheLoai")
        edtTenTheLoai.setText(tenTheLoai)
        imgExit = findViewById(R.id.img_exit)
    }

    private fun setEvent() {
        btnUpdateTheLoai.setOnClickListener {
            val updatedName = edtTenTheLoai.text.toString().trim()

            if (updatedName.isEmpty()) {
                Toast.makeText(this, "Tên thể loại không được để trống!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultIntent = Intent().apply {
                putExtra("id", theLoaiId)  // id
                putExtra("tenTheLoai", updatedName)  //update
            }
            setResult(RESULT_OK, resultIntent)
            Toast.makeText(this, "Cập nhật thể loại $updatedName thành công!", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
        imgExit.setOnClickListener {
            finish()
        }
    }
}
