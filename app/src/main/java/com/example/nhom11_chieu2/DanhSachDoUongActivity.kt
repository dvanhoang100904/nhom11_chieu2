package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.adapter.DoUongAdapter
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.model.DoUong

class DanhSachDoUongActivity : AppCompatActivity() {
    private lateinit var rvDanhSachDoUong: RecyclerView
    private lateinit var doUongAdapter: DoUongAdapter
    private lateinit var tvLoaiDoUong: TextView
    private lateinit var imgBtnDanhSachCaPhe: ImageButton
    private lateinit var imgBtnDanhSachTraSua: ImageButton
    private lateinit var imgBtnDanhSachSinhTo: ImageButton
    private lateinit var imgBtnDanhSachOrder: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_do_uong)
        setControl()
        setEvent()

    }

    private fun setEvent() {
        val databaseHelper = DatabaseHelper(this)

//        // Kiểm tra nếu các đã tồn tại trong cơ sở dữ liệu trước khi thêm
//        val kiemTraDoUong = databaseHelper.getAllDoUong()
//        if (kiemTraDoUong.isEmpty()) {
//            // Tạo danh sách nếu cơ sở dữ liệu chưa có dữ liệu
//            val danhSachDoUong = getDanhSachDoUong()
//            for (doUong in danhSachDoUong) {
//                databaseHelper.addDoUong(doUong)
//            }
//        }

        // Lấy tất cả từ cơ sở dữ liệu
        val getAllDoUong = databaseHelper.getAllDoUong()
        // Khởi tạo adapter với danh sách đồ uống lấy từ cơ sở dữ liệu
        doUongAdapter = DoUongAdapter(getAllDoUong)
        rvDanhSachDoUong.adapter = doUongAdapter

        // Cập nhật danh sách đồ uống theo loại khi nhấn nút
        imgBtnDanhSachCaPhe.setOnClickListener {
            val caPheDanhSach = getAllDoUong.filter { it.loai == "Cà phê" }
            doUongAdapter.capNhatDanhSach(caPheDanhSach)
            tvLoaiDoUong.text = "Danh Sách Đồ Uống Cà Phê"
        }
        imgBtnDanhSachTraSua.setOnClickListener {
            val traSuaDanhSach = getAllDoUong.filter { it.loai == "Trà sữa" }
            doUongAdapter.capNhatDanhSach(traSuaDanhSach)
            tvLoaiDoUong.text = "Danh Sách Đồ Uống Trà Sữa"
        }
        imgBtnDanhSachSinhTo.setOnClickListener {
            val sinhToDanhSach = getAllDoUong.filter { it.loai == "Sinh tố" }
            doUongAdapter.capNhatDanhSach(sinhToDanhSach)
            tvLoaiDoUong.text = "Danh Sách Đồ Uống Sinh Tố"
        }
        imgBtnDanhSachOrder.setOnClickListener {
            val intentDSOD = Intent(this, DanhSachOrderActivity::class.java)
            startActivity(intentDSOD)
        }

        // Nhận loại đồ uống từ Intent và cập nhật danh sách
        val loaiDoUong = intent.getStringExtra("loaiDoUong")
        val danhSachDoUong = when (loaiDoUong) {
            "Cà Phê" -> getAllDoUong.filter { it.loai == "Cà phê" }
            "Trà Sữa" -> getAllDoUong.filter { it.loai == "Trà sữa" }
            "Sinh Tố" -> getAllDoUong.filter { it.loai == "Sinh tố" }
            else -> getAllDoUong // Nếu không có loại nào khớp, hiển thị toàn bộ
        }
        doUongAdapter.capNhatDanhSach(danhSachDoUong)
        tvLoaiDoUong.text = "Danh Sách Đồ Uống ${loaiDoUong ?: ""} "

    }

    private fun getDanhSachDoUong(): List<DoUong> {
        return listOf(
            DoUong(
                1,
                "Cà phê đen",
                R.drawable.imgcaphe1,
                12000.0,
                "Hạt cà phê rang xay, nước sôi, đá viên. Có thể kèm theo: sữa, đường, hoặc các phụ gia khác tùy sở thich.",
                "Cà phê"
            ),
            DoUong(
                2,
                "Cà phê sữa đá",
                R.drawable.imgcaphe2,
                18000.0,
                "Cà phê đen pha với sữa đặc hoặc sữa tươi, được phục vụ với đá viên, tạo ra một thức uống ngọt ngào, đậm đà và mát lạnh.",
                "Cà phê"
            ),
            DoUong(
                3,
                "Bạc xỉu",
                R.drawable.imgcaphe3,
                22000.0,
                "cà phê pha với sữa đặc, có vị ngọt nhẹ và béo, thường được phục vụ với ít đá.",
                "Cà phê"
            ),
            DoUong(
                4,
                "Cà phê sữa nóng",
                R.drawable.imgcaphe4,
                20000.0,
                "cà phê đen pha với sữa tươi hoặc sữa đặc, được phục vụ nóng, mang lại hương vị đậm đà, ngọt ngào và béo ngậy.",
                "Cà phê"
            ),
            DoUong(
                5,
                "Cà phê sữa dừa",
                R.drawable.imgcaphe5,
                26000.0,
                "cà phê đen kết hợp với sữa dừa, tạo ra một thức uống ngọt ngào, béo ngậy và thơm mát, với hương vị đặc trưng từ dừa kết hợp cùng cà phê đậm đà.",
                "Cà phê"
            ),
            DoUong(
                6,
                "Cà phê sữa chua",
                R.drawable.imgcaphe6,
                28000.0,
                "sự kết hợp giữa cà phê đen đậm đà và sữa chua, tạo nên một thức uống độc đáo, vừa chua nhẹ, vừa ngọt và béo. mát mẻ, dễ chịu.",
                "Cà phê"
            ),
            DoUong(
                7,
                "Cà phê đá xay",
                R.drawable.imgcaphe7,
                24000.0,
                "cà phê xay nhuyễn kết hợp với đá viên, tạo ra một thức uống mát lạnh, đậm đà và thường được thêm sữa hoặc đường.",
                "Cà phê"
            ),
            DoUong(
                8,
                "Cà phê chồn",
                R.drawable.imgcaphe8,
                32000.0,
                "cà phê làm từ hạt đã qua quá trình tiêu hóa trong dạ dày của chồn, mang hương vị đặc biệt, mềm mại và ít đắng.",
                "Cà phê"
            ),
            DoUong(
                9,
                "Trà sữa trân châu",
                R.drawable.imgtrasua1,
                40000.0,
                "Trà sữa trân châu hương trà sữa thơm ngon, ngọt dịu, kết hợp với trân châu dẻo mềm, tạo nên trải nghiệm thú vị.",
                "Trà sữa"
            ),
            DoUong(
                10,
                "Trà sữa matcha",
                R.drawable.imgtrasua2,
                45000.0,
                "Trà sữa matcha hương vị trà matcha đậm đà, kết hợp với sữa ngọt ngào, tạo nên thức uống thơm ngon, bổ dưỡng",
                "Trà sữa"
            ),
            DoUong(
                11,
                "Trà sữa dâu",
                R.drawable.imgtrasua3,
                40000.0,
                "Trà sữa dâu hương vị dâu ngọt ngào, kết hợp với trà sữa mịn màng, tạo nên thức uống thơm ngon, giải khát.",
                "Trà sữa"
            ),
            DoUong(
                12,
                "Trà sữa hồng trà",
                R.drawable.imgtrasua4,
                40000.0,
                "Trà sữa hồng trà hương vị hồng trà nhẹ nhàng, kết hợp với sữa ngọt thanh, mang đến sự hài hòa, thơm ngon.",
                "Trà sữa"
            ),
            DoUong(
                13,
                "Trà sữa ô long",
                R.drawable.imgtrasua5,
                45000.0,
                "Trà sữa ô long hương vị trà ô long đậm đà, kết hợp với sữa mịn màng, tạo nên một thức uống thơm ngon, đặc biệt.",
                "Trà sữa"
            ),
            DoUong(
                14,
                "Trà sữa khoai môn",
                R.drawable.imgtrasua8,
                45000.0,
                "Trà sữa khoai môn hương vị khoai môn béo ngậy, kết hợp với trà sữa ngọt thanh, tạo nên một thức uống độc đáo và thơm ngon.",
                "Trà sữa"
            ),
            DoUong(
                15,
                "Trà sữa thái",
                R.drawable.imgtrasua7,
                40000.0,
                "Trà sữa thái hương vị trà Thái đậm đà, kết hợp với sữa ngọt mát, tạo nên thức uống thơm ngon, lạ miệng.",
                "Trà sữa"
            ),
            DoUong(
                16,
                "Sinh tố dừa",
                R.drawable.imgsinhto1,
                35000.0,
                "Sinh tố dừa béo ngậy, thơm mát, giàu dinh dưỡng, giúp giải nhiệt và tăng cường năng lượng.",
                "Sinh tố"
            ),
            DoUong(
                17,
                "Sinh tố bơ",
                R.drawable.imgsinhto2,
                40000.0,
                "Sinh tố bơ béo mịn, thơm ngon, giàu vitamin E và chất xơ, tốt cho da và sức khỏe.",
                "Sinh tố"
            ),
            DoUong(
                18,
                "Sinh tố chuối",
                R.drawable.imgsinhto3,
                30000.0,
                "Sinh tố chuối ngọt dịu, béo mịn, giàu kali và vitamin B6, giúp bổ sung năng lượng và tốt cho tiêu hóa.",
                "Sinh tố"
            ),
            DoUong(
                19,
                "Sinh tố kiwi",
                R.drawable.imgsinhto4,
                40000.0,
                "Sinh tố kiwi mát lạnh, chua ngọt, giàu vitamin C, giúp giải khát và bổ sung năng lượng.",
                "Sinh tố"
            ),
            DoUong(
                20,
                "Sinh tố nho",
                R.drawable.imgsinhto5,
                45000.0,
                "Sinh tố nho ngọt dịu, thơm mát, giàu chất chống oxy hóa, tốt cho sức khỏe.",
                "Sinh tố"
            ),
            DoUong(
                21,
                "Sinh tố xoài",
                R.drawable.imgsinhto6,
                35000.0,
                "Sinh tố xoài ngọt thơm, béo mịn, giàu vitamin A, giải khát và bổ sung năng lượng.",
                "Sinh tố"
            ),
            DoUong(
                22,
                "Sinh tố dưa hấu",
                R.drawable.imgsinhto7,
                30000.0,
                "Sinh tố dưa hấu ngọt mát, thanh nhẹ, giàu nước và vitamin, giúp giải nhiệt hiệu quả.",
                "Sinh tố"
            ),
            DoUong(
                23,
                "Sinh tố thơm",
                R.drawable.imgsinhto8,
                35000.0,
                "Sinh tố thơm chua ngọt, thơm lừng, giàu vitamin C, hỗ trợ tiêu hóa và làm mát cơ thể.",
                "Sinh tố"
            ),
            DoUong(
                24,
                "Sinh tố dâu",
                R.drawable.imgsinhto9,
                40000.0,
                "Sinh tố dâu chua ngọt dịu, thơm ngon, giàu vitamin C và chất chống oxy hóa.",
                "Sinh tố"
            )
        )
    }

    private fun setControl() {
        rvDanhSachDoUong = findViewById(R.id.rvDanhSachDoUong)
        rvDanhSachDoUong.layoutManager = LinearLayoutManager(this)

        tvLoaiDoUong = findViewById(R.id.tvLoaiDoUong)
        imgBtnDanhSachCaPhe = findViewById(R.id.imgBtnDanhSachCaPhe)
        imgBtnDanhSachTraSua = findViewById(R.id.imgBtnDanhSachTraSua)
        imgBtnDanhSachSinhTo = findViewById(R.id.imgBtnDanhSachSinhTo)
        imgBtnDanhSachOrder = findViewById(R.id.imgBtnDanhSachOrder)
    }
}