package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.rvorders

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicomahnic.dadm.leyendoysiendo.R

class RvOrdersFragment : Fragment() {

    companion object {
        fun newInstance() = RvOrdersFragment()
    }

    private lateinit var viewModel: RvOrdersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.rv_orders_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RvOrdersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}