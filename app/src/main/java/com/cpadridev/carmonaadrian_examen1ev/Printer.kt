package com.cpadridev.carmonaadrian_examen1ev

import android.os.Parcel
import android.os.Parcelable

class Printer(name: String, place: String, device: String, var photocopies: Boolean) :
    Device(
        name, place, device
    ) {
    constructor(`in`: Parcel) : this(
        `in`.readString()!!,
        `in`.readString()!!,
        `in`.readString()!!,
        `in`.readInt() != 0
    )

    init {
        code += "IMP" +
                if (place.indexOf(" ") == -1) {
                    place.reversed()
                } else {
                    place.substring(0, place.indexOf(" ")).reversed()
                }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel, flag: Int) {
        out.writeString(name)
        out.writeString(place)
        out.writeString(device)
        out.writeInt(if (photocopies) 1 else 0)
    }

    companion object CREATOR : Parcelable.Creator<Printer> {
        override fun createFromParcel(`in`: Parcel): Printer {
            return Printer(`in`)
        }

        override fun newArray(size: Int): Array<Printer?> {
            return arrayOfNulls(size)
        }
    }
}