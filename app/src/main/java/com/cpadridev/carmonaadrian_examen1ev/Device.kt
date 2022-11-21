package com.cpadridev.carmonaadrian_examen1ev

import android.os.Parcelable

abstract class Device(var name: String, var place: String, var device: String) : Parcelable {
    var code: String =
        when (name.length) {
            1 -> {
                name + "00"
            }
            2 -> {
                name + "0"
            }
            else -> {
                name.substring(0, 3)
            }
        }

    override fun toString(): String {
        return "$name - $place - $code"
    }
}