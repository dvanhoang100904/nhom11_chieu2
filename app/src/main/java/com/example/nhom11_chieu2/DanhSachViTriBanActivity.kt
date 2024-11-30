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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DanhSachViTriBanActivity : AppCompatActivity() {
    private lateinit var rvDanhSachViTriBanKhuA1: RecyclerView
    private lateinit var rvDanhSachViTriBanKhuA2: RecyclerView
    private lateinit var rvDanhSachViTriBanKhuA3: RecyclerView
    private lateinit var rvDanhSachViTriBanKhuA4: RecyclerView
    private lateinit var viTriBanAdapter: ViTriBanAdapter
    private lateinit var imgBtnThoat: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_vi_tri_ban)

        setControl()
        setEvent()

    }

    private fun setEvent() {
        val danhSachViTriBanA1 = getDanhSachViTriBanKhuA1()
        val danhSachViTriBanA2 = getDanhSachViTriBanKhuA2()
        val danhSachViTriBanA3 = getDanhSachViTriBanKhuA3()
        val danhSachViTriBanA4 = getDanhSachViTriBanKhuA4()

        // Thiết lập adapter cho RecyclerView
        viTriBanAdapter = ViTriBanAdapter(danhSachViTriBanA1)
        // Gán adapter cho RecyclerView
        rvDanhSachViTriBanKhuA1.adapter = viTriBanAdapter

        viTriBanAdapter = ViTriBanAdapter(danhSachViTriBanA2)
        rvDanhSachViTriBanKhuA2.adapter = viTriBanAdapter

        viTriBanAdapter = ViTriBanAdapter(danhSachViTriBanA3)
        rvDanhSachViTriBanKhuA3.adapter = viTriBanAdapter

        viTriBanAdapter = ViTriBanAdapter(danhSachViTriBanA4)

        rvDanhSachViTriBanKhuA4.adapter = viTriBanAdapter

        imgBtnThoat.setOnClickListener {
            // Hiển thị hộp thoại xác nhận trước khi thoát
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Thoát")
            builder.setMessage("Bạn có chắc chắn muốn thoát không?")

            // Nếu người dùng chọn "Có", thực hiện thoát
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                Toast.makeText(this, "Thoát thành công", Toast.LENGTH_SHORT).show()
                val intentDSVTB = Intent(this, TrangChuActivity::class.java)
                startActivity(intentDSVTB)
            }

            // Nếu người dùng chọn "Không", không làm gì cả
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
                // Không làm gì khi người dùng chọn "Không"
            }

            builder.show()  // Hiển thị hộp thoại
        }
    }

    private fun getDanhSachViTriBanKhuA1(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                1,
                "Bàn 1",
                R.drawable.imgvitriban,
            ), ViTriBan(
                2,
                "Bàn 2",
                R.drawable.imgvitriban,
            ), ViTriBan(
                3,
                "Bàn 3",
                R.drawable.imgvitriban,
            ), ViTriBan(
                4,
                "Bàn 4",
                R.drawable.imgvitriban,
            ), ViTriBan(
                5,
                "Bàn 5",
                R.drawable.imgvitriban,
            ), ViTriBan(
                6,
                "Bàn 6",
                R.drawable.imgvitriban,
            ), ViTriBan(
                7,
                "Bàn 7",
                R.drawable.imgvitriban,
            ), ViTriBan(
                8,
                "Bàn 8",
                R.drawable.imgvitriban,
            ), ViTriBan(
                9,
                "Bàn 9",
                R.drawable.imgvitriban,
            ), ViTriBan(
                10,
                "Bàn 10",
                R.drawable.imgvitriban,
            )

        )

    }

    private fun getDanhSachViTriBanKhuA2(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                11,
                "Bàn 11",
                R.drawable.imgvitriban,
            ), ViTriBan(
                12,
                "Bàn 12",
                R.drawable.imgvitriban,
            ), ViTriBan(
                13,
                "Bàn 13",
                R.drawable.imgvitriban,
            ), ViTriBan(
                14,
                "Bàn 14",
                R.drawable.imgvitriban,
            ), ViTriBan(
                15,
                "Bàn 15",
                R.drawable.imgvitriban,
            ), ViTriBan(
                16,
                "Bàn 16",
                R.drawable.imgvitriban,
            ), ViTriBan(
                17,
                "Bàn 17",
                R.drawable.imgvitriban,
            ), ViTriBan(
                18,
                "Bàn 18",
                R.drawable.imgvitriban,
            ), ViTriBan(
                19,
                "Bàn 19",
                R.drawable.imgvitriban,
            ), ViTriBan(
                20,
                "Bàn 20",
                R.drawable.imgvitriban,
            )

        )

    }

    private fun getDanhSachViTriBanKhuA3(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                21,
                "Bàn 21",
                R.drawable.imgvitriban,
            ), ViTriBan(
                22,
                "Bàn 22",
                R.drawable.imgvitriban,
            ), ViTriBan(
                23,
                "Bàn 23",
                R.drawable.imgvitriban,
            ), ViTriBan(
                24,
                "Bàn 24",
                R.drawable.imgvitriban,
            ), ViTriBan(
                25,
                "Bàn 25",
                R.drawable.imgvitriban,
            ), ViTriBan(
                26,
                "Bàn 26",
                R.drawable.imgvitriban,
            ), ViTriBan(
                27,
                "Bàn 27",
                R.drawable.imgvitriban,
            ), ViTriBan(
                28,
                "Bàn 28",
                R.drawable.imgvitriban,
            ), ViTriBan(
                29,
                "Bàn 29",
                R.drawable.imgvitriban,
            ), ViTriBan(
                30,
                "Bàn 30",
                R.drawable.imgvitriban,
            )

        )

    }

    private fun getDanhSachViTriBanKhuA4(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                31,
                "Bàn 31",
                R.drawable.imgvitriban,
            ), ViTriBan(
                32,
                "Bàn 32",
                R.drawable.imgvitriban,
            ), ViTriBan(
                33,
                "Bàn 33",
                R.drawable.imgvitriban,
            ), ViTriBan(
                34,
                "Bàn 34",
                R.drawable.imgvitriban,
            ), ViTriBan(
                35,
                "Bàn 35",
                R.drawable.imgvitriban,
            ), ViTriBan(
                36,
                "Bàn 36",
                R.drawable.imgvitriban,
            ), ViTriBan(
                37,
                "Bàn 37",
                R.drawable.imgvitriban,
            ), ViTriBan(
                38,
                "Bàn 38",
                R.drawable.imgvitriban,
            ), ViTriBan(
                39,
                "Bàn 39",
                R.drawable.imgvitriban,
            ), ViTriBan(
                40,
                "Bàn 40",
                R.drawable.imgvitriban,
            )

        )

    }

    private fun setControl() {
        rvDanhSachViTriBanKhuA1 = findViewById(R.id.rvDanhSachViTriBanKhuA1)
        rvDanhSachViTriBanKhuA1.layoutManager = LinearLayoutManager(this)

        rvDanhSachViTriBanKhuA2 = findViewById(R.id.rvDanhSachViTriBanKhuA2)
        rvDanhSachViTriBanKhuA2.layoutManager = LinearLayoutManager(this)

        rvDanhSachViTriBanKhuA3 = findViewById(R.id.rvDanhSachViTriBanKhuA3)
        rvDanhSachViTriBanKhuA3.layoutManager = LinearLayoutManager(this)

        rvDanhSachViTriBanKhuA4 = findViewById(R.id.rvDanhSachViTriBanKhuA4)
        rvDanhSachViTriBanKhuA4.layoutManager = LinearLayoutManager(this)

        imgBtnThoat = findViewById(R.id.imgBtnThoat)

    }
}