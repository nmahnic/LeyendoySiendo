package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.tabcontainer.rvbooks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.databinding.RvBooksFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.ui.adapter.BooksAdapter
import com.nicomahnic.dadm.leyendoysiendo.viewmodel.TabContainerViewModel

class RvBooksFragment : Fragment(R.layout.rv_books_fragment) {

    private lateinit var binding: RvBooksFragmentBinding
    private val viewModelTab: TabContainerViewModel by activityViewModels()
    private lateinit var v: View

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var booksAdapter: BooksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RvBooksFragmentBinding.bind(view)
        v = view
    }

    override fun onStart() {
        super.onStart()

        binding.rvBooks.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        binding.rvBooks.layoutManager = linearLayoutManager

        viewModelTab.fetchOrder().observe(viewLifecycleOwner, { result ->
            Log.d("NM", "fetchOrder $result")
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    val bookList = Gson().fromJson(result.data!!.books, Array<Book>::class.java).toList()
                    booksAdapter = BooksAdapter(requireContext(), bookList)
                    binding.rvBooks.adapter = booksAdapter
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })
    }

}