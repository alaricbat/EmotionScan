package com.advance.emotionscanapp.util.array

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color

object ArrayUtils {

    @SuppressLint("UseKtx")
    fun bitmapToFloatArray(bitmap: Bitmap): FloatArray {

        val width = bitmap.width
        val height = bitmap.height

        val floatArray = FloatArray(width * height)

        var index = 0

        for (y in 0 until height) {
            for (x in 0 until width) {

                val pixel = bitmap.getPixel(x, y)

                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)

                val gray = (r + g + b) / 3f

                floatArray[index] = gray / 255f

                index++

            }
        }

        return floatArray
    }

}