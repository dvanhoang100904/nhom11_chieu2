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
    companion object {
        val danhSachOrder = mutableListOf<Order>()
    }
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
        val caPhe = intent.getParcelableExtra<CaPhe>("caphe")
        val traSua = intent.getParcelableExtra<TraSua>("trasua")
        val sinhTo = intent.getParcelableExtra<SinhTo>("sinhto")
        val order = intent.getParcelableExtra<Order>("order")
        val doUong = caPhe ?: traSua ?: sinhTo ?: order

        if (doUong != null) {
            when (doUong) {
                is CaPhe -> {
                    // Xử lý với CaPhe
                    val kiemTraOrder = danhSachOrder.find { it.ten == doUong.ten }
                    if (kiemTraOrder != null) {
                        kiemTraOrder.soLuong++
                    } else {
                        danhSachOrder.add(
                            Order(
                                ma = doUong.ma,
                                ten = doUong.ten,
                                hinhAnh = doUong.hinhAnh,
                                gia = doUong.gia,
                                moTa = doUong.moTa,
                                soLuong = 1,
                            )
                        )
                    }
                }

                is TraSua -> {
                    // Xử lý với TraSua
                    val kiemTraOrder = danhSachOrder.find { it.ten == doUong.ten }
                    if (kiemTraOrder != null) {
                        kiemTraOrder.soLuong++
                    } else {
                        danhSachOrder.add(
                            Order(
                                ma = doUong.ma,
                                ten = doUong.ten,
                                hinhAnh = doUong.hinhAnh,
                                gia = doUong.gia,
                                moTa = doUong.moTa,
                                soLuong = 1,
                            )
                        )
                    }
                }

                is SinhTo -> {
                    // Xử lý với SinhTo
                    val kiemTraOrder = danhSachOrder.find { it.ten == doUong.ten }
                    if (kiemTraOrder != null) {
                        kiemTraOrder.soLuong++
                    } else {
                        danhSachOrder.add(
                            Order(
                                ma = doUong.ma,
                                ten = doUong.ten,
                                hinhAnh = doUong.hinhAnh,
                                gia = doUong.gia,
                                moTa = doUong.moTa,
                                soLuong = 1,
                            )
                        )
                    }
                }

                is Order -> {
                    // Xử lý với Order
                    val kiemTraOrder = danhSachOrder.find { it.ten == doUong.ten }
                    if (kiemTraOrder != null) {
                        kiemTraOrder.soLuong++
                    } else {
                        danhSachOrder.add(
                            Order(
                                ma = doUong.ma,
                                ten = doUong.ten,
                                hinhAnh = doUong.hinhAnh,
                                gia = doUong.gia,
                                moTa = doUong.moTa,
                                soLuong = 1,
                            )
                        )
                    }
                }

                else -> {
                    Toast.makeText(this, "order không thành công", Toast.LENGTH_SHORT).show()
                }
            }
        }

        orderAdapter = OrderAdapter(danhSachOrder)
        rvDanhSachOrder.adapter = orderAdapter

        btnThanhToan.setOnClickListener {
            if (!danhSachOrder.isEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Thanh toán")
                builder.setMessage("Bạn có chắc chắn muốn thanh toán không?")

                builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                    val intentTT = Intent(this, ThanhToanActivity::class.java)
                    intentTT.putParcelableArrayListExtra(
                        "danhSachOrder",
                        ArrayList(danhSachOrder)
                    )
                    startActivity(intentTT)
                    danhSachOrder.clear()
                    orderAdapter.notifyDataSetChanged()
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
            builder.setMessage("Bạn có chắc chắn muốn thoát danh sách order không?")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                val intentDSVTB = Intent(this, DanhSachViTriBanActivity::class.java)
                startActivity(intentDSVTB)
                danhSachOrder.clear()
                orderAdapter.notifyDataSetChanged()

            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }

        imgBtnDanhSachCaPhe.setOnClickListener {
            val intentDSCP = Intent(this, DanhSachCaPheActivity::class.java)
            startActivity(intentDSCP)
        }

        imgBtnDanhSachTraSua.setOnClickListener {
            val intentDSTS = Intent(this, DanhSachTraSuaActivity::class.java)
            startActivity(intentDSTS)
        }

        imgBtnDanhSachSinhTo.setOnClickListener {
            val intentDSST = Intent(this, DanhSachSinhToActivity::class.java)
            startActivity(intentDSST)
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