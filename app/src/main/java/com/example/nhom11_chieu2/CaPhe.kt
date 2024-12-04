package com.example.nhom11_chieu2

import android.os.Parcel
import android.os.Parcelable

data class CaPhe(
    val ma: Int,
    val ten: String,
    val hinhAnh: Int,
    val gia: Double,
    val moTa: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readDouble(),
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
        parcel.writeString(moTa)
    }

    companion object CREATOR : Parcelable.Creator<CaPhe> {
        override fun createFromParcel(parcel: Parcel): CaPhe {
            return CaPhe(parcel)
        }

        override fun newArray(size: Int): Array<CaPhe?> {
            return arrayOfNulls(size)
        }
    }
}