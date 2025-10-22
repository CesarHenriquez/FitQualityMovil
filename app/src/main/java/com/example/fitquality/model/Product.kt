package com.example.fitquality.model

/**
* Entidad pura del Dominio: describe un producto de la tienda.
* Usamos el 'imageResourceName' como clave para buscar el drawable.
*/
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    //  REFERENCIA SIMPLE: el nombre del archivo en res/drawable, ej: "muñequera_pro"
    val imageResourceName: String
)