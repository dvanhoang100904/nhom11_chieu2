package com.example.nhom11_chieu2

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
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
            Toast.makeText(this, "Thoát thành công", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDanhSachViTriBanKhuA1(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                "Bàn 1",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 2",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 3",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 4",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 5",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 6",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 7",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 8",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 9",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 10",
                R.drawable.imgvitriban,
            )

        )

    }

    private fun getDanhSachViTriBanKhuA2(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                "Bàn 11",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 12",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 13",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 14",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 15",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 16",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 17",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 18",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 19",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 20",
                R.drawable.imgvitriban,
            )

        )

    }

    private fun getDanhSachViTriBanKhuA3(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                "Bàn 21",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 22",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 23",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 24",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 25",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 26",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 27",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 28",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 29",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 30",
                R.drawable.imgvitriban,
            )

        )

    }

    private fun getDanhSachViTriBanKhuA4(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                "Bàn 31",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 32",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 33",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 34",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 35",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 36",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 37",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 38",
                R.drawable.imgvitriban,
            ), ViTriBan(
                "Bàn 39",
                R.drawable.imgvitriban,
            ), ViTriBan(
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