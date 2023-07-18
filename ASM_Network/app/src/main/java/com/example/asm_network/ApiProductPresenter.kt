package com.example.asm_network

import android.util.Log
import com.example.asm_network.model.Products
import com.example.asm_network.model.ProductsItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiProductPresenter(val view: ApiContracts.View) : ApiContracts.Presenter {
    override fun getProducts() {
        ApiClient.getInstant.getProducts().enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful) view.onSuccess<Products>(response.body() as Products) else view.onFailed(
                    "Error"
                )
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                view.onFailed(t.message.toString())
            }

        })
    }

    override fun getProduct(id:String) {
        ApiClient.getInstant.getProduct(id).enqueue(object : Callback<ProductsItem> {
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {
                if (response.isSuccessful) view.onSuccess<ProductsItem>(response.body() as ProductsItem) else view.onFailed("Error")
             }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {
                view.onFailed(t.message.toString())

            }

        })
    }

    override fun addProduct(product: ProductsItem) {
        ApiClient.getInstant.addProduct(product).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) view.onSuccess<Boolean>(true) else view.onFailed("Error")

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                view.onFailed(t.message.toString())

            }

        })
    }

    override fun deleteProduct(id: String,callback: (Boolean,String)->Unit) {
        ApiClient.getInstant.deleteProduct(id).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback(true,"") else callback(false,response.errorBody()!!.string())

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback(false,t.message.toString())

            }

        })
    }

    override fun updateProduct(id: String, product: ProductsItem) {
        ApiClient.getInstant.updateProduct(id, product).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) view.onSuccess(true) else view.onFailed("Error")

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                view.onFailed(t.message.toString())
             }

        })
    }
}