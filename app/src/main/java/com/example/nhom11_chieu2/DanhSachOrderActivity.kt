package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
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
    private lateinit var tvTieuDeBanOrder: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_order)
        setControl()
        setEvent()

    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)
        val maViTriBan = intent.getIntExtra("maViTriBan", -1)

        if (maViTriBan != -1) {
            val maOrderViTriBan = databaseHelper.getViTriBanByMa(maViTriBan)
            if (maOrderViTriBan != null) {
                tvTieuDeBanOrder.text = "Danh Sách Order ${maOrderViTriBan.ten}"
                val getAllOrdersByMaOrderViTriBan =
                    databaseHelper.getAllOrdersByMaOrderViTriBan(maViTriBan)

                orderAdapter = OrderAdapter(getAllOrdersByMaOrderViTriBan)
                rvDanhSachOrder.adapter = orderAdapter
                rvDanhSachOrder.layoutManager = LinearLayoutManager(this)
            }
        }

        btnThanhToan.setOnClickListener {
            val getAllOrdersByMaOrderViTriBan =
                databaseHelper.getAllOrdersByMaOrderViTriBan(maViTriBan)
            if (!getAllOrdersByMaOrderViTriBan.isEmpty()) {
                // Chuyển đổi danh sách Order thành danh sách ThanhToan
                val danhSachThanhToan = getAllOrdersByMaOrderViTriBan.map { order ->
                    ThanhToan(
                        ma = order.ma,
                        ten = order.ten,
                        hinhAnh = order.hinhAnh,
                        gia = order.gia,
                        soLuong = order.soLuong,
                        moTa = order.moTa,
                        ngayThanhToan = System.currentTimeMillis().toString(),
                        maViTriBan = order.maOrderViTriBan
                    )
                }
                val intentTT = Intent(this, ThanhToanActivity::class.java).apply {
                    putParcelableArrayListExtra(
                        "danhSachThanhToan", ArrayList(danhSachThanhToan)
                    )
                    putExtra("maViTriBan", maViTriBan)
                }
                startActivity(intentTT)
            } else {
                Toast.makeText(
                    this, "Chưa có đồ uống nào được order, vui lòng order!", Toast.LENGTH_SHORT
                ).show()
            }
        }

        imgBtnThoat.setOnClickListener {
            val intentThoat = Intent(this, DanhSachViTriBanActivity::class.java)
            startActivity(intentThoat)
        }

        imgBtnDanhSachCaPhe.setOnClickListener {
            val intentDSCP = Intent(this, DanhSachDoUongActivity::class.java).apply {
                putExtra("loaiDoUong", "Cà Phê")
                putExtra("maViTriBan", maViTriBan)
            }
            startActivity(intentDSCP)
        }

        imgBtnDanhSachTraSua.setOnClickListener {
            val intentDSTS = Intent(this, DanhSachDoUongActivity::class.java).apply {
                putExtra("loaiDoUong", "Trà Sữa")
                putExtra("maViTriBan", maViTriBan)
            }
            startActivity(intentDSTS)
        }

        imgBtnDanhSachSinhTo.setOnClickListener {
            val intentDSST = Intent(this, DanhSachDoUongActivity::class.java).apply {
                putExtra("loaiDoUong", "Sinh Tố")
                putExtra("maViTriBan", maViTriBan)
            }
            startActivity(intentDSST)
        }
    }

    private fun setControl() {
        rvDanhSachOrder = findViewById(R.id.rvDanhSachOrder)
        imgBtnThoat = findViewById(R.id.imgBtnThoat)
        tvTieuDeBanOrder = findViewById(R.id.tvTieuDeBanOrder)
        btnThanhToan = findViewById(R.id.btnThanhToan)
        imgBtnDanhSachCaPhe = findViewById(R.id.imgBtnDanhSachCaPhe)
        imgBtnDanhSachTraSua = findViewById(R.id.imgBtnDanhSachTraSua)
        imgBtnDanhSachSinhTo = findViewById(R.id.imgBtnDanhSachSinhTo)

    }
}