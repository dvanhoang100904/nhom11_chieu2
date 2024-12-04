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
        val caPhe: CaPhe? = intent.getParcelableExtra("caphe")
        val traSua: TraSua? = intent.getParcelableExtra("trasua")
        val sinhTo: SinhTo? = intent.getParcelableExtra("sinhto")
        val order: Order? = intent.getParcelableExtra<Order>("order")

        if (caPhe != null) {
            tvTieuDeChiTiet.text = "Chi Tiết ${caPhe.ten}"
            tvTenChiTiet.text = caPhe.ten
            ivHinhAnhChiTiet.setImageResource(caPhe.hinhAnh)
            tvGiaChiTiet.text = formatGia(caPhe.gia)
            tvMoTaChiTiet.text = caPhe.moTa
        } else if (traSua != null) {
            tvTieuDeChiTiet.text = "Chi Tiết ${traSua.ten}"
            tvTenChiTiet.text = traSua.ten
            ivHinhAnhChiTiet.setImageResource(traSua.hinhAnh)
            tvGiaChiTiet.text = formatGia(traSua.gia)
            tvMoTaChiTiet.text = traSua.moTa
        } else if (sinhTo != null) {
            tvTieuDeChiTiet.text = "Chi Tiết ${sinhTo.ten}"
            tvTenChiTiet.text = sinhTo.ten
            ivHinhAnhChiTiet.setImageResource(sinhTo.hinhAnh)
            tvGiaChiTiet.text = formatGia(sinhTo.gia)
            tvMoTaChiTiet.text = sinhTo.moTa
        } else if (order != null) {
            tvTieuDeChiTiet.text = "Chi Tiết ${order.ten}"
            tvTenChiTiet.text = order.ten
            ivHinhAnhChiTiet.setImageResource(order.hinhAnh)
            tvGiaChiTiet.text = formatGia(order.gia)
            tvMoTaChiTiet.text = order.moTa
        } else {
            Toast.makeText(this, "Chưa có đồ uống nào", Toast.LENGTH_SHORT).show()
        }

        btnQuayLai.setOnClickListener { finish() }

        btnOrder.setOnClickListener {
            val doUong = order ?: caPhe ?: traSua ?: sinhTo

            if (doUong != null) {
                val intentOrder = Intent(this, DanhSachOrderActivity::class.java).apply {
                    when (doUong) {
                        is CaPhe -> putExtra("caphe", doUong)
                        is TraSua -> putExtra("trasua", doUong)
                        is SinhTo -> putExtra("sinhto", doUong)
                        is Order -> putExtra("order", doUong)
                    }
                }
                startActivity(intentOrder)
                Toast.makeText(this, "Order thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Chưa có đồ uống nào", Toast.LENGTH_SHORT).show()
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