package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.rvorders

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.data.database.appDatabase
import com.nicomahnic.dadm.leyendoysiendo.databinding.RvOrdersFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Order
import com.nicomahnic.dadm.leyendoysiendo.ui.activities.SecondActivity
import com.nicomahnic.dadm.leyendoysiendo.ui.adapter.OrdersAdapter

class RvOrdersFragment : Fragment(R.layout.rv_orders_fragment) {

    private lateinit var binding: RvOrdersFragmentBinding
    private lateinit var v: View
    private lateinit var viewModel: RvOrdersViewModel
    private var orderList: MutableList<Order> = ArrayList<Order>()
    private var db: appDatabase? = null

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var ordersAdapter: OrdersAdapter

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RvOrdersFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(RvOrdersViewModel::class.java)

        v = view

        Log.d("NM", "Singleton ${SecondActivity.User.name}")
        if(orderList.isEmpty()) {
            orderList.add(Order("Dudey", 1))
            orderList.add(Order("DuDey", 2))
            orderList.add(Order("Nico para Dudey", 3))
        }

    }

    override fun onStart() {
        super.onStart()

        binding.rvOrders.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        binding.rvOrders.layoutManager = linearLayoutManager

        ordersAdapter = OrdersAdapter(orderList) { pos ->
            Log.d("NM", pos.toString())
            val action =
                RvOrdersFragmentDirections.actionRvOrdersFragmentToTabContainerFragment(
                    orderNum = orderList[pos].orderNum,
                    clientName = orderList[pos].clientName
                )

            v.findNavController().navigate(action)
        }
        binding.rvOrders.adapter = ordersAdapter
    }

}