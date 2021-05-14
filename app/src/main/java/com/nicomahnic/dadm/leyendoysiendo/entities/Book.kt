package com.nicomahnic.dadm.leyendoysiendo.entities

data class Book(
    val title: String,
    val author: String,
    val year: Int,
    val editorial: String,
    val isbn: String,
    val description: String,
    val imgUrl: String
)