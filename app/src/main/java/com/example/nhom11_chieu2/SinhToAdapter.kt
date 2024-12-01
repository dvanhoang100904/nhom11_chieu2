package com.example.nhom11_chieu2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SinhToAdapter(private val danhSachSinhTo: List<SinhTo>) :
    RecyclerView.Adapter<SinhToAdapter.SinhToViewHolder>() {
    class SinhToViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTenSinhTo: TextView = itemView.findViewById(R.id.tvTenSinhTo)
        val ivHinhAnhSinhTo: ImageView = itemView.findViewById(R.id.ivHinhAnhSinhTo)
        val btnChiTietSinhTo: Button = itemView.findViewById(R.id.btnChiTietSinhTo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinhToViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sinh_to, parent, false)
        return SinhToViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return danhSachSinhTo.size
    }

    override fun onBindViewHolder(holder: SinhToViewHolder, position: Int) {
        val sinhTo = danhSachSinhTo[position]
        holder.tvTenSinhTo.text = sinhTo.ten
        holder.ivHinhAnhSinhTo.setImageResource(sinhTo.hinhAnh)
        holder.btnChiTietSinhTo.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Chi tiết ${sinhTo.ten}", Toast.LENGTH_SHORT)
                .show()
            val intentChiTiet = Intent(holder.itemView.context, ChiTietActivity::class.java).apply {
                putExtra("sinhto", sinhTo)
            }
            holder.itemView.context.startActivity(intentChiTiet)
        }
    }
}