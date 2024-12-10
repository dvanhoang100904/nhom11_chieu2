package com.example.nhom11_chieu2.QuanTri.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.ChiTietQuanTriNhanVienActivity
import com.example.nhom11_chieu2.QuanTri.SuaNhanVienActivity
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.model.NhanVien


class QuanTriNhanVienAdapter(private var danhSachQTNhanVien: MutableList<NhanVien>) :
    RecyclerView.Adapter<QuanTriNhanVienAdapter.NhanVienViewHolder>() {
    class NhanVienViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivHinhAnhQTNhanVien: ImageView = itemView.findViewById(R.id.ivHinhAnhQTNhanVien)
        val tvhoTenQTNhanVien: TextView = itemView.findViewById(R.id.tvhoTenQTNhanVien)
        val btnChiTietQTNhanVien: TextView = itemView.findViewById(R.id.btnChiTietQTNhanVien)
        val imgBtnSuaNhanVien: ImageButton = itemView.findViewById(R.id.imgBtnSuaNhanVien)
        val imgBtnXoaNhanVien: ImageButton = itemView.findViewById(R.id.imgBtnXoaNhanVien)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NhanVienViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_quan_tri_nhan_vien, parent, false)
        return NhanVienViewHolder(view)
    }

    override fun getItemCount(): Int {
        return danhSachQTNhanVien.size
    }

    override fun onBindViewHolder(holder: NhanVienViewHolder, position: Int) {
        val nhanVien = danhSachQTNhanVien[position]
        holder.tvhoTenQTNhanVien.text = nhanVien.hoTen
        holder.ivHinhAnhQTNhanVien.setImageResource(nhanVien.hinhAnh)
        holder.btnChiTietQTNhanVien.setOnClickListener {
            val intentCTQTNV =
                Intent(holder.itemView.context, ChiTietQuanTriNhanVienActivity::class.java).apply {
                    putExtra("ma", nhanVien.ma)
                }
            holder.itemView.context.startActivity(intentCTQTNV)
        }

        holder.imgBtnSuaNhanVien.setOnClickListener {
            val intentSNV =
                Intent(holder.itemView.context, SuaNhanVienActivity::class.java).apply {
                    putExtra("ma", nhanVien.ma)
                    putExtra("hoTen", nhanVien.hoTen)
                    putExtra("hinhAnh", nhanVien.hinhAnh)
                    putExtra("chucVu", nhanVien.chucVu)
                    putExtra("email", nhanVien.email)
                    putExtra("tenDangNhap", nhanVien.tenDangNhap)
                    putExtra("matKhau", nhanVien.matKhau)
                    putExtra("quyen", nhanVien.quyen)
                }
            holder.itemView.context.startActivity(intentSNV)
        }

        holder.imgBtnXoaNhanVien.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Xóa")
            builder.setMessage("Bạn có chắc chắn muốn xóa ${nhanVien.hoTen}?")
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                val databaseHelper = DatabaseHelper(holder.itemView.context)
                databaseHelper.deleteNhanVienByMa(nhanVien.ma)
                danhSachQTNhanVien.removeAt(position)
                notifyItemRemoved(position)
                Toast.makeText(
                    holder.itemView.context,
                    "Đã xóa ${nhanVien.hoTen}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }

    }
    fun updateDanhSach(danhSachMoi: MutableList<NhanVien>) {
        danhSachQTNhanVien.clear()
        danhSachQTNhanVien.addAll(danhSachMoi)
        notifyDataSetChanged()
    }

}