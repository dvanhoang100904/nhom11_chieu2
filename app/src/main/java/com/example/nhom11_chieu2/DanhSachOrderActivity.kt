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

private val danhSachOrder = mutableListOf<Order>()

class DanhSachOrderActivity : AppCompatActivity() {

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
        // Nhận dữ liệu từ Intent
        val ten = intent.getStringExtra("ten")
        val hinhAnh = intent.getIntExtra("hinhAnh", 0)
        val gia = intent.getDoubleExtra("gia", 0.0)
        val moTa = intent.getStringExtra("moTa")

        // Kiểm tra dữ liệu hợp lệ và xử lý
        if (ten != null && hinhAnh != 0 && gia != 0.0 && moTa != null) {
            // Kiểm tra xem đã có trong danh sách chưa
            val kiemTraOrder = danhSachOrder.find { it.ten == ten }

            if (kiemTraOrder != null) {
                // Nếu đã có, tăng số lượng lên
                kiemTraOrder.soLuong++

            } else {
                // Nếu chưa có, thêm mới vào danh sách
                val order = Order(ten, hinhAnh, gia, 1, moTa) // Số lượng mặc định là 1
                danhSachOrder.add(order)
            }
        }
        // Thiết lập adapter cho RecyclerView
        orderAdapter = OrderAdapter(danhSachOrder)

        // Gán adapter cho RecyclerView
        rvDanhSachOrder.adapter = orderAdapter
        
        btnXacNhan.setOnClickListener {
            if (!danhSachOrder.isEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Xác nhận")
                builder.setMessage("Bạn có chắc chắn muốn xác nhận không?")
                builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                    Toast.makeText(this, "xác nhận thành công", Toast.LENGTH_SHORT).show()
                    danhSachOrder.clear()
                    orderAdapter.notifyDataSetChanged()
                    val intentDSVTB = Intent(this, DanhSachViTriBanActivity::class.java)
                    startActivity(intentDSVTB)
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
                Toast.makeText(this, "Thoát thành công", Toast.LENGTH_SHORT).show()
                val intentDSVTB = Intent(this, DanhSachViTriBanActivity::class.java)
                startActivity(intentDSVTB)
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }

        imgBtnDanhSachCaPhe.setOnClickListener {
            Toast.makeText(this, "Danh sách cà phê", Toast.LENGTH_SHORT).show()
            val intentDSCP = Intent(this, DanhSachCaPheActivity::class.java)
            startActivity(intentDSCP)

        }

        imgBtnDanhSachTraSua.setOnClickListener {
            Toast.makeText(this, "Danh sách trà sữa", Toast.LENGTH_SHORT).show()
            val intentDSTS = Intent(this, DanhSachTraSuaActivity::class.java)
            startActivity(intentDSTS)

        }

        imgBtnDanhSachSinhTo.setOnClickListener {
            Toast.makeText(this, "Danh sách sinh tố", Toast.LENGTH_SHORT).show()
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