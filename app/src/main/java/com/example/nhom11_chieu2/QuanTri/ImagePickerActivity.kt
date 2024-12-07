package com.example.nhom11_chieu2.QuanTri

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhom11_chieu2.QuanTri.adapter.ImageAdapter
import com.example.nhom11_chieu2.R

class ImagePickerActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val images = listOf(
        R.drawable.imgcaphe1,
        R.drawable.imgcaphe2,
        R.drawable.imgcaphe3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)

        setControl()
        setEvent()
    }

    private fun setControl() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = ImageAdapter(images) { selectedImage ->
            val resultIntent = Intent()
            resultIntent.putExtra("selectedImage", selectedImage)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun setEvent() {

    }
}
