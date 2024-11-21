package com.example.nhom11_chieu2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private val danhSachOrder: MutableList<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Khai báo các view trong item
        val tvTenOrder: TextView = view.findViewById(R.id.tvTenOrder)
        val tvGiaOrder: TextView = view.findViewById(R.id.tvGiaOrder)
        val tvSoLuongOrder: TextView = view.findViewById(R.id.tvSoLuongOrder)
        val ivHinhAnhOrder: ImageView = view.findViewById(R.id.ivHinhAnhOrder)
        val btnTangOrder: Button = view.findViewById(R.id.btnTangOrder)
        val btnGiamOrder: Button = view.findViewById(R.id.btnGiamOrder)

    }

    // Tạo ViewHolder để liên kết layout của mỗi item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(itemView)
    }

    // Trả về số lượng items trong danh sách
    override fun getItemCount(): Int {
        return danhSachOrder.size
    }

    // Liên kết dữ liệu vào ViewHolder
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = danhSachOrder[position]
        holder.tvTenOrder.text = order.ten
        holder.tvGiaOrder.text = formatGia(order.gia)
        holder.tvSoLuongOrder.text = order.soLuong.toString()
        holder.ivHinhAnhOrder.setImageResource(order.hinhAnh)

        holder.btnTangOrder.setOnClickListener {
            order.soLuong++
            holder.tvSoLuongOrder.text = order.soLuong.toString()
        }

        holder.btnGiamOrder.setOnClickListener {
            if (order.soLuong > 0) {
                order.soLuong--
                holder.tvSoLuongOrder.text = order.soLuong.toString()
            } else {
                Toast.makeText(
                    holder.itemView.context, "Số lượng không được nhỏ hơn 0", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun formatGia(gia: Double): CharSequence? {
        val decimalFormat = java.text.DecimalFormat("#,###")
        return decimalFormat.format(gia) + " đ"
    }
}