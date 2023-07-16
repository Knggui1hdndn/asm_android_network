package com.example.lab3

import com.example.lab3.model.Profile
import retrofit2.Call
import retrofit2.http.GET

interface ApiContract {
    interface View {
        fun <T> onSuccess(rs: T)
         fun onFailure(e: String);
    }

    interface Presenter {
        fun getPhotos()
        fun getListProfile()
         fun getObjProfile()
    }
}