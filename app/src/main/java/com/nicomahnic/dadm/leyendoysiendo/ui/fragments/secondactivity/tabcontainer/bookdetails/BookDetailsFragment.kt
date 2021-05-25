package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.tabcontainer.bookdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.databinding.BookDetailsFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.ui.adapter.BooksAdapter
import com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.tabcontainer.TabContainerViewModel

class BookDetailsFragment : Fragment(R.layout.book_details_fragment) {

    private lateinit var viewModel: BookDetailsViewModel
    private val viewModelTab: TabContainerViewModel by activityViewModels()
    private lateinit var binding: BookDetailsFragmentBinding
    private lateinit var v: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BookDetailsFragmentBinding.bind(view)
        v = view
        viewModel = ViewModelProvider(requireActivity()).get(BookDetailsViewModel::class.java)
        Log.d("NM", "in TabContainer")
    }

    override fun onStart() {
        super.onStart()

        viewModelTab.fetchOrder().observe(viewLifecycleOwner, { result ->
            Log.d("NM", "fetchOrder $result")
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    binding.txtOrderNum.text = result.data!!.id.toString().padStart(5,'0')
                    binding.edtTitle.setText(result.data!!.name)
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })

        binding.btnModify.setOnClickListener {
            //TODO(update item from DB)
            val newName = binding.edtTitle.text.toString()
            viewModelTab.updateOrder(newName)
        }

        binding.btnDelete.setOnClickListener {
            //TODO(delete item from DB)
        }
    }


}