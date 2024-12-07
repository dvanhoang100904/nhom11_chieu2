package com.example.nhom11_chieu2.QuanTri.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.model.NhanVien
import com.example.nhom11_chieu2.R

class NhanVienAdapter(
    private val danhSachNhanVien: MutableList<NhanVien>,
    private val onEditClick: (NhanVien) -> Unit,
    private val onDeleteClick: (NhanVien) -> Unit
) : RecyclerView.Adapter<NhanVienAdapter.NhanVienViewHolder>() {

    class NhanVienViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTenNhanVien: TextView = itemView.findViewById(R.id.tvTenNhanVien)
        val txtNhiemVu: TextView = itemView.findViewById(R.id.tvNhiemVu)
        val txtTenDangNhap: TextView = itemView.findViewById(R.id.tvTenDangNhap)
        val editNhanVien: ImageView = itemView.findViewById(R.id.editNhanVien)
        val deleteNhanVien: ImageView = itemView.findViewById(R.id.deleteNhanVien)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NhanVienViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_nhan_vien, parent, false)
        return NhanVienViewHolder(view)
    }

    override fun onBindViewHolder(holder: NhanVienViewHolder, position: Int) {
        val nhanVien = danhSachNhanVien[position]
        holder.txtTenNhanVien.text = "Họ tên: ${nhanVien.hoTen}"
        holder.txtNhiemVu.text = "Nhiệm vụ: ${nhanVien.nhiemVu}"
        holder.txtTenDangNhap.text = "Tên đăng nhập: ${nhanVien.tenDangNhap}"
        holder.editNhanVien.setOnClickListener { onEditClick(nhanVien) }
        holder.deleteNhanVien.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Xóa Nhân Viên")
                .setMessage("Bạn có chắc chắn muốn xóa nhân viên này không?")
                .setPositiveButton("Xóa") { _, _ ->
                    danhSachNhanVien.removeAt(position)
                    notifyItemRemoved(position)
                    //toast
                    Toast.makeText(
                        holder.itemView.context,
                        "Đã xóa nhân viên ${nhanVien.hoTen} thành công!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton("Hủy", null)
                .show()
        }
    }

    override fun getItemCount(): Int = danhSachNhanVien.size
}
