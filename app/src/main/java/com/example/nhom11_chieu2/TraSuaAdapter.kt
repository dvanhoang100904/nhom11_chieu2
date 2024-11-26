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

class TraSuaAdapter(private val danhSachTraSua: List<TraSua>) :
    RecyclerView.Adapter<TraSuaAdapter.TraSuaViewHolder>() {
    class TraSuaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTenTraSua: TextView = itemView.findViewById(R.id.tvTenTraSua)
        val ivHinhAnhTraSua: ImageView = itemView.findViewById(R.id.ivHinhAnhTraSua)
        val btnChiTietTraSua: Button = itemView.findViewById(R.id.btnChiTietTraSua)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraSuaViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tra_sua, parent, false)
        return TraSuaViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return danhSachTraSua.size
    }

    override fun onBindViewHolder(holder: TraSuaViewHolder, position: Int) {
        val traSua = danhSachTraSua[position]
        holder.tvTenTraSua.text = traSua.ten
        holder.ivHinhAnhTraSua.setImageResource(traSua.hinhAnh)

        holder.btnChiTietTraSua.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Chi tiết ${traSua.ten}", Toast.LENGTH_SHORT)
                .show()
            val intentChiTiet = Intent(holder.itemView.context, ChiTietActivity::class.java).apply {
                putExtra("ma", traSua.ma)
                putExtra("ten", traSua.ten)
                putExtra("hinhAnh", traSua.hinhAnh)
                putExtra("gia", traSua.gia)
                putExtra("moTa", traSua.moTa)
            }
            holder.itemView.context.startActivity(intentChiTiet)
        }
    }
}

