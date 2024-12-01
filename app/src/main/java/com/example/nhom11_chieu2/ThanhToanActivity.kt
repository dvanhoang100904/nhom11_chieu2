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
    private lateinit var btnThanhToan: Button
    private var tongTien: Double = 0.0
    private lateinit var imgBtnThoat: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanh_toan)
        setControl()
        setEvent()

    }

    private fun setEvent() {
        intent.getParcelableArrayListExtra<Order>("danhSachOrder")?.let { danhSachOrder ->
            danhSachThanhToan = danhSachOrder.map { order ->
                ThanhToan(order.ma, order.ten, order.hinhAnh, order.gia, order.soLuong, order.moTa)
            }.toMutableList()
        }


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
        btnThanhToan.setOnClickListener {
            if (!danhSachThanhToan.isEmpty()) {
                xuLyThanhToan()
            } else {
                Toast.makeText(
                    this, "Chưa có đồ uống nào được order, vui lòng order!", Toast.LENGTH_SHORT
                ).show()
            }

        }

        imgBtnThoat.setOnClickListener {
            // Hiển thị hộp thoại xác nhận trước khi thoát
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Thoát")
            builder.setMessage("Bạn có chắc chắn muốn thoát hóa đơn thanh toán không?")

            // Nếu người dùng chọn "Có", thực hiện thoát
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                Toast.makeText(this, "Thoát thành công", Toast.LENGTH_SHORT).show()
                val intentDSVTB = Intent(this, DanhSachViTriBanActivity::class.java)
                startActivity(intentDSVTB)
                finish()
            }

            // Nếu người dùng chọn "Không", không làm gì cả
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
                // Không làm gì khi người dùng chọn "Không"
            }

            builder.show()  // Hiển thị hộp thoại
        }
    }

    private fun xuLyThanhToan() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Thanh toán")
        builder.setMessage("Bạn có chắc chắn muốn thanh toán không?")

        builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
            val phuongThucThanhToan = when (rgThanhToan.checkedRadioButtonId) {
                R.id.rbTienMat -> "Tiền mặt"
                R.id.rbThe -> "Thẻ"
                R.id.rbViDienTu -> "Ví điện tử"
                else -> {
                    Toast.makeText(
                        this, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT
                    ).show()
                    return@setPositiveButton
                }
            }

            // Xử lý thanh toán
            Toast.makeText(
                this,
                "Thanh toán thành công bằng $phuongThucThanhToan",
                Toast.LENGTH_SHORT
            ).show()

            danhSachThanhToan.clear()
            thanhToanAdapter.notifyDataSetChanged()
            val intentDSVTB = Intent(this, DanhSachViTriBanActivity::class.java)
            startActivity(intentDSVTB)
        }

        builder.setNegativeButton("Không") { hopThoai, nutDuocClick -> }
        builder.show()
    }

    private fun setControl() {
        rvDanhSachThanhToan = findViewById(R.id.rvDanhSachThanhToan)
        rvDanhSachThanhToan.layoutManager = LinearLayoutManager(this)
        tvTongTien = findViewById(R.id.tvTongTien)
        rgThanhToan = findViewById(R.id.rgThanhToan)
        btnThanhToan = findViewById(R.id.btnThanhToan)
        imgBtnThoat = findViewById(R.id.imgBtnThoat)

    }
}