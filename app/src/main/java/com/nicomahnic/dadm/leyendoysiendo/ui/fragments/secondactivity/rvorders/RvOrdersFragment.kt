package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.rvorders

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.core.Resource
import com.nicomahnic.dadm.leyendoysiendo.data.DataSource
import com.nicomahnic.dadm.leyendoysiendo.data.database.AppDatabase
import com.nicomahnic.dadm.leyendoysiendo.databinding.RvOrdersFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.repository.RepositoryImpl
import com.nicomahnic.dadm.leyendoysiendo.ui.activities.SecondActivity
import com.nicomahnic.dadm.leyendoysiendo.ui.adapter.OrdersAdapter
import com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.ViewModelFactory

class RvOrdersFragment : Fragment(R.layout.rv_orders_fragment) {

    private lateinit var binding: RvOrdersFragmentBinding
    private lateinit var v: View
    private val viewModel: RvOrdersViewModel by activityViewModels(){
        ViewModelFactory(
            RepositoryImpl(
                DataSource(
                    requireContext(),
                    AppDatabase.getAppDataBase(requireActivity().applicationContext)
                )
            )
        )
    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var ordersAdapter: OrdersAdapter

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RvOrdersFragmentBinding.bind(view)

        v = view

        Log.d("NM", "Singleton ${SecondActivity.User.name}")

    }

    override fun onStart() {
        super.onStart()

        binding.rvOrders.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        binding.rvOrders.layoutManager = linearLayoutManager

        viewModel.fetchOrderList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
                is Resource.Success -> {
                    result.data?.let{ orderList ->
                        ordersAdapter = OrdersAdapter(orderList) { pos ->
                            Log.d("NM", pos.toString())
                            val action =
                                RvOrdersFragmentDirections.actionRvOrdersFragmentToTabContainerFragment(
                                    orderNum = orderList[pos].id,
                                    clientName = orderList[pos].name
                                )

                            v.findNavController().navigate(action)
                        }
                        binding.rvOrders.adapter = ordersAdapter
                    }
                }
            }
        })

    }

}