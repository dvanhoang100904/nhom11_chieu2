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


class LuuTruAdapter(private var danhSachLuuTru: List<ThanhToan>) :
    RecyclerView.Adapter<LuuTruAdapter.LuuTruViewHolder>() {
    class LuuTruViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvLuuMa: TextView = view.findViewById(R.id.tvLuuMa)
        val tvLuuTen: TextView = view.findViewById(R.id.tvLuuTen)
        val ivLuuHinhAnh: ImageView = view.findViewById(R.id.ivLuuHinhAnh)
        val tvLuuGia: TextView = view.findViewById(R.id.tvLuuGia)
        val tvLuuSoLuong: TextView = view.findViewById(R.id.tvLuuSoLuong)
        val tvLuuNgay: TextView = view.findViewById(R.id.tvLuuNgay)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LuuTruViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_luutru_thanhtoan, parent, false)
        return LuuTruViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return danhSachLuuTru.size
    }

    override fun onBindViewHolder(holder: LuuTruViewHolder, position: Int) {
        val thanhToan = danhSachLuuTru[position]
        holder.tvLuuMa.text = "Mã: ${thanhToan.ma}"
        holder.tvLuuTen.text = "Tên: ${thanhToan.ten}"
        holder.ivLuuHinhAnh.setImageResource(thanhToan.hinhAnh)
        holder.tvLuuGia.text = "Giá: ${formatGia(thanhToan.gia)}"
        holder.tvLuuSoLuong.text = "Số lượng: x${thanhToan.soLuong}"
        holder.tvLuuNgay.text = "Ngày thanh toán: ${formatNgay(thanhToan.ngayThanhToan)}"
    }

    fun updateData(newData: List<ThanhToan>) {
        this.danhSachLuuTru = newData
        notifyDataSetChanged()
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