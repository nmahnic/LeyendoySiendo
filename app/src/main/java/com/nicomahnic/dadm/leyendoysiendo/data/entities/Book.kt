package com.nicomahnic.dadm.leyendoysiendo.data.entities

data class Book(
    val title: String,
    val author: String,
    val year: Int,
    val editorial: String,
    val isbn: String,
    val description: String,
    val imgUrl: String
)

data class BoolList(val results: List<Book> = listOf())