package com.example.nhom11_chieu2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.ThanhToan
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ThanhToanAdapter(private val danhSachThanhToan: List<ThanhToan>) :
    RecyclerView.Adapter<ThanhToanAdapter.ThanhToanViewHolder>() {
    class ThanhToanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTenThanhToan: TextView = itemView.findViewById(R.id.tvTenThanhToan)
        val ivHinhAnhThanhToan: ImageView = itemView.findViewById(R.id.ivHinhAnhThanhToan)
        val tvGiaThanhToan: TextView = itemView.findViewById(R.id.tvGiaThanhToan)
        val tvSoLuongThanhToan: TextView = itemView.findViewById(R.id.tvSoLuongThanhToan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThanhToanViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_thanhtoan, parent, false)
        return ThanhToanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return danhSachThanhToan.size
    }

    override fun onBindViewHolder(holder: ThanhToanViewHolder, position: Int) {
        val thanhToan = danhSachThanhToan[position]
        holder.tvTenThanhToan.text = thanhToan.ten
        holder.ivHinhAnhThanhToan.setImageResource(thanhToan.hinhAnh)
        holder.tvGiaThanhToan.text = formatGia(thanhToan.gia)
        holder.tvSoLuongThanhToan.text = "x${thanhToan.soLuong}"
    }

    private fun formatGia(gia: Double): String {
        val decimalFormat = java.text.DecimalFormat("#,###")
        return decimalFormat.format(gia) + " VNĐ"
    }

}