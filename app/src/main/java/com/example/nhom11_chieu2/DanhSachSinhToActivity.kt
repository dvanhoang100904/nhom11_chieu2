package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DanhSachSinhToActivity : AppCompatActivity() {
    private lateinit var rvDanhSachSinhTo: RecyclerView
    private lateinit var sinhToAdapter: SinhToAdapter
    private lateinit var imgBtnDanhSachCaPhe: ImageButton
    private lateinit var imgBtnDanhSachTraSua: ImageButton
    private lateinit var imgBtnDanhSachOrder: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_sinh_to)

        // Ánh xạ
        setControl()
        // Xử lý sự kiện
        setEvent()

    }

    private fun setEvent() {
        val danhSachSinhTo = getDanhSachSinhTo()

        sinhToAdapter = SinhToAdapter(danhSachSinhTo)

        rvDanhSachSinhTo.adapter = sinhToAdapter

        imgBtnDanhSachCaPhe.setOnClickListener {
            Toast.makeText(this, "Danh sách cà phê", Toast.LENGTH_SHORT).show()
            val intentDSCP = Intent(this, DanhSachCaPheActivity::class.java)
            startActivity(intentDSCP)
        }

        imgBtnDanhSachTraSua.setOnClickListener {
            Toast.makeText(this, "Danh sách trà sữa", Toast.LENGTH_SHORT).show()
            val intentDSTS = Intent(this, DanhSachTraSuaActivity::class.java)
            startActivity(intentDSTS)
        }

        imgBtnDanhSachOrder.setOnClickListener {
            Toast.makeText(this, "Danh sách order", Toast.LENGTH_SHORT).show()
            val intentDSOD = Intent(this, DanhSachOrderActivity::class.java)
            startActivity(intentDSOD)
        }

    }

    private fun getDanhSachSinhTo(): List<SinhTo> {
        return listOf(
            SinhTo(
                "Sinh tố dừa",
                R.drawable.imgsinhto1,
                22000.0,
                "Sinh tố dừa là thức uống kết hợp dừa, sữa ngọt béo."
            ), SinhTo(
                "Sinh tố bơ",
                R.drawable.imgsinhto2,
                26000.0,
                "Sinh tố bơ là thức uống kết hợp bơ, sữa mang hương vị đậm đà và béo ngậy."
            ), SinhTo(
                "Sinh tố chuối",
                R.drawable.imgsinhto3,
                20000.0,
                "Sinh tố chuối là thức uống kết hợp chuối, sữa, ngọt béo ngậy."
            )
        )

    }

    private fun setControl() {
        rvDanhSachSinhTo = findViewById(R.id.rvDanhSachSinhTo)
        rvDanhSachSinhTo.layoutManager = LinearLayoutManager(this)

        imgBtnDanhSachCaPhe = findViewById(R.id.imgBtnDanhSachCaPhe)
        imgBtnDanhSachTraSua = findViewById(R.id.imgBtnDanhSachTraSua)
        imgBtnDanhSachOrder = findViewById(R.id.imgBtnDanhSachOrder)
    }
}