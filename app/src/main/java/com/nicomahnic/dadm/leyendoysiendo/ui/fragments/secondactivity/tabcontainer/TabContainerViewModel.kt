
package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.tabcontainer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nicomahnic.dadm.leyendoysiendo.entities.Book

class TabContainerViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val books = MutableLiveData<MutableList<Book>>()

    fun loadBooks(bookList: List<Book>?){
        bookList?.forEach {
            books.value?.add(it)
        }
    }

    fun loadOrder(order: String){
        name.value = order
    }
}