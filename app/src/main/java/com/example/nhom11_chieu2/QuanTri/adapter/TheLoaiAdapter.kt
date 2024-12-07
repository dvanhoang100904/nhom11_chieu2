package com.example.nhom11_chieu2.QuanTri.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

import com.example.nhom11_chieu2.QuanTri.model.TheLoai
import com.example.nhom11_chieu2.R

class TheLoaiAdapter(
    private val context: Context,
    private val danhSachTheLoai: MutableList<TheLoai>,
    private val onEditClick: (TheLoai) -> Unit,
    private val onDeleteClick: (TheLoai) -> Unit
) : RecyclerView.Adapter<TheLoaiAdapter.TheLoaiViewHolder>() {

    // ViewHolder cho RecyclerView
    class TheLoaiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTheLoai: TextView = itemView.findViewById(R.id.tvTheLoai)
        val btnEditTheLoai: ImageView = itemView.findViewById(R.id.btnEditTheLoai)
        val btnDeleteTheLoai: ImageView = itemView.findViewById(R.id.btnDeleteTheLoai)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheLoaiViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_the_loai, parent, false)
        return TheLoaiViewHolder(view)
    }

    override fun onBindViewHolder(holder: TheLoaiViewHolder, position: Int) {
        val theLoai = danhSachTheLoai[holder.adapterPosition] // Lấy vị trí chính xác
        holder.tvTheLoai.text = theLoai.tenTheLoai

        //edit
        holder.btnEditTheLoai.setOnClickListener {
            onEditClick(theLoai)
        }

        //xóa
        holder.btnDeleteTheLoai.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Xóa Thể Loại")
                .setMessage("Bạn có chắc muốn xóa thể loại '${theLoai.tenTheLoai}' không?")
                .setPositiveButton("Xóa") { _, _ ->
                    val currentPosition = holder.adapterPosition
                    danhSachTheLoai.removeAt(currentPosition)
                    notifyItemRemoved(currentPosition)
                    notifyItemRangeChanged(
                        currentPosition,
                        danhSachTheLoai.size
                    ) // Cập nhật lại vị trí
                    onDeleteClick(theLoai)
                    Toast.makeText(
                        context,
                        "Đã xóa thể loại '${theLoai.tenTheLoai}' thành công!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton("Hủy", null)
                .show()
        }
    }

    override fun getItemCount(): Int = danhSachTheLoai.size
}