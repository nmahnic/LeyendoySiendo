package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.rvorders

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.database.appDatabase
import com.nicomahnic.dadm.leyendoysiendo.databinding.RvOrdersFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.entities.Users
import com.nicomahnic.dadm.leyendoysiendo.ui.activities.SecondActivity
import com.nicomahnic.dadm.leyendoysiendo.utils.getJsonDataFromAsset

class RvOrdersFragment : Fragment(R.layout.rv_orders_fragment) {

    private lateinit var binding: RvOrdersFragmentBinding
    private lateinit var v: View
    private lateinit var viewModel: RvOrdersViewModel
    private lateinit var userList: List<Users>
    private var db: appDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.rv_orders_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
                // TODO: Use the ViewModel
    }
    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RvOrdersFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(RvOrdersViewModel::class.java)

        v = view

        Log.d("NM", "Singleton ${SecondActivity.User.name}")

        val jsonFileString = getJsonDataFromAsset(requireContext(),"libros.json")
        jsonFileString?.let{
            Log.d("NM", jsonFileString)
            val gson = Gson()
            val listPersonType = object : TypeToken<List<Users>>() {}.type

            userList = gson.fromJson(jsonFileString, listPersonType)
            userList.forEach {
                Log.d("NM", it.title) //por alguna razon no lo imprime
            }
        }
    }

}