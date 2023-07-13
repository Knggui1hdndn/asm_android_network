package com.example.lab3

import android.os.AsyncTask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class HttpUrl(private val view: ApiContract.View) : AsyncTask<String, Void, List<Photos>>() {
    override fun onPreExecute() {
        // Các thao tác chuẩn bị trước khi thực hiện AsyncTask
    }

    override fun onPostExecute(result: List<Photos>?) {
        if (result != null) {
            view.onSuccess(result)
        } else {
            // Xử lý lỗi hoặc hiển thị thông báo không thành công
        }
    }

    override fun doInBackground(vararg params: String?): List<Photos> {
        val sJson = getResponse(params[0]!!)
        val gson = Gson()
        val photosListType = object : TypeToken<List<Photos>>() {}.type
        val photosList = gson.fromJson<List<Photos>>(sJson, photosListType)
        return photosList
    }

    private fun getResponse(url: String): String {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()

        val inputStream = connection.inputStream
        val reader = BufferedReader(InputStreamReader(inputStream))
        val response = StringBuilder()
        var line: String?

        try {
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
        } catch (e: Exception) {
            // Xử lý lỗi khi đọc dữ liệu
        } finally {
            reader.close()
            connection.disconnect()
        }

        return response.toString()
    }
}
