package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.secondactivity.tabcontainer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.data.BookDataSource
import com.nicomahnic.dadm.leyendoysiendo.databinding.TabContainerFragmentBinding
import com.nicomahnic.dadm.leyendoysiendo.repository.BookRepositoryImpl
import com.nicomahnic.dadm.leyendoysiendo.ui.adapter.TabsViewPagerAdapter
import kotlinx.android.synthetic.main.tab_container_fragment.*

class TabContainerFragment : Fragment(R.layout.tab_container_fragment) {

    private lateinit var binding: TabContainerFragmentBinding
    private lateinit var v: View
    private val viewModelTab: TabContainerViewModel by activityViewModels() {
        BookViewModelFactory(
            BookRepositoryImpl(
                BookDataSource(requireContext())
            )
        )
    }
    private val args: TabContainerFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TabContainerFragmentBinding.bind(view)

        v = view
        viewPager.adapter = TabsViewPagerAdapter(requireActivity())

        Log.d("NM", "in TabContainer")

        viewModelTab.loadOrder(args.orderNum, args.clientName)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_order)
                1 -> tab.text = getString(R.string.tab_books)
                else -> tab.text = "undefined"
            }
        }.attach()
    }
}