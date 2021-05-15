package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.rvorders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.repository.Repository
import kotlinx.coroutines.Dispatchers

class RvOrdersViewModel(private val repo: Repository) : ViewModel() {
    val fetchOrderList = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try{
            emit(Resource.Success(repo.getOrders()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}