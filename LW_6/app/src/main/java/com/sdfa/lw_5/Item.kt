package com.sdfa.lw_5

import android.os.Parcel
import android.os.Parcelable

class Item () : Parcelable {
    var kind: String = ""
    var title: String = ""
    var photo: String = ""
    var price: Double = 0.0
    var weight: Double = 0.0
    var id: Int = 0

    val info: String
        get() = "$kind $title $weight ($price RUB)"

    constructor(parcel: Parcel) : this() {
        kind = parcel.readString() ?: ""
        title = parcel.readString() ?: ""
        photo = parcel.readString() ?: ""
        price = parcel.readDouble()
        weight = parcel.readDouble()
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(kind)
        parcel.writeString(title)
        parcel.writeString(photo)
        parcel.writeDouble(price)
        parcel.writeDouble(weight)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}