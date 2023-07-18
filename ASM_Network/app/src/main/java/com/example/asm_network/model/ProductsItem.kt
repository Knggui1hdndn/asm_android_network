package com.example.asm_network.model

import java.io.Serializable

data class ProductsItem(
    val _id: String?,
    val company: Company,
    val image: String,
    val import_date: String,
    val name: String,
    val price: Double,
    val quantity: Int
):Serializable{
    constructor(
        company: Company,
        image: String,
        import_date: String,
        name: String,
        price: Double,
        quantity: Int
    ) : this(null, company, image, import_date, name, price, quantity)
    override fun toString(): String {
return "id: $_id\n" +
"name: $name\n" +
"quantity: $quantity\n" +
"import_date: $import_date\n" +
"price: $price\n" +
 "company: {name: ${company.name}\naddress: ${company.address}\ncontact: ${company.contact}\nphone: ${company.phone}}"
    }
}