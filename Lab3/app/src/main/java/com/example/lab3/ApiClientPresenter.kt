package com.example.lab3

import android.util.Log
import com.example.lab3.model.Photos
import com.example.lab3.model.Profile
import com.example.lab3.model.ProfileItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiClientPresenter(val view: ApiContract.View) : ApiContract.Presenter {
    override fun getPhotos() {
         ApiClient2.apiInterface.getPhotos().enqueue(object : Callback<List<Photos>> {
            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {
                if (response.isSuccessful) view.onSuccess<List<Photos>>(response.body() as List<Photos>) else view.onFailure(
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

    override fun getListProfile() {
         ApiClient.apiInterface.getListProfile().enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if (response.isSuccessful) view.onSuccess(response.body() ) else view.onFailure(
                    response.errorBody().toString()
                )
             }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                view.onFailure(
                    t.message.toString()
                )
                Log.d("sdddddđ2",t.message.toString())
            }

        })
    }

    override fun getObjProfile() {
         ApiClient.apiInterface.getObjProfile().enqueue(object : Callback<ProfileItem> {
            override fun onResponse(call: Call<ProfileItem>, response: Response<ProfileItem>) {
                if (response.isSuccessful) view.onSuccess<ProfileItem>(response.body()!!) else view.onFailure(
                    response.errorBody()!!.string()
                )

            }

            override fun onFailure(call: Call<ProfileItem>, t: Throwable) {
                view.onFailure(
                    t.message.toString()
                )
            }

        })
     }
}