package com.example.lab1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab1.databinding.ActivityMainBinding
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnLoad.setOnClickListener {
                Thread {
                    val bitmap = LoadImg.loadImageFromInternet("https://techcare.vn/wp-content/uploads/2019/12/cach-tai-google-dich-ve-may-tinh-6.jpg")
                    img.post {
                        img.setImageBitmap(bitmap)
                    }
                }.start()
            }
        }
    }


}