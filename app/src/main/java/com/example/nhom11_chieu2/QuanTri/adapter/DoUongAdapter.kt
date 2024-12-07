package com.example.nhom11_chieu2.QuanTri.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.ChiTietDoUongMainActivity
import com.example.nhom11_chieu2.QuanTri.SuaDoUong_MainActivity
import com.example.nhom11_chieu2.QuanTri.model.DoUong
import com.example.nhom11_chieu2.R

class DoUongAdapter(private val doUongList: MutableList<DoUong>) :
    RecyclerView.Adapter<DoUongAdapter.DoUongViewHolder>() {

    class DoUongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgDrink: ImageView = view.findViewById(R.id.img_drink)
        val tvTheLoai: TextView = view.findViewById(R.id.tv_theLoai)
        val tvName: TextView = view.findViewById(R.id.tv_nameDoUong)
        val tvPrice: TextView = view.findViewById(R.id.tv_priceDoUong)
        val btnEdit: ImageView = view.findViewById(R.id.iv_editDoUong)
        val btnDelete: ImageView = view.findViewById(R.id.iv_deleteDoUong)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoUongViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_do_uong, parent, false)
        return DoUongViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoUongViewHolder, position: Int) {
        val doUong = doUongList[position]

        // Gán dữ liệu cho từng thành phần
        holder.imgDrink.setImageResource(doUong.hinhAnh)
        holder.tvTheLoai.text = doUong.theLoai
        holder.tvName.text = doUong.ten
        holder.tvPrice.text = doUong.gia

        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, ChiTietDoUongMainActivity::class.java)

            val bundle = Bundle()
            bundle.putString("theLoai", doUong.theLoai)
            bundle.putString("ten", doUong.ten)
            bundle.putString("gia", doUong.gia)
            bundle.putString("moTa", doUong.moTa)
            bundle.putInt("hinhAnh", doUong.hinhAnh)
            intent.putExtras(bundle)

            context.startActivity(intent)
            holder.btnEdit.setOnClickListener {
                val context = it.context
                val intent = Intent(context, SuaDoUong_MainActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("theLoai", doUong.theLoai)
                intent.putExtra("ten", doUong.ten)
                intent.putExtra("gia", doUong.gia)
                intent.putExtra("moTa", doUong.moTa)
                intent.putExtra("hinhAnh", doUong.hinhAnh)
                (context as Activity).startActivityForResult(intent, 102)
            }
            holder.btnDelete.setOnClickListener {
                val context = it.context
                AlertDialog.Builder(context)
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn có muốn xóa đồ uống \"${doUong.ten}\" không?")
                    .setPositiveButton("Xóa") { _, _ ->
                        doUongList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, doUongList.size)
                        //Toast
                        Toast.makeText(
                            context,
                            "Đã xóa \"${doUong.ten}\" thành công!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton("Hủy", null)
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = doUongList.size
}
