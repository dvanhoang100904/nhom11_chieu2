package com.example.nhom11_chieu2

import android.os.Parcel
import android.os.Parcelable


data class ThanhToan(
    val ma: Int,
    val ten: String,
    val hinhAnh: Int,
    val gia: Double,
    var soLuong: Int,
    val moTa: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ma)
        parcel.writeString(ten)
        parcel.writeInt(hinhAnh)
        parcel.writeDouble(gia)
        parcel.writeInt(soLuong)
        parcel.writeString(moTa)
    }

    companion object CREATOR : Parcelable.Creator<ThanhToan> {
        override fun createFromParcel(parcel: Parcel): ThanhToan {
            return ThanhToan(parcel)
        }

        override fun newArray(size: Int): Array<ThanhToan?> {
            return arrayOfNulls(size)
        }
    }

}