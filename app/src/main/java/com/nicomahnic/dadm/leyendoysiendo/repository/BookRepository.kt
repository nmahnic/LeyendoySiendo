package com.nicomahnic.dadm.leyendoysiendo.repository

import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book

interface BookRepository {
    fun getBooks(): List<Book>?
}