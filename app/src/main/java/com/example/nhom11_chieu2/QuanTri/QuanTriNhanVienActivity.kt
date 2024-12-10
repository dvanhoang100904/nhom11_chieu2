package com.example.nhom11_chieu2.QuanTri

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.adapter.QuanTriNhanVienAdapter
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.DatabaseHelper

class QuanTriNhanVienActivity : AppCompatActivity() {
    private lateinit var rvDanhSachQTNhanVien: RecyclerView
    private lateinit var quanTriNhanVienAdapter: QuanTriNhanVienAdapter
    private lateinit var imgBtnThemNhanVien: ImageButton
    private lateinit var imgBtnQTDoUong: ImageButton
    private lateinit var imgBtnQTHome: ImageButton
    private lateinit var svTimKiemQTNhanVien: androidx.appcompat.widget.SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quan_tri_nhan_vien)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)
        val getAllNhanVien = databaseHelper.getAllNhanVien()

        quanTriNhanVienAdapter = QuanTriNhanVienAdapter(getAllNhanVien)
        rvDanhSachQTNhanVien.adapter = quanTriNhanVienAdapter
        rvDanhSachQTNhanVien.layoutManager = LinearLayoutManager(this)


        svTimKiemQTNhanVien.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val locDanhSach = databaseHelper.searchNhanVien(it)
                    quanTriNhanVienAdapter.updateDanhSach(locDanhSach)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val locDanhSach = databaseHelper.searchNhanVien(it)
                    quanTriNhanVienAdapter.updateDanhSach(locDanhSach)
                }
                return true
            }


        })

        imgBtnThemNhanVien.setOnClickListener {
            val intentTNV = Intent(this, ThemNhanVienActivity::class.java)
            startActivity(intentTNV)
        }
        imgBtnQTHome.setOnClickListener {
            val intentQTH = Intent(this, QuanTriActivity::class.java)
            startActivity(intentQTH)
        }
        imgBtnQTDoUong.setOnClickListener {
            val intentQTDU = Intent(this, QuanTriDoUongActivity::class.java)
            startActivity(intentQTDU)
        }
    }

    private fun setControl() {
        rvDanhSachQTNhanVien = findViewById(R.id.rvDanhSachQTNhanVien)

        svTimKiemQTNhanVien = findViewById(R.id.svTimKiemQTNhanVien)
        imgBtnThemNhanVien = findViewById(R.id.imgBtnThemNhanVien)
        imgBtnQTHome = findViewById(R.id.imgBtnQTHome)
        imgBtnQTDoUong = findViewById(R.id.imgBtnQTDoUong)

    }
}