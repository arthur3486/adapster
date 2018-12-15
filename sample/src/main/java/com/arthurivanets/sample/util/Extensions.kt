package com.arthurivanets.sample.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.DimenRes


fun Context.getDimension(@DimenRes dimensionResourceId : Int) : Float {
    return resources.getDimension(dimensionResourceId)
}


fun Context.getDimensionPixelSize(@DimenRes dimensionResourceId : Int) : Int {
    return resources.getDimensionPixelSize(dimensionResourceId)
}


fun Context.toast(message : String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    ).show()
}