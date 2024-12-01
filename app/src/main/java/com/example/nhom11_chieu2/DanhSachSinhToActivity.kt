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
            val intentDSCP = Intent(this, DanhSachCaPheActivity::class.java)
            startActivity(intentDSCP)
        }

        imgBtnDanhSachTraSua.setOnClickListener {
            val intentDSTS = Intent(this, DanhSachTraSuaActivity::class.java)
            startActivity(intentDSTS)
        }

        imgBtnDanhSachOrder.setOnClickListener {
            val intentDSOD = Intent(this, DanhSachOrderActivity::class.java)
            startActivity(intentDSOD)
        }

    }

    private fun getDanhSachSinhTo(): List<SinhTo> {
        return listOf(
            SinhTo(
                1,
                "Sinh tố dừa",
                R.drawable.imgsinhto1,
                35000.0,
                1,
                "Sinh tố dừa béo ngậy, thơm mát, giàu dinh dưỡng, giúp giải nhiệt và tăng cường năng lượng."
            ),
            SinhTo(
                2,
                "Sinh tố bơ",
                R.drawable.imgsinhto2,
                4000.0,
                1,
                "Sinh tố bơ béo mịn, thơm ngon, giàu vitamin E và chất xơ, tốt cho da và sức khỏe."
            ),
            SinhTo(
                3,
                "Sinh tố chuối",
                R.drawable.imgsinhto3,
                30000.0,
                1,
                "Sinh tố chuối ngọt dịu, béo mịn, giàu kali và vitamin B6, giúp bổ sung năng lượng và tốt cho tiêu hóa."
            ),
            SinhTo(
                4,
                "Sinh tố kiwi",
                R.drawable.imgsinhto4,
                40000.0,
                1,
                "Sinh tố kiwi mát lạnh, chua ngọt, giàu vitamin C, giúp giải khát và bổ sung năng lượng."
            ),
            SinhTo(
                5,
                "Sinh tố nho",
                R.drawable.imgsinhto5,
                45000.0,
                1,
                "Sinh tố nho ngọt dịu, thơm mát, giàu chất chống oxy hóa, tốt cho sức khỏe."
            ),
            SinhTo(
                6,
                "Sinh tố xoài",
                R.drawable.imgsinhto6,
                35000.0,
                1,
                "Sinh tố xoài ngọt thơm, béo mịn, giàu vitamin A, giải khát và bổ sung năng lượng."
            ),
            SinhTo(
                7,
                "Sinh tố dưa hấu",
                R.drawable.imgsinhto7,
                30000.0,
                1,
                "Sinh tố dưa hấu ngọt mát, thanh nhẹ, giàu nước và vitamin, giúp giải nhiệt hiệu quả."
            ),
            SinhTo(
                8,
                "Sinh tố thơm",
                R.drawable.imgsinhto8,
                35000.0,
                1,
                "Sinh tố thơm chua ngọt, thơm lừng, giàu vitamin C, hỗ trợ tiêu hóa và làm mát cơ thể."
            ),
            SinhTo(
                9,
                "Sinh tố dâu",
                R.drawable.imgsinhto9,
                40000.0,
                1,
                "Sinh tố dâu chua ngọt dịu, thơm ngon, giàu vitamin C và chất chống oxy hóa."
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