package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ThanhToanActivity : AppCompatActivity() {
    private var danhSachThanhToan = mutableListOf<ThanhToan>()
    private lateinit var rvDanhSachThanhToan: RecyclerView
    private lateinit var thanhToanAdapter: ThanhToanAdapter
    private lateinit var tvTongTien: TextView
    private lateinit var rgThanhToan: RadioGroup
    private lateinit var btnXacNhan: Button
    private var tongTien: Double = 0.0
    private lateinit var imgBtnHuy: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanh_toan)
        setControl()
        setEvent()

    }

    private fun setEvent() {

        thanhToanAdapter = ThanhToanAdapter(danhSachThanhToan)
        rvDanhSachThanhToan.adapter = thanhToanAdapter

        // Tính tổng tiền
        tongTien = danhSachThanhToan.sumOf { item ->
            val gia = item.gia.takeIf { it > 0 } ?: 0.0
            val soLuong = item.soLuong.takeIf { it > 0 } ?: 0
            gia * soLuong
        }
        val tongTienFormat = String.format("%,.0f", tongTien) // Định dạng có dấu phẩy
        tvTongTien.text = "Tổng tiền: $tongTienFormat VNĐ"

        // Xử lý sự kiện thanh toán khi người dùng nhấn nút
        btnXacNhan.setOnClickListener {
            if (!danhSachThanhToan.isEmpty()) {
                xacNhanThanhToan()
            } else {
                Toast.makeText(
                    this, "Chưa có đồ uống nào được order, vui lòng order!", Toast.LENGTH_SHORT
                ).show()
            }

        }

        imgBtnHuy.setOnClickListener { finish() }
    }

    private fun xacNhanThanhToan() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Xác nhận")
        builder.setMessage("Bạn có chắc chắn muốn Xác nhận thanh toán không?")

        builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
            val phuongThucThanhToan = when (rgThanhToan.checkedRadioButtonId) {
                R.id.rbTienMat -> "Tiền mặt"
                R.id.rbChuyenKhoan -> "Chuyển khoản"
                R.id.rbThe -> "Thẻ"
                else -> {
                    Toast.makeText(
                        this, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT
                    ).show()
                    return@setPositiveButton
                }
            }

            Toast.makeText(
                this,
                "Bạn đã thanh toán thành công bằng ${phuongThucThanhToan}, tiếp tục chọn bàn!",
                Toast.LENGTH_SHORT
            ).show()
            val intentDSVTB = Intent(this, DanhSachViTriBanActivity::class.java)
            startActivity(intentDSVTB)
            danhSachThanhToan.clear()
            thanhToanAdapter.notifyDataSetChanged()
        }

        builder.setNegativeButton("Không") { hopThoai, nutDuocClick -> }
        builder.show()
    }

    private fun setControl() {
        rvDanhSachThanhToan = findViewById(R.id.rvDanhSachThanhToan)
        rvDanhSachThanhToan.layoutManager = LinearLayoutManager(this)
        tvTongTien = findViewById(R.id.tvTongTien)
        rgThanhToan = findViewById(R.id.rgThanhToan)
        btnXacNhan = findViewById(R.id.btnXacNhan)
        imgBtnHuy = findViewById(R.id.imgBtnHuy)

    }
}