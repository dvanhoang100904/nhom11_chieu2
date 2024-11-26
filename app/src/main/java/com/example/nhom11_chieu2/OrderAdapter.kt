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
        // Khai báo các view trong item
        val tvTenOrder: TextView = view.findViewById(R.id.tvTenOrder)
        val ivHinhAnhOrder: ImageView = view.findViewById(R.id.ivHinhAnhOrder)
        val tvGiaOrder: TextView = view.findViewById(R.id.tvGiaOrder)
        val tvSoLuongOrder: TextView = view.findViewById(R.id.tvSoLuongOrder)
        val btnTangOrder: Button = view.findViewById(R.id.btnTangOrder)
        val btnGiamOrder: Button = view.findViewById(R.id.btnGiamOrder)
        val btnXoaOrder: Button = view.findViewById(R.id.btnXoaOrder)

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
        holder.ivHinhAnhOrder.setImageResource(order.hinhAnh)
        holder.tvGiaOrder.text = formatGia(order.gia)
        holder.tvSoLuongOrder.text = order.soLuong.toString()

        holder.ivHinhAnhOrder.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Chi tiết ${order.ten}", Toast.LENGTH_SHORT)
                .show()
            val intentChiTiet = Intent(holder.itemView.context, ChiTietActivity::class.java).apply {
                putExtra("ma", order.ma)
                putExtra("ten", order.ten)
                putExtra("hinhAnh", order.hinhAnh)
                putExtra("gia", order.gia)
                putExtra("moTa", order.moTa)
            }
            holder.itemView.context.startActivity(intentChiTiet)
        }

        holder.tvTenOrder.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Chi tiết ${order.ten}", Toast.LENGTH_SHORT)
                .show()
            val intentChiTiet = Intent(holder.itemView.context, ChiTietActivity::class.java).apply {
                putExtra("ma", order.ma)
                putExtra("ten", order.ten)
                putExtra("hinhAnh", order.hinhAnh)
                putExtra("gia", order.gia)
                putExtra("moTa", order.moTa)
            }
            holder.itemView.context.startActivity(intentChiTiet)
        }

        holder.btnTangOrder.setOnClickListener {
            order.soLuong++
            holder.tvSoLuongOrder.text = order.soLuong.toString()
        }

        holder.btnGiamOrder.setOnClickListener {
            if (order.soLuong > 1) {
                order.soLuong--
                holder.tvSoLuongOrder.text = order.soLuong.toString()
            } else {
                Toast.makeText(
                    holder.itemView.context, "Số lượng không được nhỏ hơn 1", Toast.LENGTH_SHORT
                ).show()
            }
        }

        holder.btnXoaOrder.setOnClickListener {
            // Hiển thị hộp thoại xác nhận
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Xóa")
            builder.setMessage("Bạn có chắc chắn muốn xóa ${order.ten}?")

            // Nếu người dùng chọn "Có", xóa item
            builder.setPositiveButton("Có") { hopThoai, nutDuocClick ->
                danhSachOrder.removeAt(position)
                notifyItemRemoved(position)
                Toast.makeText(holder.itemView.context, "Đã xóa ${order.ten}", Toast.LENGTH_SHORT).show()
            }

            // Nếu người dùng chọn "Không", không làm gì cả
            builder.setNegativeButton("Không") { hopThoai, nutDuocClick ->
                // Không làm gì khi người dùng chọn "Không"
            }

            builder.show()  // Hiển thị hộp thoại
        }

    }

    private fun formatGia(gia: Double): CharSequence? {
        val decimalFormat = java.text.DecimalFormat("#,###")
        return decimalFormat.format(gia) + " đ"
    }
}