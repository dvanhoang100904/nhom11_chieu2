package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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
        val databaseHelper = DatabaseHelper(this)

        val kiemTraViTriBan = databaseHelper.getAllViTriBan()
        if (kiemTraViTriBan.isEmpty()) {
            val danhSachViTriBan = getDanhSachViTriBan()

            for (viTriBan in danhSachViTriBan) {
                databaseHelper.addViTriBan(viTriBan)
            }
        }

        val getAllViTriBan = databaseHelper.getAllViTriBan()

        viTriBanAdapter = ViTriBanAdapter(getAllViTriBan)
        rvDanhSachViTriBan.adapter = viTriBanAdapter

        imgBtnThoat.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Thoát")
            builder.setMessage("Bạn có chắc chắn muốn thoát không?")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                val intentThoat = Intent(this, NhanVienOrderActivity::class.java)
                startActivity(intentThoat)
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }
    }

    private fun getDanhSachViTriBan(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                1,
                "Bàn số 1 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                2,
                "Bàn số 2 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                3,
                "Bàn số 3 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                4,
                "Bàn số 4 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                5,
                "Bàn số 5 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                6,
                "Bàn số 6 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                7,
                "Bàn số 7 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                8,
                "Bàn số 8 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                9,
                "Bàn số 9 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                10,
                "Bàn số 10 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                11,
                "Bàn số 11 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                12,
                "Bàn số 12 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                13,
                "Bàn số 13 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                14,
                "Bàn số 14 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                15,
                "Bàn số 15 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                16,
                "Bàn số 16 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                17,
                "Bàn số 17 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                18,
                "Bàn số 18 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                19,
                "Bàn số 19 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                20,
                "Bàn số 20 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                21,
                "Bàn số 21 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                22,
                "Bàn số 22 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                23,
                "Bàn số 23 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                24,
                "Bàn số 24 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                25,
                "Bàn số 25 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                26,
                "Bàn số 26 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                27,
                "Bàn số 27 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                28,
                "Bàn số 28 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                29,
                "Bàn số 29 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                30,
                "Bàn số 30 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                31,
                "Bàn số 31 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                32,
                "Bàn số 32 (A4)",
                R.drawable.imgtable,
            )
        )
    }

    private fun setControl() {
        rvDanhSachViTriBan = findViewById(R.id.rvDanhSachViTriBan)
        rvDanhSachViTriBan.layoutManager = GridLayoutManager(this, 4)

        imgBtnThoat = findViewById(R.id.imgBtnThoat)

    }
}