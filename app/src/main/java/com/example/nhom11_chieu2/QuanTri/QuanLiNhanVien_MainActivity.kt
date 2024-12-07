package com.example.nhom11_chieu2.QuanTri

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.adapter.NhanVienAdapter
import com.example.nhom11_chieu2.QuanTri.model.NhanVien
import com.example.nhom11_chieu2.R

class QuanLiNhanVien_MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var exitButton: ImageView
    private lateinit var nhanVienAdapter: NhanVienAdapter
    private lateinit var imgExit: ImageView
    private val danhSachNhanVien = mutableListOf(
        NhanVien("Nguyễn Văn A", "Quản lý", "admin", "123"),
        NhanVien("Trần Thị B", "Nhân viên bán hàng", "btran", "111"),
        NhanVien("Lê Văn C", "Kế toán", "cle", "112")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quan_li_nhan_vien_main)

        setControl() // Khởi tạo các view
        setEvent()   // Thiết lập sự kiện
    }

    private fun setControl() {
        recyclerView = findViewById(R.id.recyclerView)
        nhanVienAdapter = NhanVienAdapter(danhSachNhanVien, onEditClick = { nhanVien ->
            val intent = Intent(this, SuaNhanVien_MainActivity::class.java).apply {
                putExtra("TEN_DANG_NHAP", nhanVien.tenDangNhap)
                putExtra("HO_TEN", nhanVien.hoTen)
                putExtra("MAT_KHAU", nhanVien.matKhau)
                putExtra("NHIEM_VU", nhanVien.nhiemVu)
            }
            startActivityForResult(intent, REQUEST_CODE_SUA_NHAN_VIEN)
        }, onDeleteClick = {
            // Sự kiện xóa đã xử lý trong Adapter
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = nhanVienAdapter
        exitButton = findViewById(R.id.img_exit)
    }

    private fun setEvent() {
        val btnThemNhanVien = findViewById<ImageView>(R.id.themNhanVien)
        btnThemNhanVien.setOnClickListener {
            Toast.makeText(this, "Thêm nhân viên mới", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ThemNhanVien_MainActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_THEM_NHAN_VIEN)
        }
        exitButton.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_THEM_NHAN_VIEN && resultCode == RESULT_OK) {
            data?.let {
                val tenDangNhap = it.getStringExtra("TEN_DANG_NHAP") ?: ""
                val hoTen = it.getStringExtra("HO_TEN") ?: ""
                val matKhau = it.getStringExtra("MAT_KHAU") ?: ""
                val nhiemVu = it.getStringExtra("NHIEM_VU") ?: ""

                // Thêm nhân viên vào danh sách
                danhSachNhanVien.add(NhanVien(hoTen, nhiemVu, tenDangNhap, matKhau))
                nhanVienAdapter.notifyDataSetChanged()
            }
        } else if (requestCode == REQUEST_CODE_SUA_NHAN_VIEN && resultCode == RESULT_OK) {
            data?.let {
                val tenDangNhap = it.getStringExtra("TEN_DANG_NHAP") ?: return
                val hoTen = it.getStringExtra("HO_TEN") ?: return
                val matKhau = it.getStringExtra("MAT_KHAU") ?: return
                val nhiemVu = it.getStringExtra("NHIEM_VU") ?: return

                danhSachNhanVien.find { nv -> nv.tenDangNhap == tenDangNhap }?.apply {
                    this.hoTen = hoTen
                    this.matKhau = matKhau
                    this.nhiemVu = nhiemVu
                }
                nhanVienAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_THEM_NHAN_VIEN = 1001
        private const val REQUEST_CODE_SUA_NHAN_VIEN = 1002
    }
}
