package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.bookdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.databinding.BookDetailsFragmentBinding
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

        viewModelTab.name.observe(viewLifecycleOwner, { result ->
            binding.txtTitle.text = result.toString()
        })
    }


}