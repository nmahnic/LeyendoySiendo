package com.nicomahnic.dadm.leyendoysiendo.repository

import com.nicomahnic.dadm.leyendoysiendo.data.BookDataSource
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book

class BookRepositoryImpl(private val dataSource: BookDataSource): BookRepository {
    override fun getBooks(): List<Book>? = dataSource.getBooks()
}