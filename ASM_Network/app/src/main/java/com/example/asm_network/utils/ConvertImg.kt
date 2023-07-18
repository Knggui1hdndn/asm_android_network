package com.example.asm_network.utils

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import java.io.ByteArrayOutputStream


object ConvertImg {
    fun convertBitmapToString(img: ImageView): String {
        val bitmap = (img.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun convertStringToBitmap(img: String): Bitmap? {
        return try {
            val encodeByte: ByteArray = Base64.decode(img, Base64.DEFAULT)

            BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
        } catch (e: Exception) {

            null
        }
    }
}