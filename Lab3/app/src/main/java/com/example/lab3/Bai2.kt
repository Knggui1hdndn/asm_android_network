package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab3.databinding.ActivityBai2Binding
import com.example.lab3.model.Photos
import com.example.lab3.model.Profile
import com.example.lab3.model.ProfileItem

class Bai2 : AppCompatActivity(), ApiContract.View {
    private var _binding: ActivityBai2Binding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBai2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = ApiClientPresenter(this)
        binding.a.visibility = View.GONE
        binding.btn1.setOnClickListener {
            binding.a.visibility = View.VISIBLE
            presenter.getObjProfile()
        }
        binding.btn2.setOnClickListener {
            binding.a.visibility = View.VISIBLE
            presenter.getListProfile()

        }
    }

    override fun <T> onSuccess(rs: T) {
        if (rs is Profile) {
            Log.d("Ddddddddddd",rs.toString())
            setAdapter(rs)
        } else {
            Log.d("Ddddddddddd",rs.toString())

            val list = Profile()
            list.add(rs as ProfileItem)
            setAdapter(list)
        }
    }

    override fun onFailure(e: String) {
        binding.a.visibility = View.GONE
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show()
    }


    fun setAdapter(list: Profile) {
        binding.a.visibility = View.GONE

        val mng = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = Adapter_bai_2(list)
        binding.rcy.layoutManager = mng
        binding.rcy.adapter = adapter
    }
}