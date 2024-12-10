package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.adapter.ThanhToanAdapter
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.model.ThanhToan

class ThanhToanActivity : AppCompatActivity() {
    private lateinit var rvDanhSachThanhToan: RecyclerView
    private lateinit var thanhToanAdapter: ThanhToanAdapter
    private lateinit var tvTongTien: TextView
    private lateinit var rgThanhToan: RadioGroup
    private lateinit var btnXacNhan: Button
    private var tongTien: Double = 0.0
    private lateinit var imgBtnCancle: ImageView
    private var danhSachThanhToan: ArrayList<ThanhToan> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanh_toan)
        setControl()
        setEvent()

    }

    private fun setEvent() {
        danhSachThanhToan =
            intent.getParcelableArrayListExtra<ThanhToan>("danhSachThanhToan") ?: arrayListOf()

        thanhToanAdapter = ThanhToanAdapter(danhSachThanhToan)
        rvDanhSachThanhToan.adapter = thanhToanAdapter
        rvDanhSachThanhToan.layoutManager = LinearLayoutManager(this)

        tongTien = danhSachThanhToan.sumOf { it.gia * it.soLuong }
        val tongTienFormat = String.format("%,.0f", tongTien) // Định dạng có dấu phẩy
        tvTongTien.text = "Tổng tiền: $tongTienFormat VNĐ"

        btnXacNhan.setOnClickListener {
            if (!danhSachThanhToan.isEmpty()) {
                xacNhanThanhToan()
            } else {
                Toast.makeText(
                    this, "Chưa có đồ uống nào được order, vui lòng order!", Toast.LENGTH_SHORT
                ).show()
            }
        }

        imgBtnCancle.setOnClickListener {
            finish()
        }
    }

    private fun xacNhanThanhToan() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Xác nhận")
        builder.setMessage("Bạn có chắc chắn muốn thanh toán không?")
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
                "Bạn đã thanh toán thành công bằng ${phuongThucThanhToan}",
                Toast.LENGTH_SHORT
            ).show()

            val databaseHelper = DatabaseHelper(this)
            for (order in danhSachThanhToan) {
                val thanhToan = ThanhToan(
                    ma = order.ma,
                    ten = order.ten,
                    hinhAnh = order.hinhAnh,
                    gia = order.gia,
                    soLuong = order.soLuong,
                    moTa = order.moTa,
                    ngayThanhToan = System.currentTimeMillis().toString()
                )
                databaseHelper.addThanhToan(thanhToan)
            }

            databaseHelper.deleteAllOrders()

            val intentNVOD = Intent(this, NhanVienOrderActivity::class.java)
            startActivity(intentNVOD)
        }
        builder.setNegativeButton("Không") { hopThoai, nutDuocClick -> }
        builder.show()
    }

    private fun setControl() {
        rvDanhSachThanhToan = findViewById(R.id.rvDanhSachThanhToan)
        tvTongTien = findViewById(R.id.tvTongTien)
        rgThanhToan = findViewById(R.id.rgThanhToan)
        btnXacNhan = findViewById(R.id.btnXacNhan)
        imgBtnCancle = findViewById(R.id.imgBtnCancle)

    }
}