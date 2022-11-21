package com.cpadridev.carmonaadrian_examen1ev

import android.os.Parcel
import android.os.Parcelable

class Computer(name: String, place: String, device: String, var year: Int, var processor: String) :
    Device(
        name, place, device
    ) {
    constructor(`in`: Parcel) : this(
        `in`.readString()!!,
        `in`.readString()!!,
        `in`.readString()!!,
        `in`.readInt()!!,
        `in`.readString()!!
    )

    init {
        this.code += processor + (year % 100)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel, flag: Int) {
        out.writeString(name)
        out.writeString(place)
        out.writeString(device)
        out.writeInt(year)
        out.writeString(processor)
    }

    companion object CREATOR : Parcelable.Creator<Computer> {
        override fun createFromParcel(`in`: Parcel): Computer {
            return Computer(`in`)
        }

        override fun newArray(size: Int): Array<Computer?> {
            return arrayOfNulls(size)
        }
    }
}