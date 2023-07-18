package com.example.asm_network

import com.example.asm_network.model.ProductsItem

interface ApiContracts {
    interface View{
        fun <T> onSuccess(rs :T)
        fun onFailed(rs:String)
    }
    interface Presenter{
        fun getProducts()
        fun getProduct(id:String)
        fun addProduct(product:ProductsItem)
        fun deleteProduct(id:String,callback: (Boolean,String)->Unit)
        fun updateProduct(id:String,product:ProductsItem)
    }
}