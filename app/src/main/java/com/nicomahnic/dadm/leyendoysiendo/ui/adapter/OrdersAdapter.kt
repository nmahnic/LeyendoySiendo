package com.nicomahnic.dadm.leyendoysiendo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.databinding.ItemOrderBinding
import com.nicomahnic.dadm.leyendoysiendo.data.entities.OrderEntity

class OrdersAdapter(
    private var orderList: List<OrderEntity>,
    val onItemClick: (Int) -> Unit
): RecyclerView.Adapter<OrdersAdapter.OrderHolder>() {

    private lateinit var binding: ItemOrderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false)
        return (OrderHolder(view))
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.setItem(orderList[position])

        holder.getItem(position)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class OrderHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setItem(order: OrderEntity) {
            binding = ItemOrderBinding.bind(itemView)
            binding.txtNameItem.text = order.name
            binding.txtOrderNum.text = order.id.toString().padStart(5,'0')
        }

        fun getItem (position: Int): Unit {
            return itemView.setOnClickListener { onItemClick(position) }
        }
    }
}