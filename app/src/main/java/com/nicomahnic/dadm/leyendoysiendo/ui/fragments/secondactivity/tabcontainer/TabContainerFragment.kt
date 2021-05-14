package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.tabcontainer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.databinding.TabContainerFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.ui.adapter.TabsViewPagerAdapter
import com.nicomahnic.dadm.leyendoysiendo.utils.getJsonDataFromAsset
import kotlinx.android.synthetic.main.tab_container_fragment.*

class TabContainerFragment : Fragment(R.layout.tab_container_fragment) {

    private lateinit var binding:TabContainerFragmentBinding
    private lateinit var v: View
    private val viewModelTab: TabContainerViewModel by activityViewModels()
    private val args: TabContainerFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TabContainerFragmentBinding.bind(view)

        v = view
        viewPager.adapter = TabsViewPagerAdapter(requireActivity())

        Log.d("NM", "in TabContainer")

        viewModelTab.loadOrder(args.order)
        viewModelTab.loadBooks(loadBookList())


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Order"
                1 -> tab.text = "Books"
                else -> tab.text = "undefined"
            }
        }.attach()
    }

    private fun loadBookList(): List<Book>? {
        val jsonFileString = getJsonDataFromAsset(requireContext(),"libros.json")
        jsonFileString?.let{
            Log.d("NM", jsonFileString)
            val gson = Gson()
            val listPersonType = object : TypeToken<List<Book>>() {}.type

            return gson.fromJson(jsonFileString, listPersonType)
        }
        return null
    }
}