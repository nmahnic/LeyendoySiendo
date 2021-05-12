package com.nicomahnic.dadm.leyendoysiendo.domain

import androidx.room.*
import com.nicomahnic.dadm.leyendoysiendo.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY id")
    fun loadAllPersons(): MutableList<UserEntity?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(user: UserEntity?)

    @Update
    fun updatePerson(user: UserEntity?)

    @Delete
    fun delete(user: UserEntity?)

    @Query("SELECT * FROM users WHERE id = :id")
     fun loadPersonById(id: Int): UserEntity?
}