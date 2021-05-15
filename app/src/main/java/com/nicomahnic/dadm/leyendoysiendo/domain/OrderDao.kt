package com.nicomahnic.dadm.leyendoysiendo.domain

import androidx.room.*
import com.nicomahnic.dadm.leyendoysiendo.data.entities.OrderEntity
import com.nicomahnic.dadm.leyendoysiendo.data.entities.UserEntity

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders ORDER BY id")
    fun getOrders(): List<OrderEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: OrderEntity?)

    @Update
    fun updateOrder(order: OrderEntity?)

    @Delete
    fun delete(order: OrderEntity?)

    @Query("SELECT * FROM orders WHERE id = :id")
     fun loadOrderById(id: Int): OrderEntity?
}