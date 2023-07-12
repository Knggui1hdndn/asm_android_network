package com.example.lab3

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiClientPresenter(val view: ApiContract.View) : ApiContract.Presenter {
    override fun getPhotos() {
        ApiClient.apiInterface.getPhotos().enqueue(object : Callback<List<Photos>> {
            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {
                if (response.isSuccessful) view.onSuccess(response.body()!!) else view.onFailure(
                    response.errorBody().toString()
                )
            }

            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                view.onFailure(
                   t.message.toString()
                )
            }

        })
    }
}