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

class DanhSachCaPheActivity : AppCompatActivity() {
    private lateinit var rvDanhSachCaPhe: RecyclerView
    private lateinit var caPheAdapter: CaPheAdapter
    private lateinit var imgBtnDanhSachTraSua: ImageButton
    private lateinit var imgBtnDanhSachSinhTo: ImageButton
    private lateinit var imgBtnDanhSachOrder: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_ca_phe)
        setControl()
        setEvent()

    }

    private fun setEvent() {
        val danhSachCaPhe = getDanhSachCaPhe() // lấy dữ liệu

        // Thiết lập adapter cho RecyclerView
        caPheAdapter = CaPheAdapter(danhSachCaPhe)
        // Gán adapter cho RecyclerView
        rvDanhSachCaPhe.adapter = caPheAdapter

        imgBtnDanhSachTraSua.setOnClickListener {
            val intentDSTS = Intent(this, DanhSachTraSuaActivity::class.java)
            startActivity(intentDSTS)
        }

        imgBtnDanhSachSinhTo.setOnClickListener {
            val intentDSST = Intent(this, DanhSachSinhToActivity::class.java)
            startActivity(intentDSST)
        }

        imgBtnDanhSachOrder.setOnClickListener {
            val intentDSOD = Intent(this, DanhSachOrderActivity::class.java)
            startActivity(intentDSOD)
        }
    }

    private fun getDanhSachCaPhe(): List<CaPhe> {
        return listOf(
            CaPhe(
                1,
                "Cà phê đen",
                R.drawable.imgcaphe1,
                12000.0,
                "Hạt cà phê rang xay, nước sôi, đá viên. Có thể kèm theo: sữa, đường, hoặc các phụ gia khác tùy sở thich."
            ),
            CaPhe(
                2,
                "Cà phê sữa đá",
                R.drawable.imgcaphe2,
                18000.0,
                "Cà phê đen pha với sữa đặc hoặc sữa tươi, được phục vụ với đá viên, tạo ra một thức uống ngọt ngào, đậm đà và mát lạnh."
            ),
            CaPhe(
                3,
                "Bạc xỉu",
                R.drawable.imgcaphe3,
                22000.0,
                "cà phê pha với sữa đặc, có vị ngọt nhẹ và béo, thường được phục vụ với ít đá."
            ),
            CaPhe(
                4,
                "Cà phê sữa nóng",
                R.drawable.imgcaphe4,
                20000.0,
                "cà phê đen pha với sữa tươi hoặc sữa đặc, được phục vụ nóng, mang lại hương vị đậm đà, ngọt ngào và béo ngậy."
            ),
            CaPhe(
                5,
                "Cà phê sữa dừa",
                R.drawable.imgcaphe5,
                26000.0,
                "cà phê đen kết hợp với sữa dừa, tạo ra một thức uống ngọt ngào, béo ngậy và thơm mát, với hương vị đặc trưng từ dừa kết hợp cùng cà phê đậm đà."
            ),
            CaPhe(
                6,
                "Cà phê sữa chua",
                R.drawable.imgcaphe6,
                28000.0,
                "sự kết hợp giữa cà phê đen đậm đà và sữa chua, tạo nên một thức uống độc đáo, vừa chua nhẹ, vừa ngọt và béo. mát mẻ, dễ chịu."
            ),
            CaPhe(
                7,
                "Cà phê đá xay",
                R.drawable.imgcaphe7,
                24000.0,
                "cà phê xay nhuyễn kết hợp với đá viên, tạo ra một thức uống mát lạnh, đậm đà và thường được thêm sữa hoặc đường."
            ),
            CaPhe(
                8,
                "Cà phê chồn",
                R.drawable.imgcaphe8,
                32000.0,
                "cà phê làm từ hạt đã qua quá trình tiêu hóa trong dạ dày của chồn, mang hương vị đặc biệt, mềm mại và ít đắng."
            ),
        )
    }

    private fun setControl() {
        // Khởi tạo RecyclerView và thiết lập các LayoutManager
        rvDanhSachCaPhe = findViewById(R.id.rvDanhSachCaPhe)
        rvDanhSachCaPhe.layoutManager = LinearLayoutManager(this)

        imgBtnDanhSachTraSua = findViewById(R.id.imgBtnDanhSachTraSua)
        imgBtnDanhSachSinhTo = findViewById(R.id.imgBtnDanhSachSinhTo)
        imgBtnDanhSachOrder = findViewById(R.id.imgBtnDanhSachOrder)
    }
}