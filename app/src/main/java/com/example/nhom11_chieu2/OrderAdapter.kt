package com.example.nhom11_chieu2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private val danhSachOrder: MutableList<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTenOrder: TextView = view.findViewById(R.id.tvTenOrder)
        val ivHinhAnhOrder: ImageView = view.findViewById(R.id.ivHinhAnhOrder)
        val tvGiaOrder: TextView = view.findViewById(R.id.tvGiaOrder)
        val tvSoLuongOrder: TextView = view.findViewById(R.id.tvSoLuongOrder)
        val btnTangOrder: Button = view.findViewById(R.id.btnTangOrder)
        val btnGiamOrder: Button = view.findViewById(R.id.btnGiamOrder)
        val btnXoaOrder: Button = view.findViewById(R.id.btnXoaOrder)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(itemView)
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

        holder.ivHinhAnhOrder.setOnClickListener {
            val intentChiTiet = Intent(holder.itemView.context, ChiTietActivity::class.java).apply {
                putExtra("ma", order.maDoUong)
            }
            holder.itemView.context.startActivity(intentChiTiet)
        }

        holder.tvTenOrder.setOnClickListener {
            val intentChiTiet = Intent(holder.itemView.context, ChiTietActivity::class.java).apply {
                putExtra("ma", order.maDoUong)
            }
            holder.itemView.context.startActivity(intentChiTiet)
        }

        holder.btnTangOrder.setOnClickListener {
            order.soLuong++
            holder.tvSoLuongOrder.text = order.soLuong.toString()
            val databaseHelper = DatabaseHelper(holder.itemView.context)
            databaseHelper.updateSoLuongOrderByMaDoUong(order.maDoUong, order.soLuong)
        }

        holder.btnGiamOrder.setOnClickListener {
            if (order.soLuong > 1) {
                order.soLuong--
                holder.tvSoLuongOrder.text = order.soLuong.toString()
                val databaseHelper = DatabaseHelper(holder.itemView.context)
                databaseHelper.updateSoLuongOrderByMaDoUong(order.maDoUong, order.soLuong)
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