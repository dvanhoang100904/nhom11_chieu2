package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
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
    private lateinit var imgBtnThoat: ImageView
    private lateinit var imgBtnXoaLuuTru: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luu_tru_thanh_toan)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)
        val getAllThanhToan = databaseHelper.getAllThanhToan()
//        Log.d("LuuTru", "Số lượng thanh toán: ${getAllThanhToan.size}")

        luuTruAdapter = LuuTruAdapter(getAllThanhToan)
        rvLuuTruThanhToan.adapter = luuTruAdapter

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

        imgBtnXoaLuuTru.setOnClickListener {
            if (getAllThanhToan.isNotEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Xóa")
                builder.setMessage("Bạn có chắc chắn muốn xóa tất cả thông tin thanh toán không?")
                builder.setPositiveButton("Có") { hopThoai, _ ->
                    databaseHelper.deleteAllThanhToan()
                    val updatedData = databaseHelper.getAllThanhToan()
                    luuTruAdapter.updateData(updatedData)  //
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
        rvLuuTruThanhToan.layoutManager = LinearLayoutManager(this)
        imgBtnThoat = findViewById(R.id.imgBtnThoat)
        imgBtnXoaLuuTru = findViewById(R.id.imgBtnXoaLuuTru)
    }
}