package com.example.nhom11_chieu2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.ThongKe
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ThongKeAdapter(private var danhSachThongKe: List<ThongKe>) :
    RecyclerView.Adapter<ThongKeAdapter.ThongKeViewHolder>() {
    class ThongKeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDoanhThu: TextView = itemView.findViewById(R.id.tvDoanhThu)
        val tvSoLuong: TextView = itemView.findViewById(R.id.tvSoLuong)
        val tvNgayThanhToan: TextView = itemView.findViewById(R.id.tvNgayThanhToan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThongKeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_thong_ke_doanh_thu, parent, false)
        return ThongKeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return danhSachThongKe.size
    }

    override fun onBindViewHolder(holder: ThongKeViewHolder, position: Int) {
        val thongKe = danhSachThongKe[position]
        holder.tvDoanhThu.text = "Tổng Tiền Bán Đồ uống: ${formatGia(thongKe.doanhThu)}"
        holder.tvSoLuong.text = "Tổng Số Lượng Đồ uống: x${thongKe.soLuong}"
        holder.tvNgayThanhToan.text = "Ngày Thanh Toán: ${formatNgay(thongKe.ngayThanhToan)}"
    }

    private fun formatGia(gia: Double): String {
        val decimalFormat = java.text.DecimalFormat("#,###")
        return decimalFormat.format(gia) + " VNĐ"
    }

    private fun formatNgay(dateStr: String): String {
        val date = Date(dateStr.toLong())  // Chuyển từ mili giây sang Date
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return format.format(date)
    }
}