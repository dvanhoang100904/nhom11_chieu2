package com.example.nhom11_chieu2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ViTriBanAdapter(private val danhSachViTriBan: List<ViTriBan>) :
    RecyclerView.Adapter<ViTriBanAdapter.ViTriBanViewHolder>() {
    class ViTriBanViewHolder(itemViTriBan: View) : RecyclerView.ViewHolder(itemViTriBan) {
        val ivHinhAnhViTriBan = itemViTriBan.findViewById<ImageView>(R.id.ivHinhAnhViTriBan)
        val tvTenViTriBan = itemViTriBan.findViewById<TextView>(R.id.tvTenViTriBan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViTriBanViewHolder {
        val itemViTriBan =
            LayoutInflater.from(parent.context).inflate(R.layout.item_vi_tri_ban, parent, false)
        return ViTriBanViewHolder(itemViTriBan)
    }

    override fun getItemCount(): Int {
        return danhSachViTriBan.size
    }

    override fun onBindViewHolder(holder: ViTriBanViewHolder, position: Int) {
        val viTriBan = danhSachViTriBan[position]
        holder.ivHinhAnhViTriBan.setImageResource(viTriBan.hinhAnh)
        holder.tvTenViTriBan.text = viTriBan.ten

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Danh Sách Order", Toast.LENGTH_SHORT).show()
        }



    }
}