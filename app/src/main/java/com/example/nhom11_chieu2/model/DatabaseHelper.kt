package com.example.nhom11_chieu2.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "DBQuanLyQuanCoffe", null, 4) {
    override fun onCreate(db: SQLiteDatabase?) {
        db ?: return
        // Bảng ViTriBan
        val sqlCreateTableViTriBan = """ 
                CREATE TABLE ViTriBan(  
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                ten TEXT, 
                hinhAnh INTEGER
                )"""
        db?.execSQL(sqlCreateTableViTriBan)

        // Bảng DoUong
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

        // Bảng OrderViTriBan
        val sqlCreateTableOrderViTriBan = """ 
                CREATE TABLE OrderViTriBan(  
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                maViTriBan INTEGER, 
                FOREIGN KEY(maViTriBan) REFERENCES ViTriBan(ma)
                )"""
        db?.execSQL(sqlCreateTableOrderViTriBan)

        // Bảng Orders
        val sqlCreateTableOrders = """
            CREATE TABLE Orders(
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                ten TEXT,
                hinhAnh INTEGER,
                gia REAL,
                soLuong INTEGER,
                moTa TEXT,
                maDoUong INTEGER, 
                maOrderViTriBan INTEGER,
                FOREIGN KEY(maDoUong) REFERENCES DoUong(ma),
                FOREIGN KEY(maOrderViTriBan) REFERENCES OrderViTriBan(ma)
            )"""
        db?.execSQL(sqlCreateTableOrders)

        // Bảng ThanhToan
        val sqlCreateTableThanhToan = """
            CREATE TABLE ThanhToan(
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                ten TEXT,
                hinhAnh INTEGER,
                gia REAL,
                soLuong INTEGER,
                moTa TEXT,
                ngayThanhToan TEXT DEFAULT CURRENT_TIMESTAMP,
                maViTriBan INTEGER,
                FOREIGN KEY(maViTriBan) REFERENCES ViTriBan(ma)

            )"""
        db?.execSQL(sqlCreateTableThanhToan)

        // Bảng NhanVien
        val sqlCreateTableNhanVien = """
            CREATE TABLE NhanVien(
                ma INTEGER PRIMARY KEY AUTOINCREMENT,
                hoTen TEXT,
                hinhAnh INTEGER,
                chucVu TEXT,
                email TEXT,
                tenDangNhap TEXT UNIQUE,
                matKhau TEXT,
                quyen INTEGER,
                isLoggedIn INTEGER DEFAULT 0
            )"""
        db?.execSQL(sqlCreateTableNhanVien)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 4) {
            db?.execSQL("DROP TABLE IF EXISTS ViTriBan")
            db?.execSQL("DROP TABLE IF EXISTS DoUong")
            db?.execSQL("DROP TABLE IF EXISTS Orders")
            db?.execSQL("DROP TABLE IF EXISTS ThanhToan")
            db?.execSQL("DROP TABLE IF EXISTS NhanVien")
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
        val rs = db.rawQuery(sql, null)
        rs.use {
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

    fun getViTriBanByMa(ma: Int): ViTriBan? {
        val db = readableDatabase
        val sql = "SELECT * FROM ViTriBan WHERE ma = ?"
        val rs = db.rawQuery(sql, arrayOf(ma.toString()))
        var viTriBan: ViTriBan? = null
        rs.use {
            val tenColumnIndex = it.getColumnIndex("ten")
            val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
            if (it.moveToFirst()) {
                val ten = it.getString(tenColumnIndex)
                val hinhAnh = it.getInt(hinhAnhColumnIndex)
                viTriBan = ViTriBan(ma, ten, hinhAnh)
            }
        }
        db.close()
        return viTriBan
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

    fun getAllDoUong(): MutableList<DoUong> {
        val danhSachDoUong = mutableListOf<DoUong>()
        val db = readableDatabase
        val sql = "SELECT * FROM DoUong"
        val rs = db.rawQuery(sql, null)
        rs.use {
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

    fun getAllDoUongNgauNhien(limit: Int): MutableList<DoUong> {
        val danhSachDoUong = mutableListOf<DoUong>()
        val db = readableDatabase
        val sql = "SELECT * FROM DoUong ORDER BY RANDOM() LIMIT $limit"
        val rs = db.rawQuery(sql, null)
        rs.use {
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
        val rs = db.rawQuery(sql, arrayOf(ma.toString()))
        var doUong: DoUong? = null
        rs.use {
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
        val sql = "SELECT * FROM DoUong WHERE ten LIKE ? OR loai LIKE ? "
        val rs = db.rawQuery(sql, arrayOf("%$key%", "%$key%"))
        rs.use {
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

    fun searchQTDoUong(key: String): MutableList<DoUong> {
        val danhSachDoUong = mutableListOf<DoUong>()
        val db = readableDatabase
        val sql = "SELECT * FROM DoUong WHERE ten LIKE ? OR loai LIKE ? "
        val rs = db.rawQuery(sql, arrayOf("%$key%", "%$key%"))
        rs.use {
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

    fun deleteDoUongByMa(ma: Int) {
        val db = writableDatabase
        val sql = "ma = ?"
        db.delete("DoUong", sql, arrayOf(ma.toString()))
        db.close()
    }

    fun updateDoUongByMa(
        ma: Int,
        ten: String,
        hinhAnh: Int,
        gia: Double,
        moTa: String,
        loai: String
    ) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("ten", ten)
            put("hinhAnh", hinhAnh)
            put("gia", gia)
            put("moTa", moTa)
            put("loai", loai)
        }
        val sql = "ma = ?"
        db.update("DoUong", cv, sql, arrayOf(ma.toString()))
        db.close()
    }

    fun addOrderByMaViTriBanVaMaDoUong(maViTriBan: Int, maDoUong: Int, soLuong: Int) {
        val db = writableDatabase
        // Kiểm tra nếu đã có order cho bàn và đồ uống này
        val sql = "SELECT ma, soLuong FROM Orders WHERE maOrderViTriBan = ? AND maDoUong = ?"
        val rs = db.rawQuery(sql, arrayOf(maViTriBan.toString(), maDoUong.toString()))
        rs.use {
            val maColumnIndex = it.getColumnIndex("ma")
            val soLuongColumnIndex = it.getColumnIndex("soLuong")

            if (it.moveToFirst()) {
                // Nếu đã có order cho món uống ở bàn này
                val maOrder = it.getInt(maColumnIndex)
                val currentSoLuong = it.getInt(soLuongColumnIndex)
                val soLuongMoi = currentSoLuong + soLuong // Cộng thêm số lượng mới

                val updateCV = ContentValues().apply {
                    put("soLuong", soLuongMoi)
                }
                db.update("Orders", updateCV, "ma = ?", arrayOf(maOrder.toString()))
            } else {
                // Nếu không có order cho món uống ở bàn này
                val orderThemMoiCV = ContentValues().apply {
                    put("maDoUong", maDoUong)
                    put("soLuong", soLuong)
                    put("maOrderViTriBan", maViTriBan)
                }
                db.insert("Orders", null, orderThemMoiCV)
            }
        }
        db.close() // Đảm bảo đóng cơ sở dữ liệu sau khi thao tác xong
    }

    fun getAllOrdersByMaOrderViTriBan(maOrderViTriBan: Int): MutableList<Order> {
        val danhSachOrder = mutableListOf<Order>()
        val db = readableDatabase
        val sql = """
            SELECT o.*, d.ten, d.gia, d.hinhAnh, d.moTa 
            FROM Orders o
            JOIN DoUong d ON o.maDoUong = d.ma
            WHERE o.maOrderViTriBan = ?
            """
        val rs = db.rawQuery(sql, arrayOf(maOrderViTriBan.toString()))
        rs.use {
            val maColumnIndex = it.getColumnIndex("ma")
            val tenColumnIndex = it.getColumnIndex("ten")
            val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
            val giaColumnIndex = it.getColumnIndex("gia")
            val soLuongColumnIndex = it.getColumnIndex("soLuong")
            val moTaColumnIndex = it.getColumnIndex("moTa")
            val maDoUongColumnIndex = it.getColumnIndex("maDoUong")
            val maOrderViTriBanColumnIndex = it.getColumnIndex("maOrderViTriBan")

            while (it.moveToNext()) {
                danhSachOrder.add(
                    Order(
                        ma = it.getInt(maColumnIndex),
                        ten = it.getString(tenColumnIndex) ?: "",
                        hinhAnh = it.getInt(hinhAnhColumnIndex),
                        gia = it.getDouble(giaColumnIndex),
                        soLuong = it.getInt(soLuongColumnIndex),
                        moTa = it.getString(moTaColumnIndex) ?: "",
                        maDoUong = it.getInt(maDoUongColumnIndex),
                        maOrderViTriBan = it.getInt(maOrderViTriBanColumnIndex)
                    )
                )
            }
        }
        db.close()
        return danhSachOrder
    }

    fun getOrdersByMaDoUongVaMaOrderViTriBan(maDoUong: Int, maOrderViTriBan: Int): Order? {
        val db = readableDatabase
        val sql = "SELECT * FROM Orders WHERE maDoUong = ? AND maOrderViTriBan = ?"
        val rs = db.rawQuery(sql, arrayOf(maDoUong.toString(), maOrderViTriBan.toString()))
        var order: Order? = null
        rs.use {
            val maColumnIndex = it.getColumnIndex("ma")
            val tenColumnIndex = it.getColumnIndex("ten")
            val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
            val giaColumnIndex = it.getColumnIndex("gia")
            val soLuongColumnIndex = it.getColumnIndex("soLuong")
            val moTaColumnIndex = it.getColumnIndex("moTa")
            val maDoUongColumnIndex = it.getColumnIndex("maDoUong")
            val maOrderViTriBanColumnIndex = it.getColumnIndex("maOrderViTriBan")

            if (it.moveToFirst()) {
                order = Order(
                    it.getInt(maColumnIndex),
                    it.getString(tenColumnIndex) ?: "",
                    it.getInt(hinhAnhColumnIndex),
                    it.getDouble(giaColumnIndex),
                    it.getInt(soLuongColumnIndex),
                    it.getString(moTaColumnIndex) ?: "",
                    it.getInt(maDoUongColumnIndex),
                    it.getInt(maOrderViTriBanColumnIndex)
                )
            }
        }
        db.close() // Đảm bảo đóng cơ sở dữ liệu
        return order
    }

    fun deleteOrderByMa(ma: Int) {
        val db = writableDatabase
        val sql = "ma = ?"
        db.delete("orders", sql, arrayOf(ma.toString()))
        db.close()
    }

    fun updateSoLuongOrderByMaDoUongVaMaOderViTriBan(
        maDoUong: Int,
        maOrderViTriBan: Int,
        soLuongMoi: Int
    ) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("soLuong", soLuongMoi)
        }
        val sql = "maDoUong = ? AND maOrderViTriBan = ?"
        db.update("Orders", cv, sql, arrayOf(maDoUong.toString(), maOrderViTriBan.toString()))
        db.close()
    }

    fun deleteAllOrdersByMaOrderViTriBan(maOrderViTriBan: Int) {
        val db = writableDatabase
        val sql = "maOrderViTriBan = ?"
        db.delete("Orders", sql, arrayOf(maOrderViTriBan.toString()))
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
            put("maViTriBan", thanhToan.maViTriBan)
        }
        db.insertWithOnConflict("ThanhToan", null, cv, SQLiteDatabase.CONFLICT_IGNORE)
        db.close()
    }

    fun getAllThanhToan(): List<ThanhToan> {
        val danhSachThanhToan = mutableListOf<ThanhToan>()
        val db = readableDatabase
        val sql = "SELECT * FROM ThanhToan "
        val rs = db.rawQuery(sql, null)
        rs.use {
            while (it.moveToNext()) {
                val maColumnIndex = it.getColumnIndex("ma")
                val tenColumnIndex = it.getColumnIndex("ten")
                val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
                val giaColumnIndex = it.getColumnIndex("gia")
                val soLuongColumnIndex = it.getColumnIndex("soLuong")
                val moTaColumnIndex = it.getColumnIndex("moTa")
                val ngayThanhToanColumnIndex = it.getColumnIndex("ngayThanhToan")
                val maViTriBanColumnIndex = it.getColumnIndex("maViTriBan")

                danhSachThanhToan.add(
                    ThanhToan(
                        it.getInt(maColumnIndex),
                        it.getString(tenColumnIndex),
                        it.getInt(hinhAnhColumnIndex),
                        it.getDouble(giaColumnIndex),
                        it.getInt(soLuongColumnIndex),
                        it.getString(moTaColumnIndex),
                        it.getString(ngayThanhToanColumnIndex) ?: "",
                        it.getInt(maViTriBanColumnIndex),

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

    fun addNhanVien(nhanVien: NhanVien) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("hoTen", nhanVien.hoTen)
            put("chucVu", nhanVien.chucVu)
            put("hinhAnh", nhanVien.hinhAnh)
            put("email", nhanVien.email)
            put("tenDangNhap", nhanVien.tenDangNhap)
            put("matKhau", nhanVien.matKhau)
            put("quyen", nhanVien.quyen)
        }
        db.insertWithOnConflict("NhanVien", null, cv, SQLiteDatabase.CONFLICT_IGNORE)
        db.close()
    }

    fun getAllNhanVien(): MutableList<NhanVien> {
        val danhSachNhanVien = mutableListOf<NhanVien>()
        val db = readableDatabase
        val sql = "SELECT * FROM NhanVien"
        val rs = db.rawQuery(sql, null)
        rs.use {
            while (it.moveToNext()) {
                val maColumnIndex = it.getColumnIndex("ma")
                val hoTenColumnIndex = it.getColumnIndex("hoTen")
                val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
                val chucVuColumnIndex = it.getColumnIndex("chucVu")
                val emailColumnIndex = it.getColumnIndex("email")
                val tenDangNhapColumnIndex = it.getColumnIndex("tenDangNhap")
                val matKhauColumnIndex = it.getColumnIndex("matKhau")
                val quyenColumnIndex = it.getColumnIndex("quyen")
                danhSachNhanVien.add(
                    NhanVien(
                        it.getInt(maColumnIndex),
                        it.getString(hoTenColumnIndex),
                        it.getInt(hinhAnhColumnIndex),
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
        return danhSachNhanVien
    }

    fun getNhanViengByMa(ma: Int): NhanVien? {
        val db = readableDatabase
        val sql = "SELECT * FROM NhanVien WHERE ma =?"
        val rs = db.rawQuery(sql, arrayOf(ma.toString()))
        var nhanVien: NhanVien? = null
        rs.use {
            val hoTenColumnIndex = it.getColumnIndex("hoTen")
            val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
            val chucVuColumnIndex = it.getColumnIndex("chucVu")
            val emailColumnIndex = it.getColumnIndex("email")
            val tenDangNhapColumnIndex = it.getColumnIndex("tenDangNhap")
            val matKhauColumnIndex = it.getColumnIndex("matKhau")
            val quyenColumnIndex = it.getColumnIndex("quyen")
            if (it.moveToFirst()) {
                val hoTen = it.getString(hoTenColumnIndex)
                val hinhAnh = it.getInt(hinhAnhColumnIndex)
                val chucVu = it.getString(chucVuColumnIndex)
                val email = it.getString(emailColumnIndex)
                val tenDangNhap = it.getString(tenDangNhapColumnIndex)
                val matKhau = it.getString(matKhauColumnIndex)
                val quyen = it.getInt(quyenColumnIndex)
                nhanVien =
                    NhanVien(ma, hoTen, hinhAnh, chucVu, email, tenDangNhap, matKhau, quyen)
            }
        }
        db.close()
        return nhanVien
    }

    fun deleteNhanVienByMa(ma: Int) {
        val db = writableDatabase
        val sql = "ma = ?"
        db.delete("NhanVien", sql, arrayOf(ma.toString()))
        db.close()

    }

    fun dangNhap(tenDangNhap: String, matKhau: String): Int? {
        val db = writableDatabase
        val sql = "SELECT quyen FROM NhanVien WHERE tenDangNhap = ? AND matKhau = ?"
        val rs = db.rawQuery(sql, arrayOf(tenDangNhap, matKhau))
        var quyen: Int? = null
        rs.use {
            if (it.moveToFirst()) {
                val quyenColumnIndex = it.getColumnIndex("quyen")
                quyen = it.getInt(quyenColumnIndex)

                val updateSql = "UPDATE NhanVien SET isLoggedIn = 1 WHERE tenDangNhap = ?"
                db.execSQL(updateSql, arrayOf(tenDangNhap))
            }
        }
        db.close()
        return quyen
    }

    fun layNhanVienDangNhap(): String? {
        val db = readableDatabase
        val sql = "SELECT hoTen FROM NhanVien WHERE isLoggedIn = 1"
        val rs = db.rawQuery(sql, null)
        var hoTen: String? = null
        rs.use {
            if (it.moveToFirst()) {
                val hoTenColumnIndex = it.getColumnIndex("hoTen")
                hoTen = it.getString(hoTenColumnIndex)
            }
        }
        db.close()
        return hoTen
    }

    fun dangXuat() {
        val db = writableDatabase
        val sql = "UPDATE NhanVien SET isLoggedIn = 0 WHERE isLoggedIn = 1  AND tenDangNhap = ?"
        db.execSQL(sql)
        db.close()
    }

    fun kiemTraEmail(email: String): Boolean {
        val db = readableDatabase
        val sql = "SELECT 1 FROM NhanVien WHERE email = ? LIMIT 1"
        val rs = db.rawQuery(sql, arrayOf(email))
        var isEmail = false
        rs.use {
            if (it.moveToFirst()) {
                isEmail = true
            }
        }
        return isEmail
    }

    fun capNhatMatKhau(email: String, matKhauMoi: String) {
        val db = writableDatabase
        val sql = "UPDATE NhanVien SET matKhau = ? WHERE email = ?"
        db.execSQL(sql, arrayOf(matKhauMoi, email))
        db.close()
    }

    fun kiemTraTenDangNhap(tenDangNhap: String): Boolean {
        val db = readableDatabase
        val sql = "SELECT 1 FROM NhanVien WHERE tenDangNhap = ? LIMIT 1"
        val rs = db.rawQuery(sql, arrayOf(tenDangNhap))
        var isTenDangNhap = false
        rs.use {
            if (it.moveToFirst()) {
                isTenDangNhap = true
            }
        }
        return isTenDangNhap
    }

    fun updateNhanVienByMa(
        ma: Int,
        hoTen: String,
        hinhAnh: Int,
        chucVu: String,
        email: String,
        tenDangNhap: String,
        matKhau: String,
        quyen: Int
    ) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("hoTen", hoTen)
            put("hinhAnh", hinhAnh)
            put("chucVu", chucVu)
            put("email", email)
            put("tenDangNhap", tenDangNhap)
            put("matKhau", matKhau)
            put("quyen", quyen)
        }
        val sql = "ma = ?"
        db.update("NhanVien", cv, sql, arrayOf(ma.toString()))
        db.close()

    }

    fun searchQTNhanVien(key: String): MutableList<NhanVien> {
        val danhSachNhanVien = mutableListOf<NhanVien>()
        val db = readableDatabase
        val sql = "SELECT * FROM NhanVien WHERE hoTen LIKE ? OR chucVu LIKE ?"
        val rs = db.rawQuery(sql, arrayOf("%$key%", "%$key%"))
        rs.use {
            while (it.moveToNext()) {
                val maColumnIndex = it.getColumnIndex("ma")
                val hoTenColumnIndex = it.getColumnIndex("hoTen")
                val hinhAnhColumnIndex = it.getColumnIndex("hinhAnh")
                val chucVuColumnIndex = it.getColumnIndex("chucVu")
                val emailColumnIndex = it.getColumnIndex("email")
                val tenDangNhapColumnIndex = it.getColumnIndex("tenDangNhap")
                val matKhauColumnIndex = it.getColumnIndex("matKhau")
                val quyenColumnIndex = it.getColumnIndex("quyen")
                danhSachNhanVien.add(
                    NhanVien(
                        it.getInt(maColumnIndex),
                        it.getString(hoTenColumnIndex),
                        it.getInt(hinhAnhColumnIndex),
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
        return danhSachNhanVien
    }
}





