package com.nicomahnic.dadm.leyendoysiendo.viewmodel

import androidx.lifecycle.*
import com.google.gson.Gson
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.data.entities.OrderEntity
import com.nicomahnic.dadm.leyendoysiendo.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterOrderViewModel(private val repo: Repository) : ViewModel() {
    val clientName = MutableLiveData<String>()

    fun fetchBooks() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try{
            emit(Resource.Success(repo.getBooks()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun insertOrder(clientName: String, bookList: List<Book>){
        viewModelScope.launch {
            repo.insertOrder(
                OrderEntity(name = clientName, books = Gson().toJson(bookList))
            )
        }
    }

}