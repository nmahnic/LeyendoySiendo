package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicomahnic.dadm.leyendoysiendo.repository.Repository


class ViewModelFactory(private val repo: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(repo)
    }
}