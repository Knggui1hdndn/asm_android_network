package com.example.asm_network

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.asm_network.databinding.ActivityUpdateBinding
import com.example.asm_network.model.Company
import com.example.asm_network.model.ProductsItem
import com.example.asm_network.utils.ConvertImg
import java.util.Locale

class UpdateActivity : AppCompatActivity(), ApiContracts.View {
    private lateinit var binding: ActivityUpdateBinding
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            binding.imgProduct.setImageURI(uri)
        } else {
            Toast.makeText(this, "none", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgProduct.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        var id: String? = intent.getStringExtra("id")
        val add = intent.getBooleanExtra("add", false)
        if (add) {
            binding.btnUpDate.text="ADD"
            binding.pg.visibility= View.INVISIBLE
        }
         if (!add) ApiProductPresenter(this).getProduct(id!!)
        binding.btnUpDate.setOnClickListener {
            with(binding) {
                if (check(
                        txtName,
                        txtQuantity,
                        txtImportDate,
                        txtPrice,
                        txtCompanyName,
                        txtCompanyAddress,
                        txtCompanyContact,
                        txtCompanyPhone
                    )
                ) {
                    binding.pg.visibility=View.VISIBLE

                    val productsItem =   ProductsItem(null,
                        Company(
                            txtCompanyAddress.text.toString(),
                            txtCompanyContact.text.toString(),
                            txtCompanyName.text.toString(),
                            txtCompanyPhone.text.toString()
                        ),
                        ConvertImg.convertBitmapToString(imgProduct),
                        txtImportDate.text.toString(),
                        txtName.text.toString(),
                        txtPrice.text.toString().toDouble(),
                        txtQuantity.text.toString().toInt()
                    )
                    if (add) ApiProductPresenter(this@UpdateActivity).addProduct(productsItem) else ApiProductPresenter(
                        this@UpdateActivity
                    ).updateProduct(
                        id.toString(), productsItem
                    )
                }
            }
        }

    }

    private fun setData(productsItem: ProductsItem?) {
        with(binding) {
            if (productsItem != null) {
                txtId.setText(productsItem._id.toString())
                txtNames.text = productsItem.name
                txtName.setText(productsItem.name)
                txtQuantity.setText(productsItem.quantity.toString())
                txtImportDate.setText(productsItem.import_date)
                txtPrice.setText(productsItem.price.toString())
                txtCompanyName.setText(productsItem.company.name)
                txtCompanyAddress.setText(productsItem.company.address)
                txtCompanyContact.setText(productsItem.company.contact)
                txtCompanyPhone.setText(productsItem.company.phone)
                imgProduct.setImageBitmap(ConvertImg.convertStringToBitmap(productsItem.image))

            }
        }
    }

    private fun check(vararg edt: EditText): Boolean {
        var i = true
        edt.forEach {
            if (it.text.toString().isNotEmpty()) {
                it.error = null

            } else {
                it.error = "Không bỏ trống " + it.hint.toString().toLowerCase(Locale.ROOT)
                i = false
            }
        }
        return i
    }

    override fun <T> onSuccess(rs: T) {
        binding.pg.visibility= View.INVISIBLE

        if (rs is ProductsItem) {
            setData(rs as ProductsItem)
        } else {
            setResult(RESULT_OK, intent)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    override fun onFailed(rs: String) {
        binding.pg.visibility=View.INVISIBLE

        Toast.makeText(this, rs, Toast.LENGTH_SHORT).show()
    }
}