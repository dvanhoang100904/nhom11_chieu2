package com.example.nhom11_chieu2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "DBQuanLyQuanCoffe", null, 3) {
    override fun onCreate(db: SQLiteDatabase?) {
        db ?: return  // Nếu `db` null, thoát sớm.
        val createTableViTriBanQuery = """ 
                CREATE TABLE ViTriBan(  
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                ten TEXT, 
                hinhAnh INTEGER
                )"""
        db?.execSQL(createTableViTriBanQuery)

        val createTableDoUongQuery = """
            CREATE TABLE DoUong(
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                ten TEXT,
                hinhAnh INTEGER,
                gia REAL,
                moTa TEXT,
                loai TEXT
            )"""
        db?.execSQL(createTableDoUongQuery)

        val createTableOrdersQuery = """
            CREATE TABLE Orders(
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                ten TEXT,
                hinhAnh INTEGER,
                gia REAL,
                soLuong INTEGER,
                moTa TEXT,
                maDoUong INTEGER, 
                FOREIGN KEY(maDoUong) REFERENCES DoUong(ma)
            )"""
        db?.execSQL(createTableOrdersQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 3) {
            db?.execSQL("DROP TABLE IF EXISTS ViTriBan")
            db?.execSQL("DROP TABLE IF EXISTS DoUong")
            db?.execSQL("DROP TABLE IF EXISTS Orders")
            onCreate(db)
        }
    }

    fun getAllViTriBan(): List<ViTriBan> {
        val danhSachViTriBan = mutableListOf<ViTriBan>()
        val db = readableDatabase
        val sql = "SELECT * FROM ViTriBan"
        val cursor = db.rawQuery(sql, null)
        cursor.use {
            while (it.moveToNext()) {
                val maColumnIndex = it.getColumnIndex("ma")
                val tenColumnIndex = it.getColumnIndex("ten")
                val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
                danhSachViTriBan.add(
                    ViTriBan(
                        it.getInt(maColumnIndex),
                        it.getString(tenColumnIndex),
                        it.getInt(hinhAnhColumnIndex)
                    )
                )
            }
        }
        db.close()
        return danhSachViTriBan
    }

    fun addViTriBan(viTriBan: ViTriBan) {
        val db = writableDatabase

        // Kiểm tra tên có rỗng không
        if (viTriBan.ten.isNullOrEmpty()) {
            return
        }

        // Dùng phương thức insertWithOnConflict để tránh chèn trùng lặp
        val values = ContentValues().apply {
            put("ten", viTriBan.ten)
            put("hinhAnh", viTriBan.hinhAnh)
        }
        db.insertWithOnConflict("ViTriBan", null, values, SQLiteDatabase.CONFLICT_IGNORE)

        db.close()
    }

    fun getAllDoUong(): List<DoUong> {
        val danhSachDoUong = mutableListOf<DoUong>()
        val db = readableDatabase
        val sql = "SELECT * FROM DoUong"
        val cursor = db.rawQuery(sql, null)
        cursor.use {
            while (it.moveToNext()) {
                val maColumnIndex = it.getColumnIndex("ma")
                val tenColumnIndex = it.getColumnIndex("ten")
                val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
                val giaColumnIndex = it.getColumnIndex("gia")
                val moTaColumnIndex = it.getColumnIndex("moTa")
                val loaiColumnIndex = it.getColumnIndex("loai")
                danhSachDoUong.add(
                    DoUong(
                        it.getInt(maColumnIndex),
                        it.getString(tenColumnIndex),
                        it.getInt(hinhAnhColumnIndex),
                        it.getDouble(giaColumnIndex),
                        it.getString(moTaColumnIndex),
                        it.getString(loaiColumnIndex),
                    )
                )
            }
        }
        db.close()
        return danhSachDoUong
    }

    fun addDoUong(doUong: DoUong) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ten", doUong.ten)
            put("hinhAnh", doUong.hinhAnh)
            put("gia", doUong.gia)
            put("moTa", doUong.moTa)
            put("loai", doUong.loai)
        }
        db.insertWithOnConflict("DoUong", null, values, SQLiteDatabase.CONFLICT_IGNORE)
        db.close()
    }

    fun getDoUongByMa(ma: Int): DoUong? {
        val db = readableDatabase
        val sql = "SELECT * FROM DoUong WHERE ma = ?"
        val cursor = db.rawQuery(sql, arrayOf(ma.toString()))
        var doUong: DoUong? = null
        cursor.use {
            if (it.moveToFirst()) {
                val ten = it.getString(it.getColumnIndexOrThrow("ten"))
                val hinhAnh = it.getInt(it.getColumnIndexOrThrow("hinhAnh"))
                val gia = it.getDouble(it.getColumnIndexOrThrow("gia"))
                val moTa = it.getString(it.getColumnIndexOrThrow("moTa"))
                val loai = it.getString(it.getColumnIndexOrThrow("loai"))
                doUong = DoUong(ma, ten, hinhAnh, gia, moTa, loai)
            }
        }
        db.close()
        return doUong
    }

    fun addOrder(order: Order) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ten", order.ten)
            put("hinhAnh", order.hinhAnh)
            put("gia", order.gia)
            put("soLuong", order.soLuong)
            put("moTa", order.moTa)
            put("maDoUong", order.maDoUong)
        }
        db.use {
            it.insertWithOnConflict("Orders", null, values, SQLiteDatabase.CONFLICT_IGNORE)
        }
    }

    fun getAllOrders(): MutableList<Order> {
        val danhSachOrder = mutableListOf<Order>()
        val db = readableDatabase
        val sql = "SELECT * FROM Orders"
        val cursor = db.rawQuery(sql, null)
        cursor.use {
            while (it.moveToNext()) {
                val maColumnIndex = it.getColumnIndex("ma")
                val tenColumnIndex = it.getColumnIndex("ten")
                val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
                val giaColumnIndex = it.getColumnIndex("gia")
                val soLuongColumnIndex = it.getColumnIndex("soLuong")
                val moTaColumnIndex = it.getColumnIndex("moTa")
                val maDoUongColumnIndex = it.getColumnIndex("maDoUong")
                danhSachOrder.add(
                    Order(
                        it.getInt(maColumnIndex),
                        it.getString(tenColumnIndex),
                        it.getInt(hinhAnhColumnIndex),
                        it.getDouble(giaColumnIndex),
                        it.getInt(soLuongColumnIndex),
                        it.getString(moTaColumnIndex),
                        it.getInt(maDoUongColumnIndex)
                    )
                )
            }
        }
        db.close()
        return danhSachOrder
    }

    fun getOrdersByMaDoUong(maDoUong: Int): Order? {
        val db = readableDatabase
        val sql = "SELECT * FROM Orders WHERE maDoUong = ?"
        val cursor = db.rawQuery(sql, arrayOf(maDoUong.toString()))
        var order: Order? = null
        cursor.use {
            val maColumnIndex = it.getColumnIndex("ma")
            val tenColumnIndex = it.getColumnIndex("ten")
            val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
            val giaColumnIndex = it.getColumnIndex("gia")
            val soLuongColumnIndex = it.getColumnIndex("soLuong")
            val moTaColumnIndex = it.getColumnIndex("moTa")
            val maDoUongColumnIndex = it.getColumnIndex("maDoUong")
            if (it.moveToFirst()) {
                order = Order(
                    it.getInt(maColumnIndex),
                    it.getString(tenColumnIndex),
                    it.getInt(hinhAnhColumnIndex),
                    it.getDouble(giaColumnIndex),
                    it.getInt(soLuongColumnIndex),
                    it.getString(moTaColumnIndex),
                    it.getInt(maDoUongColumnIndex)
                )
            }
        }
        db.close()
        return order
    }

    fun deleteOrderByMa(ma: Int) {
        val db = writableDatabase
        val sql = "ma = ?"
        db.delete("orders", sql, arrayOf(ma.toString()))
        db.close()
    }

    fun updateSoLuongOrderByMaDoUong(maDoUong: Int, soLuongMoi: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("soLuong", soLuongMoi)
        }
        val sql = "maDoUong = ?"
        db.update("Orders", values, sql, arrayOf(maDoUong.toString()))
        db.close()
    }

    fun deleteAllOrders() {
        val db = writableDatabase
        db.execSQL("DELETE FROM Orders") // Xóa tất cả bản ghi trong bảng Orders
        db.close()
    }



}