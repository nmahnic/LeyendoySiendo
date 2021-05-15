package com.nicomahnic.dadm.leyendoysiendo.repository

import com.nicomahnic.dadm.leyendoysiendo.data.BookDataSource
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.data.entities.OrderEntity

class BookRepositoryImpl(private val dataSource: BookDataSource): BookRepository {
    override fun getBooks(): List<Book>? = dataSource.getBooks()

    override suspend fun insertOrder(order: OrderEntity) {
        dataSource.insertOrderIntoRoom(order)
    }
}