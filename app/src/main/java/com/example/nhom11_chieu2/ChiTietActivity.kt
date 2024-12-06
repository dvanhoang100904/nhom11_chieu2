package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChiTietActivity : AppCompatActivity() {
    private lateinit var tvTieuDeChiTiet: TextView
    private lateinit var tvTenChiTiet: TextView
    private lateinit var ivHinhAnhChiTiet: ImageView
    private lateinit var tvGiaChiTiet: TextView
    private lateinit var tvMoTaChiTiet: TextView
    private lateinit var btnQuayLai: Button
    private lateinit var btnOrder: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet)

        // Ánh xạ view
        setControl()
        // Xử lý sự kiện
        setEvent()

    }

    private fun setEvent() {
        val maDoUong = intent.getIntExtra("ma", -1) // Lấy mã đồ uống
        if (maDoUong != -1) {
            // Lấy chi tiết từ cơ sở dữ liệu
            val databaseHelper = DatabaseHelper(this)
            val doUong = databaseHelper.getDoUongByMa(maDoUong)
            if (doUong != null) {
                // Hiển thị thông tin lên giao diện
                tvTieuDeChiTiet.text = "Chi Tiết ${doUong.ten}"
                tvTenChiTiet.text = doUong.ten
                ivHinhAnhChiTiet.setImageResource(doUong.hinhAnh)
                tvGiaChiTiet.text = formatGia(doUong.gia)
                tvMoTaChiTiet.text = doUong.moTa
            } else {
                Toast.makeText(this, "Không tìm thấy thông tin đồ uống.", Toast.LENGTH_SHORT).show()
                finish() // Đóng activity nếu không tìm thấy
            }
        } else {
            finish()
        }

        btnQuayLai.setOnClickListener { finish() }

        btnOrder.setOnClickListener {
            val maDoUong = intent.getIntExtra("ma", -1)
            if (maDoUong != -1) {
                val databaseHelper = DatabaseHelper(this)
                val doUong = databaseHelper.getDoUongByMa(maDoUong)
                if (doUong != null) {
                    val kiemTraOrders = databaseHelper.getOrdersByMaDoUong(doUong.ma)
                    if (kiemTraOrders != null) {
                        // Nếu sản phẩm đã có trong giỏ hàng, tăng số lượng lên
                        kiemTraOrders.soLuong++
                        databaseHelper.updateSoLuongOrderByMaDoUong(
                            kiemTraOrders.maDoUong,
                            kiemTraOrders.soLuong
                        )
                        Toast.makeText(this, "Order ${doUong.ten} thành công ", Toast.LENGTH_SHORT)
                            .show()
                        val intentOrder = Intent(this, DanhSachOrderActivity::class.java)
                        startActivity(intentOrder)
                    } else {
                        // Nếu chưa có, tạo mới
                        val order = Order(
                            ma = doUong.ma,
                            ten = doUong.ten,
                            hinhAnh = doUong.hinhAnh,
                            gia = doUong.gia,
                            soLuong = 1,
                            moTa = doUong.moTa,
                            maDoUong = doUong.ma
                        )
                        // Thêm vào cơ sở dữ liệu
                        databaseHelper.addOrder(order)

                        Toast.makeText(this, "Order ${doUong.ten} thành công ", Toast.LENGTH_SHORT)
                            .show()
                        val intentOrder = Intent(this, DanhSachOrderActivity::class.java)
                        startActivity(intentOrder)
                    }
                }
            }
        }

    }

    private fun formatGia(gia: Double): String {
        val decimalFormat = java.text.DecimalFormat("#,###") // Định dạng hàng nghìn
        return decimalFormat.format(gia) + " VNĐ"
    }

    private fun setControl() {
        // Thiết lập các thông tin vào layout chi tiết
        tvTieuDeChiTiet = findViewById(R.id.tvTieuDeChiTiet)
        tvTenChiTiet = findViewById(R.id.tvTenChiTiet)
        ivHinhAnhChiTiet = findViewById(R.id.ivHinhAnhChiTiet)
        tvGiaChiTiet = findViewById(R.id.tvGiaChiTiet)
        tvMoTaChiTiet = findViewById(R.id.tvMoTaChiTiet)
        btnQuayLai = findViewById(R.id.btnQuayLai)
        btnOrder = findViewById(R.id.btnOrder)
    }
}