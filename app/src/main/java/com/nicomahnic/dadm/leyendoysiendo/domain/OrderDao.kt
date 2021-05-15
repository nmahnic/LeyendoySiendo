package com.nicomahnic.dadm.leyendoysiendo.domain

import androidx.room.*
import com.nicomahnic.dadm.leyendoysiendo.data.entities.OrderEntity
import com.nicomahnic.dadm.leyendoysiendo.data.entities.UserEntity

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders ORDER BY id")
    fun loadAllOrders(): MutableList<OrderEntity?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(user: OrderEntity?)

    @Update
    fun updateOrder(user: OrderEntity?)

    @Delete
    fun delete(user: OrderEntity?)

    @Query("SELECT * FROM orders WHERE id = :id")
     fun loadOrderById(id: Int): OrderEntity?
}