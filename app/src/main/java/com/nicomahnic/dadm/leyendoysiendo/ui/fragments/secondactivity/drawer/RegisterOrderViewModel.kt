package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.drawer

import androidx.lifecycle.*
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.data.entities.OrderEntity
import com.nicomahnic.dadm.leyendoysiendo.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterOrderViewModel(private val repo: BookRepository) : ViewModel() {
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
                OrderEntity(name = clientName)
            )
        }
    }

}

class BookViewModelFactory(private val repo: BookRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(BookRepository::class.java).newInstance(repo)
    }
}