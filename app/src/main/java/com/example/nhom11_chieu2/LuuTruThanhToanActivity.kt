package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.adapter.LuuTruAdapter
import com.example.nhom11_chieu2.model.DatabaseHelper

class LuuTruThanhToanActivity : AppCompatActivity() {
    private lateinit var rvLuuTruThanhToan: RecyclerView
    private lateinit var luuTruAdapter: LuuTruAdapter
    private lateinit var imgBtnThoat: ImageButton
    private lateinit var imgBtnXoaLuuTru: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luu_tru_thanh_toan)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)
        val getAllThanhToan = databaseHelper.getAllThanhToan()

        luuTruAdapter = LuuTruAdapter(getAllThanhToan)
        rvLuuTruThanhToan.adapter = luuTruAdapter
        rvLuuTruThanhToan.layoutManager = LinearLayoutManager(this)

        imgBtnThoat.setOnClickListener {
            val intentTT = Intent(this, TrangChuActivity::class.java)
            startActivity(intentTT)
        }

        imgBtnXoaLuuTru.setOnClickListener {
            if (getAllThanhToan.isNotEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Xóa")
                builder.setMessage("Bạn có chắc chắn muốn xóa tất cả thông tin thanh toán không?")
                builder.setPositiveButton("Có") { hopThoai, _ ->
                    databaseHelper.deleteAllThanhToan()
                    val updatedData = databaseHelper.getAllThanhToan()
                    luuTruAdapter.updateData(updatedData)
                }
                builder.setNegativeButton("Không") { hopThoai, nutDuocClick -> }
                builder.show()
            } else {
                Toast.makeText(
                    this, "Chưa có thông tin thanh toán nào", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setControl() {
        rvLuuTruThanhToan = findViewById(R.id.rvLuuTruThanhToan)
        imgBtnThoat = findViewById(R.id.imgBtnThoat)
        imgBtnXoaLuuTru = findViewById(R.id.imgBtnXoaLuuTru)
    }
}