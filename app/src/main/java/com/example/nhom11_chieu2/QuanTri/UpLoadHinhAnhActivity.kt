package com.example.nhom11_chieu2.QuanTri

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.adapter.UpLoadAdapter
import com.example.nhom11_chieu2.R

class UpLoadHinhAnhActivity : AppCompatActivity() {
    private lateinit var rvUpLoadHinhAnh: RecyclerView
    private lateinit var ivHinhAnhChonUpload: ImageView
    private lateinit var btnQuayLai: Button
    private lateinit var btnDongY: Button
    private var hinhAnhChon: Int? = null

    private val hinhAnh = listOf(
        R.drawable.imgcaphe1,
        R.drawable.imgcaphe2,
        R.drawable.imgcaphe3,
        R.drawable.imgtrasua1,
        R.drawable.imgtrasua2,
        R.drawable.imgsinhto1,
        R.drawable.imgsinhto2,
        R.drawable.imgemployeecoffe1,
        R.drawable.imgemployeecoffe2,
        R.drawable.imgemployeecoffe3,
        R.drawable.imgemployeecoffe5,
        R.drawable.imgemployeecoffe6,

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_load_hinh_anh)

        setControl()
        setEvent()
    }

    private fun setEvent() {
        val uploadAdapter = UpLoadAdapter(hinhAnh) { hinhAnhChon ->
            ivHinhAnhChonUpload.setImageResource(hinhAnhChon)
            this.hinhAnhChon = hinhAnhChon
        }
        rvUpLoadHinhAnh.adapter = uploadAdapter

        btnQuayLai.setOnClickListener { finish() }

        btnDongY.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("hinhAnhUpload", hinhAnhChon)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun setControl() {
        rvUpLoadHinhAnh = findViewById(R.id.rvUpLoadHinhAnh)
        rvUpLoadHinhAnh.layoutManager = GridLayoutManager(this, 3)
        ivHinhAnhChonUpload = findViewById(R.id.ivHinhAnhChonUpload)
        btnQuayLai = findViewById(R.id.btnQuayLai)
        btnDongY = findViewById(R.id.btnDongY)
    }
}

