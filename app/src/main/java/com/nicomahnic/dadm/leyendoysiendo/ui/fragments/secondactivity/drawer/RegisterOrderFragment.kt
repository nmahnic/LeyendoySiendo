package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.drawer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.data.BookDataSource
import com.nicomahnic.dadm.leyendoysiendo.databinding.RegisterOrderFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.repository.BookRepositoryImpl
import com.nicomahnic.dadm.leyendoysiendo.ui.adapter.OrderBooksAdapter

class RegisterOrderFragment : Fragment(R.layout.register_order_fragment) {

    private lateinit var binding: RegisterOrderFragmentBinding
    private lateinit var v: View
    private val viewModel: RegisterOrderViewModel by activityViewModels() {
        BookViewModelFactory(
            BookRepositoryImpl(
                BookDataSource(requireContext())
            )
        )
    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var orderBooksAdapter: OrderBooksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RegisterOrderFragmentBinding.bind(view)

        v = view
    }

    override fun onStart() {
        super.onStart()

        binding.rvOrderBooks.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        binding.rvOrderBooks.layoutManager = linearLayoutManager

        viewModel.fetchBooks().observe(viewLifecycleOwner, { result ->
            Log.d("NM","fetch $result")
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    orderBooksAdapter = OrderBooksAdapter(requireContext(),result.data!!){ pos ->
                        Log.d("NM", pos.toString())
                    }
                    binding.rvOrderBooks.adapter = orderBooksAdapter
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })
    }

}