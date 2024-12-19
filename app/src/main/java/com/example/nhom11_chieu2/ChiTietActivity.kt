package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nhom11_chieu2.model.DatabaseHelper

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
        setControl()
        setEvent()
    }

    private fun setEvent() {
        val maDoUong = intent.getIntExtra("maDoUong", -1)
        val maViTriBan = intent.getIntExtra("maViTriBan", -1)

        if (maViTriBan == -1) {
            btnOrder.visibility = View.GONE
        } else {
            btnOrder.visibility = View.VISIBLE
        }

        if (maDoUong != -1) {
            val databaseHelper = DatabaseHelper(this)
            val doUong = databaseHelper.getDoUongByMa(maDoUong)
            if (doUong != null) {
                tvTieuDeChiTiet.text = "Chi Tiết ${doUong.ten}"
                tvTenChiTiet.text = doUong.ten
                ivHinhAnhChiTiet.setImageResource(doUong.hinhAnh)
                tvGiaChiTiet.text = formatGia(doUong.gia)
                tvMoTaChiTiet.text = doUong.moTa
            }
        }

        btnQuayLai.setOnClickListener { finish() }

        btnOrder.setOnClickListener {
            if (maDoUong != -1) {
                val databaseHelper = DatabaseHelper(this)
                val doUong = databaseHelper.getDoUongByMa(maDoUong)
                if (doUong != null) {
                    if (maViTriBan != -1) {
                        val kiemTraOrders = databaseHelper.getOrdersByMaDoUongVaMaOrderViTriBan(
                            maDoUong, maViTriBan
                        )
                        if (kiemTraOrders != null) {
                            kiemTraOrders.soLuong++
                            databaseHelper.updateSoLuongOrderByMaDoUongVaMaOderViTriBan(
                                kiemTraOrders.maDoUong,
                                kiemTraOrders.maOrderViTriBan,
                                kiemTraOrders.soLuong
                            )
                            Toast.makeText(
                                this,
                                "Order ${doUong.ten} thành công",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            databaseHelper.addOrderByMaViTriBanVaMaDoUong(maViTriBan, doUong.ma, 1)
                            Toast.makeText(
                                this,
                                "Order ${doUong.ten} thành công",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        val intentDSOD = Intent(this, DanhSachOrderActivity::class.java).apply {
                            putExtra("maViTriBan", maViTriBan)
                        }
                        startActivity(intentDSOD)
                    }
                }
            }
        }
    }

    private fun formatGia(gia: Double): String {
        val decimalFormat = java.text.DecimalFormat("#,###")
        return decimalFormat.format(gia) + " VNĐ"
    }

    private fun setControl() {
        tvTieuDeChiTiet = findViewById(R.id.tvTieuDeChiTiet)
        tvTenChiTiet = findViewById(R.id.tvTenChiTiet)
        ivHinhAnhChiTiet = findViewById(R.id.ivHinhAnhChiTiet)
        tvGiaChiTiet = findViewById(R.id.tvGiaChiTiet)
        tvMoTaChiTiet = findViewById(R.id.tvMoTaChiTiet)
        btnQuayLai = findViewById(R.id.btnQuayLai)
        btnOrder = findViewById(R.id.btnOrder)
    }
}