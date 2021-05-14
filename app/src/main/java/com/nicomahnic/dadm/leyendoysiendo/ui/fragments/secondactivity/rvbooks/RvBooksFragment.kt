package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.rvbooks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.database.appDatabase
import com.nicomahnic.dadm.leyendoysiendo.databinding.RvBooksFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.databinding.RvOrdersFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.entities.Order
import com.nicomahnic.dadm.leyendoysiendo.ui.adapter.BooksAdapter
import com.nicomahnic.dadm.leyendoysiendo.ui.adapter.OrdersAdapter
import com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.rvorders.RvOrdersFragmentDirections
import com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.rvorders.RvOrdersViewModel
import com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.tabcontainer.TabContainerViewModel

class RvBooksFragment : Fragment(R.layout.rv_books_fragment) {

    private lateinit var binding: RvBooksFragmentBinding
    private val viewModelTab: TabContainerViewModel by activityViewModels()
    private lateinit var v: View
    private lateinit var viewModel: RvBooksViewModel
    private lateinit var bookList: List<Book>
    private var db: appDatabase? = null

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var booksAdapter: BooksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RvBooksFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(RvBooksViewModel::class.java)

        v = view
    }

    override fun onStart() {
        super.onStart()

        binding.rvBooks.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        binding.rvBooks.layoutManager = linearLayoutManager

        viewModelTab.books.observe(viewLifecycleOwner, { result ->
            booksAdapter = BooksAdapter(result)
            binding.rvBooks.adapter = booksAdapter
        })
    }

}