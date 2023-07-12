package com.example.lab2


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
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
        private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .callTimeout(1500L, TimeUnit.MILLISECONDS)
            .readTimeout(1500L, TimeUnit.MILLISECONDS)
            .build()
        private var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        private var url = "http://192.168.1.181/android/"
        var apiInterface: ApiClient = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(url)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()
            .create(ApiClient::class.java)
    }

    @GET("lab2.1.php")
    fun getApiLab2_1(
        @Query("name") name: String,
        @Query("score") score: String
    ): Call<ResponseBody>

    @GET("lab2.2.php")
    fun getApiLab2_2(
        @Query("length") length: String,
        @Query("width") width: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("lab2.3.php")
    fun getApiLab2_3(
        @Field("canh") canh: String,
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("lab2.4.php")
    fun getApiLab2_4(
        @Field("a") a: String,
        @Field("b") b: String,
        @Field("c") c: String,
    ): Call<ResponseBody>
}