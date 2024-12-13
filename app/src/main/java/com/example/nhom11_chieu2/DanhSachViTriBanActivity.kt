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
import com.example.nhom11_chieu2.model.ViTriBan

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

        viTriBanAdapter = ViTriBanAdapter(getAllViTriBan)
        rvDanhSachViTriBan.adapter = viTriBanAdapter
        rvDanhSachViTriBan.layoutManager = GridLayoutManager(this, 4)

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

    private fun setControl() {
        rvDanhSachViTriBan = findViewById(R.id.rvDanhSachViTriBan)
        imgBtnThoat = findViewById(R.id.imgBtnThoat)

    }
}