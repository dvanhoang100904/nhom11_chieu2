package com.example.nhom11_chieu2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoUongAdapter(private var danhSachDoUong: List<DoUong>) :
    RecyclerView.Adapter<DoUongAdapter.DoUongViewHolder>() {
    class DoUongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTenDoUong: TextView = itemView.findViewById(R.id.tvTenDoUong)
        val ivHinhAnhDoUong: ImageView = itemView.findViewById(R.id.ivHinhAnhDoUong)
        val btnChiTietDoUong: Button = itemView.findViewById(R.id.btnChiTietDoUong)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoUongViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_do_uong, parent, false)
        return DoUongViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return danhSachDoUong.size
    }

    override fun onBindViewHolder(holder: DoUongViewHolder, position: Int) {
        val doUong = danhSachDoUong[position]
        holder.tvTenDoUong.text = doUong.ten
        holder.ivHinhAnhDoUong.setImageResource(doUong.hinhAnh)
        holder.btnChiTietDoUong.setOnClickListener {
            val intentChiTiet = Intent(holder.itemView.context, ChiTietActivity::class.java).apply {
                putExtra("ma", doUong.ma)
            }
            holder.itemView.context.startActivity(intentChiTiet)
        }
    }
    fun capNhatDanhSach(newList: List<DoUong>) {
        danhSachDoUong = newList
        notifyDataSetChanged()
    }
}