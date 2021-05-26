package com.nicomahnic.dadm.leyendoysiendo.repository

import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.data.entities.OrderEntity

interface Repository {
    fun getBooks(): List<Book>?

    suspend fun insertOrder(order: OrderEntity)
    suspend fun getOrders(): List<OrderEntity>?
    suspend fun getOrdersByOrderNum(orderNum: Long): OrderEntity?
    suspend fun updateOrder(order: OrderEntity)
    suspend fun deleteOrder(order: OrderEntity)
}