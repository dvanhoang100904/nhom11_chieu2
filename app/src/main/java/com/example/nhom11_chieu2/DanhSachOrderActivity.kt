package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private val danhSachOrder = mutableListOf<Order>()

class DanhSachOrderActivity : AppCompatActivity() {

    private lateinit var rvDanhSachOrder: RecyclerView
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var btnQuayLai: Button
    private lateinit var btnXacNhan: Button
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

        // Kiểm tra dữ liệu hợp lệ và xử lý
        if (ten != null && hinhAnh != 0 && gia != 0.0) {
            // Kiểm tra xem đã có trong danh sách chưa
            val kiemTraOrder = danhSachOrder.find { it.ten == ten }

            if (kiemTraOrder != null) {
                // Nếu đã có, tăng số lượng lên
                kiemTraOrder.soLuong++
                Toast.makeText(this, "Số lượng tăng lên", Toast.LENGTH_SHORT).show()
            } else {
                // Nếu chưa có, thêm mới vào danh sách
                val order = Order(ten, hinhAnh, gia, 1) // Số lượng mặc định là 1
                danhSachOrder.add(order)
                Toast.makeText(this, "Order thành công!", Toast.LENGTH_SHORT).show()
            }
        }
        // Thiết lập adapter cho RecyclerView
        orderAdapter = OrderAdapter(danhSachOrder)

        // Gán adapter cho RecyclerView
        rvDanhSachOrder.adapter = orderAdapter

        btnQuayLai.setOnClickListener { finish() }

        btnXacNhan.setOnClickListener {
            Toast.makeText(this, "Xác nhận thành công!", Toast.LENGTH_SHORT).show()
            val intentDSVTB = Intent(this, DanhSachViTriBanActivity::class.java)
            startActivity(intentDSVTB)
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
        }
    }

    private fun setControl() {
        // Thiết lập RecyclerView
        rvDanhSachOrder = findViewById(R.id.rvDanhSachOrder)
        rvDanhSachOrder.layoutManager = LinearLayoutManager(this)

        btnQuayLai = findViewById(R.id.btnQuayLai)
        btnXacNhan = findViewById(R.id.btnXacNhan)
        imgBtnDanhSachCaPhe = findViewById(R.id.imgBtnDanhSachCaPhe)
        imgBtnDanhSachTraSua = findViewById(R.id.imgBtnDanhSachTraSua)
        imgBtnDanhSachSinhTo = findViewById(R.id.imgBtnDanhSachSinhTo)
    }
}