package com.example.nhom11_chieu2.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.text.Normalizer

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "DBQuanLyQuanCoffe", null, 3) {
    override fun onCreate(db: SQLiteDatabase?) {
        db ?: return
        val sqlCreateTableViTriBan = """ 
                CREATE TABLE ViTriBan(  
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                ten TEXT, 
                hinhAnh INTEGER
                )"""
        db?.execSQL(sqlCreateTableViTriBan)

        val sqlCreateTableDoUong = """
            CREATE TABLE DoUong(
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                ten TEXT,
                hinhAnh INTEGER,
                gia REAL,
                moTa TEXT,
                loai TEXT
            )"""
        db?.execSQL(sqlCreateTableDoUong)

        val sqlCreateTableOrders = """
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
        db?.execSQL(sqlCreateTableOrders)

        val sqlCreateTableThanhToan = """
            CREATE TABLE ThanhToan(
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                ten TEXT,
                hinhAnh INTEGER,
                gia REAL,
                soLuong INTEGER,
                moTa TEXT,
                ngayThanhToan TEXT DEFAULT (strftime('%Y-%m-%d %H:%M:%S', 'now'))
            )"""
        db?.execSQL(sqlCreateTableThanhToan)

        val sqlCreateTableNguoiDung = """
            CREATE TABLE NguoiDung(
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                hoTen TEXT,
                chucVu TEXT,
                email TEXT,
                tenDangNhap TEXT UNIQUE,
                matKhau TEXT,
                quyen INTEGER
            )"""
        db?.execSQL(sqlCreateTableNguoiDung)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 3) {
            db?.execSQL("DROP TABLE IF EXISTS ViTriBan")
            db?.execSQL("DROP TABLE IF EXISTS DoUong")
            db?.execSQL("DROP TABLE IF EXISTS Orders")
            db?.execSQL("DROP TABLE IF EXISTS ThanhToan")
            db?.execSQL("DROP TABLE IF EXISTS NguoiDung")
            onCreate(db)
        }
    }

    fun addViTriBan(viTriBan: ViTriBan) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("ten", viTriBan.ten)
            put("hinhAnh", viTriBan.hinhAnh)
        }
        db.insertWithOnConflict("ViTriBan", null, cv, SQLiteDatabase.CONFLICT_IGNORE)
        db.close()
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

    fun addDoUong(doUong: DoUong) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("ten", doUong.ten)
            put("hinhAnh", doUong.hinhAnh)
            put("gia", doUong.gia)
            put("moTa", doUong.moTa)
            put("loai", doUong.loai)
        }
        db.insertWithOnConflict("DoUong", null, cv, SQLiteDatabase.CONFLICT_IGNORE)
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

    fun getDoUongByMa(ma: Int): DoUong? {
        val db = readableDatabase
        val sql = "SELECT * FROM DoUong WHERE ma = ?"
        val cursor = db.rawQuery(sql, arrayOf(ma.toString()))
        var doUong: DoUong? = null
        cursor.use {
            val tenColumnIndex = it.getColumnIndex("ten")
            val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
            val giaColumnIndex = it.getColumnIndex("gia")
            val moTaColumnIndex = it.getColumnIndex("moTa")
            val loaiColumnIndex = it.getColumnIndex("loai")
            if (it.moveToFirst()) {
                val ten = it.getString(tenColumnIndex)
                val hinhAnh = it.getInt(hinhAnhColumnIndex)
                val gia = it.getDouble(giaColumnIndex)
                val moTa = it.getString(moTaColumnIndex)
                val loai = it.getString(loaiColumnIndex)
                doUong = DoUong(ma, ten, hinhAnh, gia, moTa, loai)
            }
        }
        db.close()
        return doUong
    }

    fun searchDoUong(key: String): List<DoUong> {
        val danhSachDoUong = mutableListOf<DoUong>()
        val db = readableDatabase
        val sql = """
        SELECT * FROM DoUong 
        WHERE ten LIKE ? OR loai LIKE ?
        """
        val cursor = db.rawQuery(sql, arrayOf("%$key%", "%$key%"))
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
                        it.getString(loaiColumnIndex)
                    )
                )
            }
        }
        db.close()
        return danhSachDoUong
    }

    fun addOrder(order: Order) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("ten", order.ten)
            put("hinhAnh", order.hinhAnh)
            put("gia", order.gia)
            put("soLuong", order.soLuong)
            put("moTa", order.moTa)
            put("maDoUong", order.maDoUong)
        }
        db.insertWithOnConflict("Orders", null, cv, SQLiteDatabase.CONFLICT_IGNORE)
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
        val cv = ContentValues().apply {
            put("soLuong", soLuongMoi)
        }
        val sql = "maDoUong = ?"
        db.update("Orders", cv, sql, arrayOf(maDoUong.toString()))
        db.close()
    }

    fun deleteAllOrders() {
        val db = writableDatabase
        db.execSQL("DELETE FROM Orders") // Xóa tất cả bản ghi trong bảng Orders
        db.close()
    }

    fun addThanhToan(thanhToan: ThanhToan) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("ten", thanhToan.ten)
            put("hinhAnh", thanhToan.hinhAnh)
            put("gia", thanhToan.gia)
            put("soLuong", thanhToan.soLuong)
            put("moTa", thanhToan.moTa)
            put("ngayThanhToan", thanhToan.ngayThanhToan)
        }
        db.insertWithOnConflict("ThanhToan", null, cv, SQLiteDatabase.CONFLICT_IGNORE)
        db.close()
    }

    fun getAllThanhToan(): List<ThanhToan> {
        val danhSachThanhToan = mutableListOf<ThanhToan>()
        val db = readableDatabase
        val sql = "SELECT * FROM ThanhToan"
        val cursor = db.rawQuery(sql, null)
        cursor.use {
            while (it.moveToNext()) {
                val maColumnIndex = it.getColumnIndex("ma")
                val tenColumnIndex = it.getColumnIndex("ten")
                val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
                val giaColumnIndex = it.getColumnIndex("gia")
                val soLuongColumnIndex = it.getColumnIndex("soLuong")
                val moTaColumnIndex = it.getColumnIndex("moTa")
                val ngayThanhToanColumnIndex = it.getColumnIndex("ngayThanhToan")

                danhSachThanhToan.add(
                    ThanhToan(
                        it.getInt(maColumnIndex),
                        it.getString(tenColumnIndex),
                        it.getInt(hinhAnhColumnIndex),
                        it.getDouble(giaColumnIndex),
                        it.getInt(soLuongColumnIndex),
                        it.getString(moTaColumnIndex),
                        it.getString(ngayThanhToanColumnIndex) ?: ""
                    )
                )
            }
        }
        db.close()
        return danhSachThanhToan
    }

    fun deleteAllThanhToan() {
        val db = writableDatabase
        db.execSQL("DELETE FROM ThanhToan")
        db.close()
    }

    fun addNguoiDung(nguoiDung: NguoiDung) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("hoTen", nguoiDung.hoTen)
            put("chucVu", nguoiDung.chucVu)
            put("email", nguoiDung.email)
            put("tenDangNhap", nguoiDung.tenDangNhap)
            put("matKhau", nguoiDung.matKhau)
            put("quyen", nguoiDung.quyen)
        }
        db.insertWithOnConflict("NguoiDung", null, cv, SQLiteDatabase.CONFLICT_IGNORE)
        db.close()
    }

    fun getAllNguoiDung(): List<NguoiDung> {
        val danhSachNguoiDung = mutableListOf<NguoiDung>()
        val db = readableDatabase
        val sql = "SELECT * FROM NguoiDung"
        val cursor = db.rawQuery(sql, null)
        cursor.use {
            while (it.moveToNext()) {
                val maColumnIndex = it.getColumnIndex("ma")
                val hoTenColumnIndex = it.getColumnIndex("hoTen")
                val chucVuColumnIndex = it.getColumnIndex("chucVu")
                val emailColumnIndex = it.getColumnIndex("email")
                val tenDangNhapColumnIndex = it.getColumnIndex("tenDangNhap")
                val matKhauColumnIndex = it.getColumnIndex("matKhau")
                val quyenColumnIndex = it.getColumnIndex("quyen")
                danhSachNguoiDung.add(
                    NguoiDung(
                        it.getInt(maColumnIndex),
                        it.getString(hoTenColumnIndex),
                        it.getString(chucVuColumnIndex),
                        it.getString(emailColumnIndex),
                        it.getString(tenDangNhapColumnIndex),
                        it.getString(matKhauColumnIndex),
                        it.getInt(quyenColumnIndex)
                    )
                )
            }
        }
        db.close()
        return danhSachNguoiDung
    }

    fun dangNhap(tenDangNhap: String, matKhau: String): Int? {
        val db = readableDatabase
        val sql = "SELECT quyen FROM NguoiDung WHERE tenDangNhap = ? AND matKhau = ?"
        val cursor = db.rawQuery(sql, arrayOf(tenDangNhap, matKhau))
        var quyen: Int? = null
        cursor.use {
            val quyenColumnIndex = it.getColumnIndex("quyen")
            if (it.moveToFirst()) {
                quyen = it.getInt(quyenColumnIndex)
            }
        }
        db.close()
        return quyen
    }
}

