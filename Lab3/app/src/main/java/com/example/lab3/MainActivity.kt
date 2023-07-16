package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab3.databinding.ActivityMainBinding
import com.example.lab3.model.Photos
import com.example.lab3.model.Profile

class MainActivity : AppCompatActivity(), ApiContract.View {
    val presenter = ApiClientPresenter(this)
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.getPhotos()
         // HttpUrl(this).execute("https://jsonplaceholder.typicode.com/photos")
    }

    override fun <T> onSuccess(rs: T) {
       if (rs is List<*>){
           setAdapter(rs as  List<Photos>)
           binding.a.visibility = View.GONE
       }
    }


    override fun onFailure(e: String) {
        binding.a.visibility = View.GONE

        Toast.makeText(this, e, Toast.LENGTH_SHORT).show()
    }

    fun setAdapter(list: List<Photos>) {
        val mng = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = Adapter(list)
        binding.rcy.layoutManager = mng
        binding.rcy.adapter = adapter
    }
}