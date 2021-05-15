package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.drawer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.data.BookDataSource
import com.nicomahnic.dadm.leyendoysiendo.data.database.AppDatabase
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.databinding.RegisterOrderFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.repository.BookRepositoryImpl
import com.nicomahnic.dadm.leyendoysiendo.ui.adapter.OrderBooksAdapter

class RegisterOrderFragment :
    Fragment(R.layout.register_order_fragment),
    OrderBooksAdapter.OnBookClickListener
{

    private lateinit var binding: RegisterOrderFragmentBinding
    private lateinit var v: View
    private val viewModel: RegisterOrderViewModel by activityViewModels() {
        BookViewModelFactory(
            BookRepositoryImpl(
                BookDataSource(
                    requireContext(),
                    AppDatabase.getAppDataBase(requireActivity().applicationContext)
                )
            )
        )
    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var orderBooksAdapter: OrderBooksAdapter

    private var bookList = mutableListOf<Book>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RegisterOrderFragmentBinding.bind(view)

        v = view
    }

    override fun onStart() {
        super.onStart()
        setupRecycleView()
        setupObservers()

        binding.btnEnter.setOnClickListener {
            val clientName = binding.edtUser.text.toString()
            if(bookList.isNotEmpty() && clientName.isNotBlank()){
                viewModel.insertOrder(clientName, bookList)
                Toast.makeText(requireContext(), "Orden Cargada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObservers() {
        viewModel.fetchBooks().observe(viewLifecycleOwner, { result ->
            //Log.d("NM", "fetch $result")
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    orderBooksAdapter = OrderBooksAdapter(
                        requireContext(),
                        result.data!!,
                        this
                    )
                    binding.rvOrderBooks.adapter = orderBooksAdapter
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })
    }

    private fun setupRecycleView() {
        binding.rvOrderBooks.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        binding.rvOrderBooks.layoutManager = linearLayoutManager
    }

    override fun onBookClick(position: Int, book: Book) {
        bookList.add(book)
        Toast.makeText(requireContext(), "Libro agregado", Toast.LENGTH_SHORT).show()
    }

}