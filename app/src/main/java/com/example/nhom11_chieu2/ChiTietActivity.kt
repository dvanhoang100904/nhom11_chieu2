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
        // Nhận dữ liệu từ Intent
        val ma = intent.getStringExtra("ma")
        val ten = intent.getStringExtra("ten")
        val hinhAnh = intent.getIntExtra("hinhAnh", 0)
        val gia = intent.getDoubleExtra("gia", 0.0)
        val moTa = intent.getStringExtra("moTa")

        // Gán dữ liệu vào TextView và ImageView
        tvTenChiTiet.text = ten
        ivHinhAnhChiTiet.setImageResource(hinhAnh)
        // Kiểm tra giá có hợp lệ không rồi hiển thị
        if (gia >= 0.0) {
            tvGiaChiTiet.text = formatGia(gia)
        } else {
            tvGiaChiTiet.text = "Giá không hợp lệ" // Trường hợp nếu không có giá hợp lệ
        }
        tvMoTaChiTiet.text = moTa

        btnQuayLai.setOnClickListener { finish() }

        // Xử lý sự kiện khi nhấn nút "Order"
        btnOrder.setOnClickListener {
            Toast.makeText(this, "Order $ten thành công" , Toast.LENGTH_SHORT).show()
            // Xử lý việc thêm vào danh sách order
            val intentOrder = Intent(this, DanhSachOrderActivity::class.java).apply {
                putExtra("ma", ma)
                putExtra("ten", ten)
                putExtra("hinhAnh", hinhAnh)
                putExtra("gia", gia)
                putExtra("moTa", moTa)
            }
            startActivity(intentOrder)

        }
    }

    private fun formatGia(gia: Double): String {
        val decimalFormat = java.text.DecimalFormat("#,###") // Định dạng hàng nghìn
        return decimalFormat.format(gia) + "đ"
    }

    private fun setControl() {
        // Thiết lập các thông tin vào layout chi tiết
        tvTenChiTiet = findViewById(R.id.tvTenChiTiet)
        ivHinhAnhChiTiet = findViewById(R.id.ivHinhAnhChiTiet)
        tvGiaChiTiet = findViewById(R.id.tvGiaChiTiet)
        tvMoTaChiTiet = findViewById(R.id.tvMoTaChiTiet)
        btnQuayLai = findViewById(R.id.btnQuayLai)
        btnOrder = findViewById(R.id.btnOrder)
    }
}