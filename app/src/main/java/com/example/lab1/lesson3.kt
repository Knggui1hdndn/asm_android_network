package com.example.lab1

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab1.databinding.ActivityLesson2Binding
import com.example.lab1.databinding.ActivityLesson3Binding

class lesson3 : AppCompatActivity(),Listen {
    private lateinit var binding: ActivityLesson3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLesson3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLoad.setOnClickListener {
            val load=LoadImageTask(this, this).execute("https://synnexfpt.com/wp-content/uploads/2022/07/FPT-Services-Banner-2.jpg")

        }
    }

    override fun onLoader(bitmap: Bitmap) {
         binding.img.setImageBitmap(bitmap)
    }

    override fun onError() {

    }
}