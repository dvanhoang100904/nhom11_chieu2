package com.example.nhom11_chieu2.QuanTri.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.ChiTietQuanTriDoUongActivity
import com.example.nhom11_chieu2.QuanTri.SuaDoUongActivity
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.model.DoUong
import com.example.nhom11_chieu2.model.NhanVien


class QuanTriDoUongAdapter(private var danhSachQTDoUong: MutableList<DoUong>) :
    RecyclerView.Adapter<QuanTriDoUongAdapter.QuanTriDoUongViewHolder>() {
    class QuanTriDoUongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTenQTDoUong: TextView = itemView.findViewById(R.id.tvTenQTDoUong)
        val ivHinhAnhQTDoUong: ImageView = itemView.findViewById(R.id.ivHinhAnhQTDoUong)
        val btnChiTietQTDoUong: Button = itemView.findViewById(R.id.btnChiTietQTDoUong)
        val imgBtnSuaDoUong: ImageButton = itemView.findViewById(R.id.imgBtnSuaDoUong)
        val imgBtnXoaDoUong: ImageButton = itemView.findViewById(R.id.imgBtnXoaDoUong)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuanTriDoUongViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_quan_tri_do_uong, parent, false)
        return QuanTriDoUongViewHolder(view)
    }

    override fun getItemCount(): Int {
        return danhSachQTDoUong.size
    }

    override fun onBindViewHolder(holder: QuanTriDoUongViewHolder, position: Int) {
        val doUong = danhSachQTDoUong[position]
        holder.tvTenQTDoUong.text = doUong.ten
        holder.ivHinhAnhQTDoUong.setImageResource(doUong.hinhAnh)
        holder.btnChiTietQTDoUong.setOnClickListener {
            val intentCTQTDU =
                Intent(holder.itemView.context, ChiTietQuanTriDoUongActivity::class.java).apply {
                    putExtra("ma", doUong.ma)
                }
            holder.itemView.context.startActivity(intentCTQTDU)
        }

        holder.imgBtnSuaDoUong.setOnClickListener {
            val intentSDU =
                Intent(holder.itemView.context, SuaDoUongActivity::class.java).apply {
                    putExtra("ma", doUong.ma)
                    putExtra("ten", doUong.ten)
                    putExtra("hinhAnh", doUong.hinhAnh)
                    putExtra("gia", doUong.gia)
                    putExtra("moTa", doUong.moTa)
                    putExtra("loai", doUong.loai)
                }
            holder.itemView.context.startActivity(intentSDU)
        }
        holder.imgBtnXoaDoUong.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Xóa")
            builder.setMessage("Bạn có chắc chắn muốn xóa ${doUong.ten}")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                val databaseHelper = DatabaseHelper(holder.itemView.context)
                databaseHelper.deleteDoUongByMa(doUong.ma)
                danhSachQTDoUong.removeAt(position)
                notifyItemRemoved(position)
                Toast.makeText(
                    holder.itemView.context,
                    "Đã xóa ${doUong.ten}",
                    Toast.LENGTH_SHORT
                )
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }
    }
    fun updateDanhSach(danhSachMoi: MutableList<DoUong>) {
        danhSachQTDoUong.clear()
        danhSachQTDoUong.addAll(danhSachMoi)
        notifyDataSetChanged()
    }

}