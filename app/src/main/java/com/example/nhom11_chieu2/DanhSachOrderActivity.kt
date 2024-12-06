package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        // Ánh xạ view
        setControl()
        // Xử lý sự kiện
        setEvent()

    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)
        val getAllOrders = databaseHelper.getAllOrders()

        orderAdapter = OrderAdapter(getAllOrders)
        rvDanhSachOrder.adapter = orderAdapter

        btnThanhToan.setOnClickListener {
            if (!getAllOrders.isEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Thanh toán")
                builder.setMessage("Bạn có chắc chắn muốn thanh toán không?")
                builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
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
                                .toString() // Hoặc dùng một ngày thanh toán cụ thể
                        )
                    }

                    // Truyền danh sách đơn hàng qua Intent
                    val intentTT = Intent(this, ThanhToanActivity::class.java)
                    intentTT.putParcelableArrayListExtra(
                        "danhSachThanhToan",
                        ArrayList(danhSachThanhToan)
                    ) // Truyền danh sách đơn hàng
                    startActivity(intentTT)
                }
                builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
                }
                builder.show()
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
                val databaseHelper = DatabaseHelper(this)
                databaseHelper.deleteAllOrders()
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
        rvDanhSachOrder.layoutManager = LinearLayoutManager(this)

        btnThanhToan = findViewById(R.id.btnThanhToan)
        imgBtnThoat = findViewById(R.id.imgBtnThoat)
        imgBtnDanhSachCaPhe = findViewById(R.id.imgBtnDanhSachCaPhe)
        imgBtnDanhSachTraSua = findViewById(R.id.imgBtnDanhSachTraSua)
        imgBtnDanhSachSinhTo = findViewById(R.id.imgBtnDanhSachSinhTo)
    }
}