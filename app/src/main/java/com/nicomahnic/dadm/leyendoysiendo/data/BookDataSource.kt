package com.nicomahnic.dadm.leyendoysiendo.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.utils.getJsonDataFromAsset

class BookDataSource(private val context: Context) {
    fun getBooks(): List<Book>? {
        val jsonFileString = getJsonDataFromAsset(context, "libros.json")
        jsonFileString?.let {
//            Log.d("NM", "BookDataSource: $jsonFileString")
            val gson = Gson()
            val listPersonType = object : TypeToken<List<Book>>() {}.type

            return gson.fromJson(jsonFileString, listPersonType)
        }
        return null
    }
}