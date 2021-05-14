package com.nicomahnic.dadm.leyendoysiendo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.entities.Book

class BooksAdapter(
    private var bookList: MutableList<Book>
): RecyclerView.Adapter<BooksAdapter.OrderHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent,false)
        return (OrderHolder(view))
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.setName(bookList[position].title)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    inner class OrderHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setName(name: String) {
            val txt: TextView = itemView.findViewById(R.id.txt_book_title)
            txt.text = name
        }
    }
}