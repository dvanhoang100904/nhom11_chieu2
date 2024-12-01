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
    private lateinit var btnXacNhan: Button
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
                                soLuong = 1
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
                                soLuong = 1
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
                                soLuong = 1
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
                                soLuong = 1
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

        btnXacNhan.setOnClickListener {
            if (!danhSachOrder.isEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Xác nhận ")
                builder.setMessage("Bạn có chắc chắn muốn xác nhận danh sách order không?")

                builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                    Toast.makeText(this, "xác nhận thành công", Toast.LENGTH_SHORT).show()
                    val intentTT = Intent(this, ThanhToanActivity::class.java)
                    intentTT.putParcelableArrayListExtra(
                        "danhSachOrder",
                        ArrayList(danhSachOrder)
                    ) // Danh sách Order
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
            // Hiển thị hộp thoại xác nhận trước khi thoát
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Thoát")
            builder.setMessage("Bạn có chắc chắn muốn thoát danh sách order không?")

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
        // Thiết lập RecyclerView
        rvDanhSachOrder = findViewById(R.id.rvDanhSachOrder)
        rvDanhSachOrder.layoutManager = LinearLayoutManager(this)

        btnXacNhan = findViewById(R.id.btnXacNhan)
        imgBtnThoat = findViewById(R.id.imgBtnThoat)
        imgBtnDanhSachCaPhe = findViewById(R.id.imgBtnDanhSachCaPhe)
        imgBtnDanhSachTraSua = findViewById(R.id.imgBtnDanhSachTraSua)
        imgBtnDanhSachSinhTo = findViewById(R.id.imgBtnDanhSachSinhTo)
    }
}