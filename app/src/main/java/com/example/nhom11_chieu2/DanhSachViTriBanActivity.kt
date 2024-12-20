package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.adapter.ViTriBanAdapter
import com.example.nhom11_chieu2.model.DatabaseHelper

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
        val getAllViTriBan = databaseHelper.getAllViTriBan()

        viTriBanAdapter = ViTriBanAdapter(getAllViTriBan) { viTriBan ->
            val intentDSOD = Intent(this, DanhSachOrderActivity::class.java).apply {
                putExtra("maViTriBan", viTriBan.ma)
            }
            startActivity(intentDSOD)
        }
        rvDanhSachViTriBan.adapter = viTriBanAdapter
        rvDanhSachViTriBan.layoutManager = GridLayoutManager(this, 4)

        imgBtnThoat.setOnClickListener {
            val intentTT = Intent(this, TrangChuActivity::class.java)
            startActivity(intentTT)
        }
    }

    private fun setControl() {
        rvDanhSachViTriBan = findViewById(R.id.rvDanhSachViTriBan)
        imgBtnThoat = findViewById(R.id.imgBtnThoat)

    }
}