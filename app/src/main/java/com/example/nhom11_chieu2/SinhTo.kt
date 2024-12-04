package com.example.nhom11_chieu2

import android.os.Parcel
import android.os.Parcelable

data class SinhTo(
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
        parcel.readString() ?: "",
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ma)
        parcel.writeString(ten)
        parcel.writeInt(hinhAnh)
        parcel.writeDouble(gia)
        parcel.writeString(moTa)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SinhTo> {
        override fun createFromParcel(parcel: Parcel): SinhTo {
            return SinhTo(parcel)
        }

        override fun newArray(size: Int): Array<SinhTo?> {
            return arrayOfNulls(size)
        }
    }
}