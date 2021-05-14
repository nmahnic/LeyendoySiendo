
package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.tabcontainer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.repository.BookRepository
import kotlinx.coroutines.Dispatchers

class TabContainerViewModel(private val repo: BookRepository) : ViewModel() {
    val clientName = MutableLiveData<String>()
    val orderNum = MutableLiveData<Long>()
    val books = MutableLiveData<MutableList<Book>>()

    fun fetchBooks() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try{
            emit(Resource.Success(repo.getBooks()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun loadBooks(bookList: List<Book>?){
        bookList?.forEach {
            books.value?.add(it)
        }
    }

    fun loadOrder(order: Long, name: String){
        orderNum.value = order
        clientName.value = name
    }
}

class BookViewModelFactory(private val repo: BookRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(BookRepository::class.java).newInstance(repo)
    }
}