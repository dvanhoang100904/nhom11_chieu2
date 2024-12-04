package com.example.nhom11_chieu2

import android.os.Parcel
import android.os.Parcelable

data class ViTriBan(val ma: Int, val ten: String, val hinhAnh: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ma)
        parcel.writeString(ten)
        parcel.writeInt(hinhAnh)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ViTriBan> {
        override fun createFromParcel(parcel: Parcel): ViTriBan {
            return ViTriBan(parcel)
        }

        override fun newArray(size: Int): Array<ViTriBan?> {
            return arrayOfNulls(size)
        }
    }

}
