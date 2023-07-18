package com.example.asm_network

import com.example.asm_network.model.Products
import com.example.asm_network.model.ProductsItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiClient {
    companion object {
        const val baseUrl = "http://192.168.1.181:3000/api/"
        val getInstant =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
    }

    @GET("products")
    fun getProducts(): Call<Products>

    @GET("product/{id}")
    fun getProduct(@Path("id") id:String): Call<ProductsItem>

    @PUT("products/{id}")
    fun updateProduct(@Path("id") productId: String, @Body product: ProductsItem): Call<ResponseBody>

    @POST("products")
    fun addProduct(@Body product: ProductsItem): Call<ResponseBody>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") productId: String): Call<ResponseBody>
}