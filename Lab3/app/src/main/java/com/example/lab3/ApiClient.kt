package com.example.lab3


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiClient {
    companion object {

        private var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        private var url = "https://jsonplaceholder.typicode.com/"
        var apiInterface: ApiClient = Retrofit.Builder()

            .baseUrl(url)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()
            .create(ApiClient::class.java)
    }

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>
}