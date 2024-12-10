package com.example.nhom11_chieu2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.DanhSachOrderActivity
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.ViTriBan

class ViTriBanAdapter(private val danhSachViTriBan: List<ViTriBan>) :
    RecyclerView.Adapter<ViTriBanAdapter.ViTriBanViewHolder>() {
    class ViTriBanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivHinhAnhViTriBan = itemView.findViewById<ImageView>(R.id.ivHinhAnhViTriBan)
        val tvTenViTriBan = itemView.findViewById<TextView>(R.id.tvTenViTriBan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViTriBanViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_vi_tri_ban, parent, false)
        return ViTriBanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return danhSachViTriBan.size
    }

    override fun onBindViewHolder(holder: ViTriBanViewHolder, position: Int) {
        val viTriBan = danhSachViTriBan[position]
        holder.ivHinhAnhViTriBan.setImageResource(viTriBan.hinhAnh)
        holder.tvTenViTriBan.text = viTriBan.ten

        holder.itemView.setOnClickListener {
            val intentDSOD = Intent(holder.itemView.context, DanhSachOrderActivity::class.java)
            holder.itemView.context.startActivity(intentDSOD)
        }
    }
}