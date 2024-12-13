package com.example.nhom11_chieu2

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nhom11_chieu2.QuanTri.QuanTriActivity
import com.example.nhom11_chieu2.model.DatabaseHelper
import com.example.nhom11_chieu2.model.DoUong
import com.example.nhom11_chieu2.model.NhanVien
import com.example.nhom11_chieu2.model.ViTriBan

class DangNhapActivity : AppCompatActivity() {
    private lateinit var edtTen: EditText
    private lateinit var btnDangNhap: Button
    private lateinit var edtMatKhau: EditText
    private lateinit var ivShowMatKhau: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        var isPasswordVisible = false

        ivShowMatKhau.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            // Đổi inputType của EditText để hiển thị hoặc ẩn mật khẩu
            edtMatKhau.inputType = if (isPasswordVisible) {
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            // Đảm bảo con trỏ vẫn ở cuối văn bản
            edtMatKhau.setSelection(edtMatKhau.text.length)

            // Thay đổi hình ảnh của ImageView dựa trên trạng thái
            ivShowMatKhau.setImageResource(
                if (isPasswordVisible) R.drawable.imgeyehidden else R.drawable.imgeyeview
            )
        }

        btnDangNhap.setOnClickListener {
            val ten = edtTen.text.toString().trim()
            val matKhau = edtMatKhau.text.toString().trim()

            if (ten.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhau.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhau.length < 6) {
                Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!ten.matches(Regex("[a-zA-Z0-9._-]+"))) {
                Toast.makeText(this, "Tên đăng nhập không hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val databaseHelper = DatabaseHelper(this)
            val quyen = databaseHelper.dangNhap(ten, matKhau)
            if (quyen != null) {
                if (quyen == 50) {
                    val intentNVOD = Intent(this, NhanVienOrderActivity::class.java)
                    startActivity(intentNVOD)
                } else if (quyen == 100) {
                    val intentQT = Intent(this, QuanTriActivity::class.java)
                    startActivity(intentQT)
                }
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val databaseHelper = DatabaseHelper(this)
        val kiemTraNhanVien = databaseHelper.getAllNhanVien()
        if (kiemTraNhanVien.isEmpty()) {
            val danhSachNhanVien = getDanhSachNhanVien()
            for (nhanVien in danhSachNhanVien) {
                databaseHelper.addNhanVien(nhanVien)
            }
            Log.d("db", "Số lượng nhân viên: ${databaseHelper.getAllNhanVien().size}")
        }

        val kiemTraDoUong = databaseHelper.getAllDoUong()
        if (kiemTraDoUong.isEmpty()) {
            val danhSachDoUong = getDanhSachDoUong()
            for (doUong in danhSachDoUong) {
                databaseHelper.addDoUong(doUong)
            }
            Log.d("db", "Số lượng đồ uống: ${databaseHelper.getAllDoUong().size}")
        }

        val kiemTraViTriBan = databaseHelper.getAllViTriBan()
        if (kiemTraViTriBan.isEmpty()) {
            val danhSachViTriBan = getDanhSachViTriBan()
            for (viTriBan in danhSachViTriBan) {
                databaseHelper.addViTriBan(viTriBan)
            }
            Log.d("db", "Số lượng bàn: ${databaseHelper.getAllViTriBan().size}")
        }
    }

    private fun getDanhSachNhanVien(): List<NhanVien> {
        return listOf(
            NhanVien(
                1,
                "Đào Văn Hoàng",
                R.drawable.imgemployeecoffe4,
                "Nhân Viên Order",
                "daovanhoang11@gmail.com",
                "daovanhoang11",
                "123456",
                50
            ),
            NhanVien(
                2,
                "Huỳnh Ngọc Dân",
                R.drawable.imgemployeecoffe1,
                "Quản Trị",
                "huynhngodan@gmail.com",
                "huynhngocdan11",
                "123456",
                100
            ),
            NhanVien(
                3,
                "Nguyễn Đức Chuẩn",
                R.drawable.imgemployeecoffe5,
                "Nhân Viên",
                "nguyenducchuan@gmail.com",
                "nguyenducchuan11",
                "123456",
                50
            ),
            NhanVien(
                4,
                "Bùi Tín Thành",
                R.drawable.imgemployeecoffe6,
                "Nhân Viên",
                "buitinthanh@gmail.com",
                "buitinthanh11",
                "123456",
                50
            ),
            NhanVien(
                5,
                "Nguyễn Thị Minh Thu",
                R.drawable.imgemployeecoffe2,
                "Nhân Viên",
                "nguyenthiminhthu11@gmail.com",
                "nguyenthiminhthu11",
                "123456",
                50
            ),
        )
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
            ), DoUong(
                2,
                "Cà phê sữa đá",
                R.drawable.imgcaphe2,
                18000.0,
                "Cà phê đen pha với sữa đặc hoặc sữa tươi, được phục vụ với đá viên, tạo ra một thức uống ngọt ngào, đậm đà và mát lạnh.",
                "Cà phê"
            ), DoUong(
                3,
                "Bạc xỉu",
                R.drawable.imgcaphe3,
                22000.0,
                "cà phê pha với sữa đặc, có vị ngọt nhẹ và béo, thường được phục vụ với ít đá.",
                "Cà phê"
            ), DoUong(
                4,
                "Cà phê sữa nóng",
                R.drawable.imgcaphe4,
                20000.0,
                "cà phê đen pha với sữa tươi hoặc sữa đặc, được phục vụ nóng, mang lại hương vị đậm đà, ngọt ngào và béo ngậy.",
                "Cà phê"
            ), DoUong(
                5,
                "Cà phê sữa dừa",
                R.drawable.imgcaphe5,
                26000.0,
                "cà phê đen kết hợp với sữa dừa, tạo ra một thức uống ngọt ngào, béo ngậy và thơm mát, với hương vị đặc trưng từ dừa kết hợp cùng cà phê đậm đà.",
                "Cà phê"
            ), DoUong(
                6,
                "Cà phê sữa chua",
                R.drawable.imgcaphe6,
                28000.0,
                "sự kết hợp giữa cà phê đen đậm đà và sữa chua, tạo nên một thức uống độc đáo, vừa chua nhẹ, vừa ngọt và béo. mát mẻ, dễ chịu.",
                "Cà phê"
            ), DoUong(
                7,
                "Cà phê đá xay",
                R.drawable.imgcaphe7,
                24000.0,
                "cà phê xay nhuyễn kết hợp với đá viên, tạo ra một thức uống mát lạnh, đậm đà và thường được thêm sữa hoặc đường.",
                "Cà phê"
            ), DoUong(
                8,
                "Cà phê chồn",
                R.drawable.imgcaphe8,
                32000.0,
                "cà phê làm từ hạt đã qua quá trình tiêu hóa trong dạ dày của chồn, mang hương vị đặc biệt, mềm mại và ít đắng.",
                "Cà phê"
            ), DoUong(
                9,
                "Trà sữa trân châu",
                R.drawable.imgtrasua1,
                40000.0,
                "Trà sữa trân châu hương trà sữa thơm ngon, ngọt dịu, kết hợp với trân châu dẻo mềm, tạo nên trải nghiệm thú vị.",
                "Trà sữa"
            ), DoUong(
                10,
                "Trà sữa matcha",
                R.drawable.imgtrasua2,
                45000.0,
                "Trà sữa matcha hương vị trà matcha đậm đà, kết hợp với sữa ngọt ngào, tạo nên thức uống thơm ngon, bổ dưỡng",
                "Trà sữa"
            ), DoUong(
                11,
                "Trà sữa dâu",
                R.drawable.imgtrasua3,
                40000.0,
                "Trà sữa dâu hương vị dâu ngọt ngào, kết hợp với trà sữa mịn màng, tạo nên thức uống thơm ngon, giải khát.",
                "Trà sữa"
            ), DoUong(
                12,
                "Trà sữa hồng trà",
                R.drawable.imgtrasua4,
                40000.0,
                "Trà sữa hồng trà hương vị hồng trà nhẹ nhàng, kết hợp với sữa ngọt thanh, mang đến sự hài hòa, thơm ngon.",
                "Trà sữa"
            ), DoUong(
                13,
                "Trà sữa ô long",
                R.drawable.imgtrasua5,
                45000.0,
                "Trà sữa ô long hương vị trà ô long đậm đà, kết hợp với sữa mịn màng, tạo nên một thức uống thơm ngon, đặc biệt.",
                "Trà sữa"
            ), DoUong(
                14,
                "Trà sữa khoai môn",
                R.drawable.imgtrasua8,
                45000.0,
                "Trà sữa khoai môn hương vị khoai môn béo ngậy, kết hợp với trà sữa ngọt thanh, tạo nên một thức uống độc đáo và thơm ngon.",
                "Trà sữa"
            ), DoUong(
                15,
                "Trà sữa thái",
                R.drawable.imgtrasua7,
                40000.0,
                "Trà sữa thái hương vị trà Thái đậm đà, kết hợp với sữa ngọt mát, tạo nên thức uống thơm ngon, lạ miệng.",
                "Trà sữa"
            ), DoUong(
                16,
                "Sinh tố dừa",
                R.drawable.imgsinhto1,
                35000.0,
                "Sinh tố dừa béo ngậy, thơm mát, giàu dinh dưỡng, giúp giải nhiệt và tăng cường năng lượng.",
                "Sinh tố"
            ), DoUong(
                17,
                "Sinh tố bơ",
                R.drawable.imgsinhto2,
                40000.0,
                "Sinh tố bơ béo mịn, thơm ngon, giàu vitamin E và chất xơ, tốt cho da và sức khỏe.",
                "Sinh tố"
            ), DoUong(
                18,
                "Sinh tố chuối",
                R.drawable.imgsinhto3,
                30000.0,
                "Sinh tố chuối ngọt dịu, béo mịn, giàu kali và vitamin B6, giúp bổ sung năng lượng và tốt cho tiêu hóa.",
                "Sinh tố"
            ), DoUong(
                19,
                "Sinh tố kiwi",
                R.drawable.imgsinhto4,
                40000.0,
                "Sinh tố kiwi mát lạnh, chua ngọt, giàu vitamin C, giúp giải khát và bổ sung năng lượng.",
                "Sinh tố"
            ), DoUong(
                20,
                "Sinh tố nho",
                R.drawable.imgsinhto5,
                45000.0,
                "Sinh tố nho ngọt dịu, thơm mát, giàu chất chống oxy hóa, tốt cho sức khỏe.",
                "Sinh tố"
            ), DoUong(
                21,
                "Sinh tố xoài",
                R.drawable.imgsinhto6,
                35000.0,
                "Sinh tố xoài ngọt thơm, béo mịn, giàu vitamin A, giải khát và bổ sung năng lượng.",
                "Sinh tố"
            ), DoUong(
                22,
                "Sinh tố dưa hấu",
                R.drawable.imgsinhto7,
                30000.0,
                "Sinh tố dưa hấu ngọt mát, thanh nhẹ, giàu nước và vitamin, giúp giải nhiệt hiệu quả.",
                "Sinh tố"
            ), DoUong(
                23,
                "Sinh tố thơm",
                R.drawable.imgsinhto8,
                35000.0,
                "Sinh tố thơm chua ngọt, thơm lừng, giàu vitamin C, hỗ trợ tiêu hóa và làm mát cơ thể.",
                "Sinh tố"
            ), DoUong(
                24,
                "Sinh tố dâu",
                R.drawable.imgsinhto9,
                40000.0,
                "Sinh tố dâu chua ngọt dịu, thơm ngon, giàu vitamin C và chất chống oxy hóa.",
                "Sinh tố"
            )
        )
    }

    private fun getDanhSachViTriBan(): List<ViTriBan> {
        return listOf(
            ViTriBan(
                1,
                "Bàn số 1 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                2,
                "Bàn số 2 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                3,
                "Bàn số 3 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                4,
                "Bàn số 4 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                5,
                "Bàn số 5 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                6,
                "Bàn số 6 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                7,
                "Bàn số 7 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                8,
                "Bàn số 8 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                9,
                "Bàn số 9 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                10,
                "Bàn số 10 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                11,
                "Bàn số 11 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                12,
                "Bàn số 12 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                13,
                "Bàn số 13 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                14,
                "Bàn số 14 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                15,
                "Bàn số 15 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                16,
                "Bàn số 16 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                17,
                "Bàn số 17 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                18,
                "Bàn số 18 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                19,
                "Bàn số 19 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                20,
                "Bàn số 20 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                21,
                "Bàn số 21 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                22,
                "Bàn số 22 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                23,
                "Bàn số 23 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                24,
                "Bàn số 24 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                25,
                "Bàn số 25 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                26,
                "Bàn số 26 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                27,
                "Bàn số 27 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                28,
                "Bàn số 28 (A4)",
                R.drawable.imgtable,
            ), ViTriBan(
                29,
                "Bàn số 29 (A1)",
                R.drawable.imgtable,
            ), ViTriBan(
                30,
                "Bàn số 30 (A2)",
                R.drawable.imgtable,
            ), ViTriBan(
                31,
                "Bàn số 31 (A3)",
                R.drawable.imgtable,
            ), ViTriBan(
                32,
                "Bàn số 32 (A4)",
                R.drawable.imgtable,
            )
        )
    }

    private fun setControl() {
        edtTen = findViewById(R.id.edtTen)
        edtMatKhau = findViewById(R.id.edtMatKhau)
        btnDangNhap = findViewById(R.id.btnDangNhap)
        ivShowMatKhau = findViewById(R.id.ivShowMatKhau)

    }
}