package com.example.nhom11_chieu2.QuanTri

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.adapter.TheLoaiAdapter
import com.example.nhom11_chieu2.QuanTri.model.TheLoai
import com.example.nhom11_chieu2.R

class QuanLiMenu_MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddTheLoai: ImageView
    private lateinit var imgDouong: ImageView
    private lateinit var imgHome: ImageView
    private lateinit var theLoaiAdapter: TheLoaiAdapter
    private val danhSachTheLoai = mutableListOf(
        TheLoai(1, "Sinh Tố"), TheLoai(2, "Trà Sữa"), TheLoai(3, "Cà Phê")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quan_li_menu_main)

        setControl()
        setEvent()
    }

    private fun setControl() {
        recyclerView = findViewById(R.id.RecyclerViewMenu)
        btnAddTheLoai = findViewById(R.id.btn_addTheLoai)
        imgDouong = findViewById(R.id.douong)
        imgHome = findViewById(R.id.home)
        // Initialize the RecyclerView with an adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        theLoaiAdapter = TheLoaiAdapter(context = this,
            danhSachTheLoai = danhSachTheLoai,
            onEditClick = { theLoai ->
                // When the edit button is clicked, launch the SuaTheLoai_MainActivity to edit
                val intent = Intent(this, SuaTheLoai_MainActivity::class.java).apply {
                    putExtra("id", theLoai.id)
                    putExtra("tenTheLoai", theLoai.tenTheLoai)
                }
                editTheLoaiLauncher.launch(intent)
            },
            onDeleteClick = { theLoai ->
                // Handle deletion of the item
                danhSachTheLoai.remove(theLoai)
                theLoaiAdapter.notifyDataSetChanged()
            })
        recyclerView.adapter = theLoaiAdapter
    }

    private fun setEvent() {
        // Handle adding a new category
        btnAddTheLoai.setOnClickListener {
            val intent = Intent(this, ThemTheLoai_MainActivity::class.java)
            startActivity(intent)
        }
        imgDouong.setOnClickListener {
            val intent = Intent(this, QuanTriDoUong_MainActivity::class.java)
            startActivity(intent)
        }
        imgHome.setOnClickListener {
            val intent = Intent(this, QuanTriActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()
        // Fetch any new categories added
        val sharedPref = getSharedPreferences("TheLoaiData", Context.MODE_PRIVATE)
        val newTheLoaiName = sharedPref.getString("newTheLoai", null)
        if (!newTheLoaiName.isNullOrEmpty()) {
            val newTheLoai = TheLoai(id = (1..1000).random(), tenTheLoai = newTheLoaiName)
            danhSachTheLoai.add(newTheLoai)
            theLoaiAdapter.notifyDataSetChanged()
            sharedPref.edit().remove("newTheLoai").apply()
        }
    }

    // Register the ActivityResultContracts to handle results when editing a category
    private val editTheLoaiLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val id = result.data?.getIntExtra("id", -1) ?: -1
            val updatedName = result.data?.getStringExtra("tenTheLoai")

            // Update the category if a valid ID and name are provided
            if (id != -1 && !updatedName.isNullOrEmpty()) {
                val index = danhSachTheLoai.indexOfFirst { it.id == id }
                if (index != -1) {
                    danhSachTheLoai[index] = danhSachTheLoai[index].copy(tenTheLoai = updatedName)
                    theLoaiAdapter.notifyItemChanged(index) // Notify adapter that an item was updated
                }
            }
        }
    }
}
