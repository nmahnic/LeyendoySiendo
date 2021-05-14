package com.nicomahnic.dadm.leyendoysiendo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.entities.Order

class OrdersAdapter(
    private var orderList: List<Order>,
    val onItemClick: (Int) -> Unit
): RecyclerView.Adapter<OrdersAdapter.OrderHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false)
        return (OrderHolder(view))
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.setName(orderList[position].clientName)

        holder.getItem(position)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class OrderHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setName(name: String) {
            val txt: TextView = itemView.findViewById(R.id.txt_name_item)
            txt.text = name
        }

        fun getItem (position: Int): Unit {
            return itemView.setOnClickListener { onItemClick(position) }
        }
    }
}