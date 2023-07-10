package com.example.lab1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

object LoadImg {
    fun loadImageFromInternet(link:String): Bitmap? {
        var url: URL? = null
        return try {
            url= URL(link)
            BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: Exception) {
            null
        }

    }
}