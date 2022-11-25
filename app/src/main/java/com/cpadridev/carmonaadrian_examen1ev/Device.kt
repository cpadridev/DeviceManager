package com.cpadridev.carmonaadrian_examen1ev

import android.os.Parcelable

abstract class Device(var name: String, var place: String, var device: String) : Parcelable {
    init {
        when (name.length) {
            1 -> {
                name += "00"
            }
            2 -> {
                name += "0"
            }
        }
    }

    var code: String =
        if (name.length >= 3) {
            name.substring(0, 3)
        } else {
            name
        }

    override fun toString(): String {
        return "$name - $place - $code"
    }
}