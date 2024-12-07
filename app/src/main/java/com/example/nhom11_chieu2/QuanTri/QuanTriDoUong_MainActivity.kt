package com.example.nhom11_chieu2.QuanTri

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.adapter.DoUongAdapter
import com.example.nhom11_chieu2.QuanTri.model.DoUong
import com.example.nhom11_chieu2.R

class QuanTriDoUong_MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddDrink: ImageView
    private lateinit var searchEditText: EditText
    private lateinit var adapter: DoUongAdapter
    private lateinit var imgTheLoai: ImageView
    private lateinit var imgHome: ImageView
    private var doUongList = mutableListOf<DoUong>()

    companion object {
        const val REQUEST_CODE_ADD_DRINK = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quan_tri_do_uong_main)


        setControl()
        setEvent()
    }

    private fun setControl() {
        recyclerView = findViewById(R.id.recyclerviewDoUong)
        btnAddDrink = findViewById(R.id.btn_addDoUong)
        searchEditText = findViewById(R.id.et_searchDoUong)
        imgTheLoai = findViewById(R.id.theLoai)
        imgHome = findViewById(R.id.home)
        // Khởi tạo danh sách đồ uống
        doUongList = mutableListOf(
            DoUong(
                "Cà phê",
                "Cà phê đen",
                "12.000 VND",
                "Hạt cà phê rang xay, nước sôi, đá viên.",
                R.drawable.imgcaphe1
            ),
            DoUong(
                "Cà phê",
                "Cà phê sữa đá",
                "15.000 VND",
                "Cà phê đậm đặc, sữa đặc, đường và đá.",
                R.drawable.imgcaphe2
            ),
            DoUong(
                "Cà phê",
                "Bạc xỉu",
                "18.000 VND",
                "Cà phê đen đậm đặc, sữa đặc và đá.",
                R.drawable.imgcaphe3
            )
        )
        //timkiem
        doUongList.addAll(doUongList)
        // Cấu hình adapter và RecyclerView
        adapter = DoUongAdapter(doUongList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setEvent() {

        btnAddDrink.setOnClickListener {
            val intent = Intent(this, ThemDoUong_MainActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_DRINK)
        }
        imgTheLoai.setOnClickListener {
            val intent = Intent(this, QuanLiMenu_MainActivity::class.java)
            startActivity(intent)
        }
        imgHome.setOnClickListener {
            val intent = Intent(this, QuanTriActivity::class.java)
            startActivity(intent)

        }
        //hien thi tim kiem
        searchEditText.addTextChangedListener(object : android.text.TextWatcher {  // Thay đổi ở đây
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                // Không cần thực hiện gì ở đây
                //thuc hien chuc nang loc theo the loai
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                val query = charSequence.toString().trim()
                if (query.isEmpty()) {
                    // Nếu ô tìm kiếm trống, hiển thị lại toàn bộ danh sách
                    doUongList.clear()
                    doUongList.addAll(doUongList)
                } else {
                    // Lọc danh sách theo tên
                    val filteredList = doUongList.filter {
                        it.ten.contains(query, ignoreCase = true)
                    }
                    doUongList.clear()
                    doUongList.addAll(filteredList)
                }
                // Cập nhật RecyclerView
                adapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(editable: android.text.Editable?) {
                // Không cần thực hiện gì ở đây
                //theo danh sach
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_ADD_DRINK && resultCode == Activity.RESULT_OK) {
            // Lấy dữ liệu từ Intent
            val theLoai = data?.getStringExtra("theLoai") ?: ""
            val ten = data?.getStringExtra("ten") ?: ""
            val gia = data?.getStringExtra("gia") ?: ""
            val moTa = data?.getStringExtra("moTa") ?: ""

            // thêm đồ uống
            val newDrink = DoUong(theLoai, ten, gia, moTa, R.drawable.imgcaphe1) // Ảnh mặc định
            doUongList.add(newDrink)
            adapter.notifyItemInserted(doUongList.size - 1)
        } else if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            // cập nhật đồ uống
            val position = data?.getIntExtra("position", -1) ?: -1
            if (position != -1) {
                // Dùng `data?.get...`
                val theLoai = data?.getStringExtra("theLoai") ?: ""
                val ten = data?.getStringExtra("ten") ?: ""
                val gia = data?.getStringExtra("gia") ?: ""
                val moTa = data?.getStringExtra("moTa") ?: ""
                val hinhAnh =
                    data?.getIntExtra("hinhAnh", R.drawable.imgcaphe1) ?: R.drawable.imgcaphe1
                doUongList[position] = DoUong(theLoai, ten, gia, moTa, hinhAnh)
                adapter.notifyItemChanged(position)
            }
        }
    }
}
