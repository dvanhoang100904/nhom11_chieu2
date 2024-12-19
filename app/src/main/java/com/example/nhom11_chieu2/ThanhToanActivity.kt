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
    private lateinit var imgBtnCancle: ImageView
    private var tongTien: Double = 0.0
    private var danhSachThanhToan: ArrayList<ThanhToan> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanh_toan)
        setControl()
        setEvent()

    }

    private fun setEvent() {
        val maViTriBan = intent.getIntExtra("maViTriBan", -1)
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
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Xác nhận")
                builder.setMessage("Bạn có chắc chắn muốn thanh toán không?")
                builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                    val ptThanhToan = when (rgThanhToan.checkedRadioButtonId) {
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
                        "Bạn đã thanh toán thành công bằng ${ptThanhToan}",
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
                            ngayThanhToan = System.currentTimeMillis().toString(),
                            maViTriBan = order.maViTriBan
                        )
                        databaseHelper.addThanhToan(thanhToan)
                    }

                    databaseHelper.deleteAllOrdersByMaOrderViTriBan(maViTriBan)
                    val intentDSVTB = Intent(this, DanhSachViTriBanActivity::class.java)
                    startActivity(intentDSVTB)
                    finish()

                }
                builder.setNegativeButton("Không") { hopThoai, nutDuocClick -> }
                builder.show()
            }
        }

        imgBtnCancle.setOnClickListener {
            finish()
        }
    }

    private fun setControl() {
        rvDanhSachThanhToan = findViewById(R.id.rvDanhSachThanhToan)
        tvTongTien = findViewById(R.id.tvTongTien)
        rgThanhToan = findViewById(R.id.rgThanhToan)
        btnXacNhan = findViewById(R.id.btnXacNhan)
        imgBtnCancle = findViewById(R.id.imgBtnCancle)

    }
}