package com.example.lab3


import com.example.lab3.model.Photos
import com.example.lab3.model.Profile
import com.example.lab3.model.ProfileItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiClient {
    companion object {
        private var gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        var apiInterface: ApiClient = Retrofit.Builder()
            .baseUrl("http://192.168.1.181/android/")
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()
            .create(ApiClient::class.java)
    }


    @GET("person_array.json")
    fun getListProfile(): Call<Profile>

    @GET("person_object.json")
    fun getObjProfile(): Call<ProfileItem>
}

interface ApiClient2 {
    companion object {
        private var gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        var apiInterface: ApiClient2 = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()
            .create(ApiClient2::class.java)
    }

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>


}