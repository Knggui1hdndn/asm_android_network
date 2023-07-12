package com.example.lab3

interface ApiContract {
    interface View {
        fun onSuccess(rs: List<Photos>);
        fun onFailure(e: String);
    }

    interface Presenter {
        fun getPhotos()
    }
}