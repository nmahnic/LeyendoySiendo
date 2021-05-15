
package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.tabcontainer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.repository.Repository
import kotlinx.coroutines.Dispatchers

class TabContainerViewModel(private val repo: Repository) : ViewModel() {
    val clientName = MutableLiveData<String>()
    val orderNum = MutableLiveData<Long>()

    fun fetchBooks() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try{
            emit(Resource.Success(repo.getBooks()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun loadOrder(order: Long, name: String){
        orderNum.value = order
        clientName.value = name
    }
}