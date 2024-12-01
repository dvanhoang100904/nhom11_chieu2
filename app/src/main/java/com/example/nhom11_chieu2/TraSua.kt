package com.example.nhom11_chieu2

import android.os.Parcel
import android.os.Parcelable

data class TraSua(
    val ma: Int,
    val ten: String,
    val hinhAnh: Int,
    val gia: Double,
    val soLuong: Int,
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ma)
        parcel.writeString(ten)
        parcel.writeInt(hinhAnh)
        parcel.writeDouble(gia)
        parcel.writeInt(soLuong)
        parcel.writeString(moTa)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TraSua> {
        override fun createFromParcel(parcel: Parcel): TraSua {
            return TraSua(parcel)
        }

        override fun newArray(size: Int): Array<TraSua?> {
            return arrayOfNulls(size)
        }
    }
}