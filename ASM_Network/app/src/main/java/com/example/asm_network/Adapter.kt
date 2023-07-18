package com.example.asm_network

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asm_network.databinding.ItemProductsBinding
import com.example.asm_network.model.Products
import com.example.asm_network.model.ProductsItem
import com.example.asm_network.utils.ConvertImg

class Adapter(var products: Products, val clickItem: (String,Int) -> Unit,val clickUpdate:(Intent)->Unit) :
    RecyclerView.Adapter<Adapter.AdapterViewHolder>() {

    inner class AdapterViewHolder(val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductsItem) {
            with(binding) {
                txtAddress.text = product.company.address
                txtNumber.text = product.company.phone
                txtDate.text = product.import_date
                txtName.text = product.name
                txtPrice.text = product.price.toString()
                imgProduct.setImageBitmap(ConvertImg.convertStringToBitmap(product.image))
                mRelate.setOnLongClickListener(object : OnLongClickListener {
                    override fun onLongClick(v: View?): Boolean {
                        showDialog(v!!.context, product._id.toString())
                        return true
                    }

                })
                imgShow.setOnClickListener { showDialog(it.context, product) }
                imgEdit.setOnClickListener {

                    val intent = Intent(it.context, UpdateActivity::class.java)
                    intent.putExtra("id", product._id)
                    clickUpdate(intent)
                }

            }
        }

        private fun showDialog(context: Context?, id: String) {
            val dialog =
                AlertDialog.Builder(context).setMessage("Xác nhận xóa").setTitle("Thông báo!")
                    .setPositiveButton("Delete") { dialogInterface: DialogInterface, i: Int ->
                       clickItem(products.toString(),adapterPosition)

                    }
                    .setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.cancel()
                    }

                    .show()
        }

        private fun showDialog(context: Context, product: ProductsItem) {
            val dialog =
                AlertDialog.Builder(context).setMessage(product.toString()).setTitle(product.name)
                    .setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.cancel()
                    }
                    .setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.cancel()

                    }

                    .setTitle(product.name).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            ItemProductsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setData(product: Products) {
        products.clear()
        products = product
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(products[position])
    }
}