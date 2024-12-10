package com.example.nhom11_chieu2.QuanTri.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.R

class UpLoadAdapter(private val hinhAnh: List<Int>, private val onImageClick: (Int) -> Unit) :
    RecyclerView.Adapter<UpLoadAdapter.UploadViewHolder>() {
    class UploadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivUpLoadHinhAnh: ImageView = itemView.findViewById(R.id.ivUpLoadHinhAnh)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_upload_hinh_anh, parent, false)
        return UploadViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hinhAnh.size
    }

    override fun onBindViewHolder(holder: UploadViewHolder, position: Int) {
        val hinhAnh = hinhAnh[position]
        holder.ivUpLoadHinhAnh.setImageResource(hinhAnh)

        holder.ivUpLoadHinhAnh.setOnClickListener {
            onImageClick(hinhAnh)
        }
    }
}