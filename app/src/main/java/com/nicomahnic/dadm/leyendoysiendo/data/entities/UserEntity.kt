package com.nicomahnic.dadm.leyendoysiendo.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "device_name")
    val name: String,

    @ColumnInfo(name = "device_description")
    val password: String,

    var checked: Boolean = false
)