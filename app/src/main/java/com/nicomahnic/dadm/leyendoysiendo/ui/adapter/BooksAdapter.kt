package com.nicomahnic.dadm.leyendoysiendo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.databinding.ItemBookBinding

class BooksAdapter(
    private var bookList: List<Book>
): RecyclerView.Adapter<BooksAdapter.OrderHolder>() {

    private lateinit var binding: ItemBookBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent,false)
        return (OrderHolder(view))
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.setContent(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    inner class OrderHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setContent(book: Book) {
            binding = ItemBookBinding.bind(itemView)
            binding.txtBookTitle.text = book.title
            binding.txtAuthor.text = book.author
            binding.txtDescription.text = book.description
        }
    }
}