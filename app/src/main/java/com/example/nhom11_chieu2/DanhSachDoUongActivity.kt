package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.adapter.DoUongAdapter
import com.example.nhom11_chieu2.model.DatabaseHelper


class DanhSachDoUongActivity : AppCompatActivity() {
    private lateinit var rvDanhSachDoUong: RecyclerView
    private lateinit var doUongAdapter: DoUongAdapter
    private lateinit var tvLoaiDoUong: TextView
    private lateinit var svTimKiem: androidx.appcompat.widget.SearchView
    private lateinit var imgBtnDanhSachCaPhe: ImageButton
    private lateinit var imgBtnDanhSachTraSua: ImageButton
    private lateinit var imgBtnDanhSachSinhTo: ImageButton
    private lateinit var imgBtnDanhSachOrder: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_do_uong)
        setControl()
        setEvent()

    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)
        val maViTriBan = intent.getIntExtra("maViTriBan", -1)
        val getAllDoUong = databaseHelper.getAllDoUong()

        doUongAdapter = DoUongAdapter(getAllDoUong) { doUong ->
            val intentCT = Intent(this, ChiTietActivity::class.java).apply {
                putExtra("maDoUong", doUong.ma)
                putExtra("maViTriBan", maViTriBan)
            }
            startActivity(intentCT)
        }
        rvDanhSachDoUong.adapter = doUongAdapter
        rvDanhSachDoUong.layoutManager = LinearLayoutManager(this)

        svTimKiem.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val locDanhSach = databaseHelper.searchDoUong(it)
                    doUongAdapter.capNhatDanhSach(locDanhSach)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val locDanhSach = databaseHelper.searchDoUong(it)
                    doUongAdapter.capNhatDanhSach(locDanhSach)
                }
                return true
            }
        })

        imgBtnDanhSachCaPhe.setOnClickListener {
            val danhSachCaPhe = getAllDoUong.filter { it.loai == "Cà phê" }
            doUongAdapter.capNhatDanhSach(danhSachCaPhe)
            tvLoaiDoUong.text = "Danh Sách Đồ Uống Cà Phê"
        }

        imgBtnDanhSachTraSua.setOnClickListener {
            val danhSachTraSua = getAllDoUong.filter { it.loai == "Trà sữa" }
            doUongAdapter.capNhatDanhSach(danhSachTraSua)
            tvLoaiDoUong.text = "Danh Sách Đồ Uống Trà Sữa"
        }

        imgBtnDanhSachSinhTo.setOnClickListener {
            val danhSachSinhTo = getAllDoUong.filter { it.loai == "Sinh tố" }
            doUongAdapter.capNhatDanhSach(danhSachSinhTo)
            tvLoaiDoUong.text = "Danh Sách Đồ Uống Sinh Tố"
        }

        imgBtnDanhSachOrder.setOnClickListener {
            val intentDSOD = Intent(this, DanhSachOrderActivity::class.java).apply {
                putExtra("maViTriBan", maViTriBan)
            }
            startActivity(intentDSOD)
        }

        val loaiDoUong = intent.getStringExtra("loaiDoUong")

        val danhSachDoUong = when (loaiDoUong) {
            "Cà Phê" -> getAllDoUong.filter { it.loai == "Cà phê" }
            "Trà Sữa" -> getAllDoUong.filter { it.loai == "Trà sữa" }
            "Sinh Tố" -> getAllDoUong.filter { it.loai == "Sinh tố" }
            else -> getAllDoUong
        }
        doUongAdapter.capNhatDanhSach(danhSachDoUong)
        tvLoaiDoUong.text = "Danh Sách Đồ Uống ${loaiDoUong ?: ""} "

    }

    private fun setControl() {
        rvDanhSachDoUong = findViewById(R.id.rvDanhSachDoUong)
        tvLoaiDoUong = findViewById(R.id.tvLoaiDoUong)
        svTimKiem = findViewById(R.id.svTimKiem)
        imgBtnDanhSachCaPhe = findViewById(R.id.imgBtnDanhSachCaPhe)
        imgBtnDanhSachTraSua = findViewById(R.id.imgBtnDanhSachTraSua)
        imgBtnDanhSachSinhTo = findViewById(R.id.imgBtnDanhSachSinhTo)
        imgBtnDanhSachOrder = findViewById(R.id.imgBtnDanhSachOrder)
    }
}