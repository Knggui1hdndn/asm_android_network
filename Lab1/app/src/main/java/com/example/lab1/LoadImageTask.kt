package com.example.lab1

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask

interface Listen {
    fun onLoader(bitmap: Bitmap)
    fun onError()
}

class LoadImageTask(val listen: Listen, val context: Context ) : AsyncTask<String, Void, Bitmap?>() {
    private lateinit var progress: ProgressDialog
    override fun onPreExecute() {
        super.onPreExecute()
        progress = ProgressDialog.show(context, "Load", "Loading...")

    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        progress.dismiss()
        if (result != null) listen.onLoader(result!!) else listen.onError()
    }

    override fun doInBackground(vararg p0: String?): Bitmap {
        return LoadImg.loadImageFromInternet(p0[0]!!)!!

    }
}