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
            val intentDSCaphe = Intent(this, DanhSachCaPheActivity::class.java)
            startActivity(intentDSCaphe)
        }

        imgBtnDanhSachSinhTo.setOnClickListener {
            val intentDSST = Intent(this, DanhSachSinhToActivity::class.java)
            startActivity(intentDSST)
        }

        imgBtnDanhSachOrder.setOnClickListener {
            val intentDSOrder = Intent(this, DanhSachOrderActivity::class.java)
            startActivity(intentDSOrder)
        }
    }

    private fun getDanhSachTraSua(): List<TraSua> {
        return listOf(
            TraSua(
                1,
                "Trà sữa trân châu",
                R.drawable.imgtrasua1,
                40000.0,
                1,
                "Trà sữa trân châu hương trà sữa thơm ngon, ngọt dịu, kết hợp với trân châu dẻo mềm, tạo nên trải nghiệm thú vị."
            ),
            TraSua(
                2,
                "Trà sữa matcha",
                R.drawable.imgtrasua2,
                45000.0,
                1,
                "Trà sữa matcha hương vị trà matcha đậm đà, kết hợp với sữa ngọt ngào, tạo nên thức uống thơm ngon, bổ dưỡng"
            ),
            TraSua(
                3,
                "Trà sữa dâu",
                R.drawable.imgtrasua3,
                40000.0,
                1,
                "Trà sữa dâu hương vị dâu ngọt ngào, kết hợp với trà sữa mịn màng, tạo nên thức uống thơm ngon, giải khát."
            ),
            TraSua(
                4,
                "Trà sữa hồng trà",
                R.drawable.imgtrasua4,
                40000.0,
                1,
                "Trà sữa hồng trà hương vị hồng trà nhẹ nhàng, kết hợp với sữa ngọt thanh, mang đến sự hài hòa, thơm ngon."
            ),
            TraSua(
                5,
                "Trà sữa ô long",
                R.drawable.imgtrasua5,
                45000.0,
                1,
                "Trà sữa ô long hương vị trà ô long đậm đà, kết hợp với sữa mịn màng, tạo nên một thức uống thơm ngon, đặc biệt."
            ),
            TraSua(
                6,
                "Trà sữa khoai môn",
                R.drawable.imgtrasua6,
                45000.0,
                1,
                "Trà sữa khoai môn hương vị khoai môn béo ngậy, kết hợp với trà sữa ngọt thanh, tạo nên một thức uống độc đáo và thơm ngon."
            ),
            TraSua(
                7,
                "Trà sữa thái",
                R.drawable.imgtrasua7,
                40000.0,
                1,
                "Trà sữa thái hương vị trà Thái đậm đà, kết hợp với sữa ngọt mát, tạo nên thức uống thơm ngon, lạ miệng."
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