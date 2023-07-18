package com.example.asm_network

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asm_network.databinding.ActivityMainBinding
import com.example.asm_network.model.Products

class MainActivity : AppCompatActivity(), ApiContracts.View {
    val presenter = ApiProductPresenter(this)
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private   var products: Products = Products()


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            binding.pg.visibility = View.VISIBLE
            presenter.getProducts()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.getProducts()
        adapter = Adapter(Products(), {id,i->
            binding.pg.visibility = View.VISIBLE
             presenter.deleteProduct(id) { b, e ->
                if (b) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    products.removeAt(i)
                    adapter.setData(products)
                } else {
                    Toast.makeText(this, e, Toast.LENGTH_SHORT).show()
                }
                binding.pg.visibility = View.INVISIBLE
            }
        },{
            val intent = Intent(this, UpdateActivity::class.java)

            intent.putExtra("id", it)
            startActivityForResult(intent,1)
        })
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, UpdateActivity::class.java)
            intent.putExtra("add", true)
            startActivityForResult(intent, 1)
        }
    }

    override fun <T> onSuccess(rs: T) {
        binding.pg.visibility = View.INVISIBLE
        if (rs is Products) {
            val mng = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rcy.layoutManager = mng
            binding.rcy.adapter = adapter
            products.clear()
            products.addAll(rs)
            adapter.setData(products)
        }
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
    }

    override fun onFailed(rs: String) {
        Toast.makeText(this, rs, Toast.LENGTH_SHORT).show()
        binding.pg.visibility = View.INVISIBLE

    }
}