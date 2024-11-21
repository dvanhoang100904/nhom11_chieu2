package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DanhSachTraSuaActivity : AppCompatActivity() {
    private lateinit var rvDanhSachTraSua: RecyclerView
    private lateinit var traSuaAdapter: TraSuaAdapter
    private lateinit var imgBtnDanhSachCaPhe: ImageButton
    private lateinit var imgBtnDanhSachSinhTo: ImageButton
    private lateinit var imgBtnDanhSachOrder: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_tra_sua)

        setControl()
        setEvent()

    }

    private fun setEvent() {
        val danhSachTraSua = getDanhSachTraSua()

        // Thiết lập adapter cho RecyclerView
        traSuaAdapter = TraSuaAdapter(danhSachTraSua)

        // Gán adapter cho RecyclerView
        rvDanhSachTraSua.adapter = traSuaAdapter

        imgBtnDanhSachCaPhe.setOnClickListener {
            Toast.makeText(this, "Danh sách cà phê", Toast.LENGTH_SHORT).show()
            val intentDSCaphe = Intent(this, DanhSachCaPheActivity::class.java)
            startActivity(intentDSCaphe)
        }

        imgBtnDanhSachSinhTo.setOnClickListener {
            Toast.makeText(this, "Danh sách sinh tố", Toast.LENGTH_SHORT).show()
            val intentDSST = Intent(this, DanhSachSinhToActivity::class.java)
            startActivity(intentDSST)
        }

        imgBtnDanhSachOrder.setOnClickListener {
            Toast.makeText(this, "Danh sách order", Toast.LENGTH_SHORT).show()
            val intentDSOrder = Intent(this, DanhSachOrderActivity::class.java)
            startActivity(intentDSOrder)
        }
    }

    private fun getDanhSachTraSua(): List<TraSua> {
        return listOf(
            TraSua(
                "Trà sữa trân châu",
                R.drawable.imgtrasua1,
                22000.0,
                "Trà sữa trân châu là thức uống kết hợp trà, sữa và trân châu dai, ngọt béo."
            ),
            TraSua(
                "Trà sữa matcha",
                R.drawable.imgtrasua2,
                26000.0,
                "Trà sữa matcha là thức uống kết hợp trà xanh matcha, sữa và thường có thêm trân châu, mang hương vị đậm đà và béo ngậy."
            ),
            TraSua(
                "Trà sữa dâu",
                R.drawable.imgtrasua3,
                24000.0,
                "Trà sữa dâu là thức uống kết hợp trà, sữa và siro dâu, tạo vị ngọt, chua nhẹ và béo ngậy."
            ),
        )
    }

    private fun setControl() {
        rvDanhSachTraSua = findViewById(R.id.rvDanhSachTraSua)
        rvDanhSachTraSua.layoutManager = LinearLayoutManager(this)

        imgBtnDanhSachCaPhe = findViewById(R.id.imgBtnDanhSachCaPhe)
        imgBtnDanhSachSinhTo = findViewById(R.id.imgBtnDanhSachSinhTo)
        imgBtnDanhSachOrder = findViewById(R.id.imgBtnDanhSachOrder)
    }
}