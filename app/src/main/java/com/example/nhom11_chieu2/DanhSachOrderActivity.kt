package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.adapter.OrderAdapter
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.model.ThanhToan

class DanhSachOrderActivity : AppCompatActivity() {
    private lateinit var rvDanhSachOrder: RecyclerView
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var btnThanhToan: Button
    private lateinit var imgBtnThoat: ImageButton
    private lateinit var imgBtnDanhSachCaPhe: ImageButton
    private lateinit var imgBtnDanhSachTraSua: ImageButton
    private lateinit var imgBtnDanhSachSinhTo: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_order)
        setControl()
        setEvent()

    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)
        val getAllOrders = databaseHelper.getAllOrders()

        orderAdapter = OrderAdapter(getAllOrders)
        rvDanhSachOrder.adapter = orderAdapter
        rvDanhSachOrder.layoutManager = LinearLayoutManager(this)

        btnThanhToan.setOnClickListener {
            if (!getAllOrders.isEmpty()) {
                // Chuyển đổi danh sách Order thành danh sách ThanhToan
                val danhSachThanhToan = getAllOrders.map { order ->
                    ThanhToan(
                        ma = order.ma,
                        ten = order.ten,
                        hinhAnh = order.hinhAnh,
                        gia = order.gia,
                        soLuong = order.soLuong,
                        moTa = order.moTa,
                        ngayThanhToan = System.currentTimeMillis()
                            .toString()
                    )
                }
                val intentTT = Intent(this, ThanhToanActivity::class.java).apply {
                    putParcelableArrayListExtra(
                        "danhSachThanhToan", ArrayList(danhSachThanhToan)
                    )
                }
                startActivity(intentTT)
            } else {
                Toast.makeText(
                    this, "Chưa có đồ uống nào được order, vui lòng order!", Toast.LENGTH_SHORT
                ).show()
            }
        }

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

        imgBtnDanhSachCaPhe.setOnClickListener {
            val intentDSOD = Intent(this, DanhSachDoUongActivity::class.java)
            intentDSOD.putExtra("loaiDoUong", "Cà Phê")
            startActivity(intentDSOD)
        }

        imgBtnDanhSachTraSua.setOnClickListener {
            val intentDSOD = Intent(this, DanhSachDoUongActivity::class.java)
            intentDSOD.putExtra("loaiDoUong", "Trà Sữa")
            startActivity(intentDSOD)
        }

        imgBtnDanhSachSinhTo.setOnClickListener {
            val intentDSOD = Intent(this, DanhSachDoUongActivity::class.java)
            intentDSOD.putExtra("loaiDoUong", "Sinh Tố")
            startActivity(intentDSOD)
        }
    }

    private fun setControl() {
        rvDanhSachOrder = findViewById(R.id.rvDanhSachOrder)
        btnThanhToan = findViewById(R.id.btnThanhToan)
        imgBtnThoat = findViewById(R.id.imgBtnThoat)
        imgBtnDanhSachCaPhe = findViewById(R.id.imgBtnDanhSachCaPhe)
        imgBtnDanhSachTraSua = findViewById(R.id.imgBtnDanhSachTraSua)
        imgBtnDanhSachSinhTo = findViewById(R.id.imgBtnDanhSachSinhTo)
    }
}