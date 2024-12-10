package com.example.nhom11_chieu2.QuanTri

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.adapter.QuanTriDoUongAdapter
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.DatabaseHelper

class QuanTriDoUongActivity : AppCompatActivity() {
    private lateinit var rvDanhSachQTDoUong: RecyclerView
    private lateinit var quanTriDoUongAdapter: QuanTriDoUongAdapter
    private lateinit var imgBtnThemDoUong: ImageButton
    private lateinit var imgBtnQTNhanVien: ImageButton
    private lateinit var imgBtnQTHome: ImageButton
    private lateinit var svTimKiemQTDoUong: androidx.appcompat.widget.SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quan_tri_do_uong)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)

        val getAllDoUong = databaseHelper.getAllDoUong()

        quanTriDoUongAdapter = QuanTriDoUongAdapter(getAllDoUong)
        rvDanhSachQTDoUong.adapter = quanTriDoUongAdapter
        rvDanhSachQTDoUong.layoutManager = LinearLayoutManager(this)

        svTimKiemQTDoUong.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val locDanhSach =databaseHelper.searchDoUongQT(it)
                    quanTriDoUongAdapter.updateDanhSach(locDanhSach)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val locDanhSach =databaseHelper.searchDoUongQT(it)
                    quanTriDoUongAdapter.updateDanhSach(locDanhSach)
                }
                return true
            }
        })

        imgBtnThemDoUong.setOnClickListener {
            val intentTDU = Intent(this, ThemDoUongActivity::class.java)
            startActivity(intentTDU)
        }

        imgBtnQTNhanVien.setOnClickListener {
            val intentQTND = Intent(this, QuanTriNhanVienActivity::class.java)
            startActivity(intentQTND)
        }
        imgBtnQTHome.setOnClickListener {
            val intentQTH = Intent(this, QuanTriActivity::class.java)
            startActivity(intentQTH)
        }
    }

    private fun setControl() {
        rvDanhSachQTDoUong = findViewById(R.id.rvDanhSachQTDoUong)
        imgBtnThemDoUong = findViewById(R.id.imgBtnThemDoUong)
        imgBtnQTHome = findViewById(R.id.imgBtnQTHome)
        imgBtnQTNhanVien = findViewById(R.id.imgBtnQTNhanVien)
        svTimKiemQTDoUong = findViewById(R.id.svTimKiemQTDoUong)
    }
}