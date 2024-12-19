package com.example.nhom11_chieu2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.R
import com.example.nhom11_chieu2.model.Order

class OrderAdapter(private val danhSachOrder: MutableList<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTenOrder: TextView = itemView.findViewById(R.id.tvTenOrder)
        val ivHinhAnhOrder: ImageView = itemView.findViewById(R.id.ivHinhAnhOrder)
        val tvGiaOrder: TextView = itemView.findViewById(R.id.tvGiaOrder)
        val tvSoLuongOrder: TextView = itemView.findViewById(R.id.tvSoLuongOrder)
        val btnTangOrder: Button = itemView.findViewById(R.id.btnTangOrder)
        val btnGiamOrder: Button = itemView.findViewById(R.id.btnGiamOrder)
        val btnXoaOrder: Button = itemView.findViewById(R.id.btnXoaOrder)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return danhSachOrder.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = danhSachOrder[position]
        holder.tvTenOrder.text = order.ten
        holder.ivHinhAnhOrder.setImageResource(order.hinhAnh)
        holder.tvGiaOrder.text = formatGia(order.gia)
        holder.tvSoLuongOrder.text = order.soLuong.toString()

        holder.btnTangOrder.setOnClickListener {
            order.soLuong++
            holder.tvSoLuongOrder.text = order.soLuong.toString()
            val databaseHelper = DatabaseHelper(holder.itemView.context)
            databaseHelper.updateSoLuongOrderByMaDoUongVaMaOderViTriBan(
                order.maDoUong,
                order.maOrderViTriBan,
                order.soLuong
            )
        }

        holder.btnGiamOrder.setOnClickListener {
            if (order.soLuong > 1) {
                order.soLuong--
                holder.tvSoLuongOrder.text = order.soLuong.toString()
                val databaseHelper = DatabaseHelper(holder.itemView.context)
                databaseHelper.updateSoLuongOrderByMaDoUongVaMaOderViTriBan(
                    order.maDoUong,
                    order.soLuong,
                    order.maOrderViTriBan
                )
            } else {
                Toast.makeText(
                    holder.itemView.context, "Số lượng không được nhỏ hơn 1", Toast.LENGTH_SHORT
                ).show()
            }
        }

        holder.btnXoaOrder.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Xóa")
            builder.setMessage("Bạn có chắc chắn muốn xóa ${order.ten}?")

            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->

                val databaseHelper = DatabaseHelper(holder.itemView.context)
                databaseHelper.deleteOrderByMa(order.ma)

                danhSachOrder.removeAt(position)
                notifyItemRemoved(position)

                Toast.makeText(holder.itemView.context, "Đã xóa ${order.ten}", Toast.LENGTH_SHORT)
                    .show()
            }
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
            }
            builder.show()
        }
    }

    private fun formatGia(gia: Double): String {
        val decimalFormat = java.text.DecimalFormat("#,###")
        return decimalFormat.format(gia) + " VNĐ"
    }
}