package com.example.lab1

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.lab1.databinding.ActivityLesson2Binding

class lesson2 : AppCompatActivity() {
    private lateinit var binding: ActivityLesson2Binding
    private lateinit var progress: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
    btnLoad.setOnClickListener {  }
            progress= ProgressDialog.show(this@lesson2,"Load","Loading...")
            Thread{
                val bitmap=LoadImg.loadImageFromInternet("https://synnexfpt.com/wp-content/uploads/2022/07/FPT-Services-Banner-2.jpg")

            }
        }
    }
    private val  handler:Handler= Handler(Looper.getMainLooper()) {

        true
    }
}