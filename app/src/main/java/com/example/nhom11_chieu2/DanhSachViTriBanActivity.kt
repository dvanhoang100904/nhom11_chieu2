package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DanhSachViTriBanActivity : AppCompatActivity() {
    private lateinit var rvDanhSachViTriBan: RecyclerView
    private lateinit var viTriBanAdapter: ViTriBanAdapter
    private lateinit var imgBtnThoat: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_danh_sach_vi_tri_ban)

        setControl()
        setEvent()

    }

    private fun setEvent() {
        val danhSachViTriBan = getDanhSachViTriBan()

        // Thiết lập adapter cho RecyclerView
        viTriBanAdapter = ViTriBanAdapter(danhSachViTriBan)
        // Gán adapter cho RecyclerView
        rvDanhSachViTriBan.adapter = viTriBanAdapter


        imgBtnThoat.setOnClickListener {
            // Hiển thị hộp thoại xác nhận trước khi thoát
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Thoát")
            builder.setMessage("Bạn có chắc chắn muốn thoát Danh sách vị trí bàn không?")

            // Nếu người dùng chọn "Có", thực hiện thoát
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                Toast.makeText(this, "Thoát thành công", Toast.LENGTH_SHORT).show()
                val intentDSVTB = Intent(this, TrangChuActivity::class.java)
                startActivity(intentDSVTB)
                finish()
            }

            // Nếu người dùng chọn "Không", không làm gì cả
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
                // Không làm gì khi người dùng chọn "Không"
            }

            builder.show()  // Hiển thị hộp thoại
        }
    }

    private fun getDanhSachViTriBan(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                1,
                "Bàn số 1 (A1)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                2,
                "Bàn số 2 (A2)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                3,
                "Bàn số 3 (A3)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                4,
                "Bàn số 4 (A4)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                5,
                "Bàn số 5 (A1)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                6,
                "Bàn số 6 (A2)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                7,
                "Bàn số 7 (A3)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                8,
                "Bàn số 8 (A4)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                9,
                "Bàn số 9 (A1)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                10,
                "Bàn số 10 (A2)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                11,
                "Bàn số 1 (A3)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                12,
                "Bàn số 12 (A4)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                13,
                "Bàn số 13 (A1)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                14,
                "Bàn số 14 (A2)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                15,
                "Bàn số 15 (A3)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                16,
                "Bàn số 16 (A4)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                17,
                "Bàn số 17 (A1)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                18,
                "Bàn số 18 (A2)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                19,
                "Bàn số 19 (A3)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                20,
                "Bàn số 20 (A4)",
                R.drawable.imgvitriban,
            )
            , ViTriBan(
                21,
                "Bàn số 21 (A1)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                22,
                "Bàn số 22 (A2)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                23,
                "Bàn số 23 (A3)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                24,
                "Bàn số 24 (A4)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                25,
                "Bàn số 25 (A1)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                26,
                "Bàn số 26 (A2)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                27,
                "Bàn số 27 (A3)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                28,
                "Bàn số 28 (A4)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                29,
                "Bàn số 29 (A1)",
                R.drawable.imgvitriban,
            ), ViTriBan(
                30,
                "Bàn số 30 (A2)",
                R.drawable.imgvitriban,
            )

        )
    }


    private fun setControl() {
        rvDanhSachViTriBan = findViewById(R.id.rvDanhSachViTriBan)
        rvDanhSachViTriBan.layoutManager = GridLayoutManager(this, 4) // 4 cột

        imgBtnThoat = findViewById(R.id.imgBtnThoat)

    }
}