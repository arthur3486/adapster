package com.arthurivanets.sample.util

import android.content.Context
import androidx.annotation.DimenRes


fun Context.getDimension(@DimenRes dimensionResourceId : Int) : Float {
    return resources.getDimension(dimensionResourceId)
}


fun Context.getDimensionPixelSize(@DimenRes dimensionResourceId : Int) : Int {
    return resources.getDimensionPixelSize(dimensionResourceId)
}