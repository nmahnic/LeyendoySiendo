package com.nicomahnic.dadm.leyendoysiendo.repository

import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.data.entities.OrderEntity

interface Repository {
    fun getBooks(): List<Book>?

    suspend fun insertOrder(order: OrderEntity)
    suspend fun getOrders(): List<OrderEntity>?
}