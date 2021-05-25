
package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.tabcontainer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.repository.Repository
import kotlinx.coroutines.Dispatchers

class TabContainerViewModel(private val repo: Repository) : ViewModel() {
    val orderNum = MutableLiveData<Long>()

    fun fetchBooks() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try{
            emit(Resource.Success(repo.getBooks()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun fetchOrder() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getOrdersByOrderNum(orderNum.value!!)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun updateOrder(newName: String) {
        //TODO(update order)
    }

    fun loadOrder(order: Long) {
        orderNum.value = order
    }

}