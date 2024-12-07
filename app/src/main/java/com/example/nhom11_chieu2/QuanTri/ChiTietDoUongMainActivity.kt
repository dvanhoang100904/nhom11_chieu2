package com.example.nhom11_chieu2.QuanTri

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhom11_chieu2.R

class ChiTietDoUongMainActivity : AppCompatActivity() {
    private lateinit var tvTitle: TextView
    private lateinit var imgDrink: ImageView
    private lateinit var tvTen: TextView
    private lateinit var tvGia: TextView
    private lateinit var tvMoTa: TextView
    private lateinit var imgExit: ImageView
    private lateinit var imgTheLoai: ImageView
    private lateinit var imgDoUong: ImageView
    private lateinit var imgCaiDat: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_do_uong_main)
        setControl()
        setEvent()
    }

    private fun setControl() {
        tvTitle = findViewById(R.id.tv_title)
        imgDrink = findViewById(R.id.img_drink_detail)
        tvTen = findViewById(R.id.tv_drink_name)
        tvGia = findViewById(R.id.tv_drink_price)
        tvMoTa = findViewById(R.id.tv_drink_description)
        imgExit = findViewById(R.id.img_exit)
        imgTheLoai = findViewById(R.id.theLoai)
        imgDoUong = findViewById(R.id.douong)
        imgCaiDat = findViewById(R.id.caidat)
    }

    private fun setEvent() {
        //nhận dữ liệu từ Intent thông qua Bundle
        val bundle = intent.extras
        if (bundle != null) {
            val tenDoUong = bundle.getString("ten", "Chi Tiết")
            val gia = bundle.getString("gia", "N/A") // trống hoặc không
            val moTa = bundle.getString("moTa", "N/A")
            val hinhAnh = bundle.getInt("hinhAnh", R.drawable.imgcaphe1) //-> truyền ảnh vào dữ liệu

            // cập nhật giao diện
            tvTitle.text = "Chi Tiết $tenDoUong"
            imgDrink.setImageResource(hinhAnh)
            tvTen.text = tenDoUong
            tvGia.text = gia
            tvMoTa.text = moTa
        }
        //các sự kiển chuyển và thoát trang
        imgExit.setOnClickListener {
            finish()
        }
        imgTheLoai.setOnClickListener {
            val intent = Intent(this, QuanLiMenu_MainActivity::class.java)
            startActivity(intent)
        }

        imgDoUong.setOnClickListener {
            val intent = Intent(this, QuanTriDoUong_MainActivity::class.java)
            startActivity(intent)
        }
        imgCaiDat.setOnClickListener {

        }
    }
}

