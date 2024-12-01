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

class CaPheAdapter(private val danhSachCaPhe: List<CaPhe>) :
    RecyclerView.Adapter<CaPheAdapter.CaPheViewHolder>() {
    class CaPheViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTenCaPhe: TextView = itemView.findViewById(R.id.tvTenCaPhe)
        val ivHinhAnhCaPhe: ImageView = itemView.findViewById(R.id.ivHinhAnhCaPhe)
        val btnChiTietCaPhe: Button = itemView.findViewById(R.id.btnChiTietCaPhe)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaPheViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ca_phe, parent, false)
        return CaPheViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return danhSachCaPhe.size
    }

    override fun onBindViewHolder(holder: CaPheViewHolder, position: Int) {
        val caPhe = danhSachCaPhe[position]
        holder.tvTenCaPhe.text = caPhe.ten
        holder.ivHinhAnhCaPhe.setImageResource(caPhe.hinhAnh)

        holder.btnChiTietCaPhe.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Chi tiết ${caPhe.ten}",
                Toast.LENGTH_SHORT
            )
                .show()
            val intentChiTiet = Intent(holder.itemView.context, ChiTietActivity::class.java).apply {
                putExtra("caphe", caPhe)
            }
            holder.itemView.context.startActivity(intentChiTiet)
        }
    }
}
