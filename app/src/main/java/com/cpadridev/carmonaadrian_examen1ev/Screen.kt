package com.cpadridev.carmonaadrian_examen1ev

import android.os.Parcel
import android.os.Parcelable

class Screen(name: String, place: String, device: String, var inches: Int) :
    Device(
        name, place, device
    ) {
    constructor(`in`: Parcel) : this(
        `in`.readString()!!,
        `in`.readString()!!,
        `in`.readString()!!,
        `in`.readInt()!!
    )

    init {
        code += inches.toString() + name.substring(name.length - 3)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel, flag: Int) {
        out.writeString(name)
        out.writeString(place)
        out.writeString(device)
        out.writeInt(inches)
    }

    companion object CREATOR : Parcelable.Creator<Screen> {
        override fun createFromParcel(`in`: Parcel): Screen {
            return Screen(`in`)
        }

        override fun newArray(size: Int): Array<Screen?> {
            return arrayOfNulls(size)
        }
    }
}