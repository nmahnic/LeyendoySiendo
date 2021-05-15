package com.nicomahnic.dadm.leyendoysiendo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.data.entities.Book
import com.nicomahnic.dadm.leyendoysiendo.databinding.ItemBookBinding
import com.nicomahnic.dadm.leyendoysiendo.databinding.ItemOrderbookBinding
import kotlinx.android.synthetic.main.nav_header_main.view.*
import kotlinx.android.synthetic.main.second_activity.*

class OrderBooksAdapter(
    context: Context,
    private var bookList: List<Book>,
    val onItemClick: (Int) -> Unit
): RecyclerView.Adapter<OrderBooksAdapter.OrderHolder>() {

    private var context = context
    private lateinit var binding: ItemOrderbookBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_orderbook,parent,false)
        return (OrderHolder(view))
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.setContent(bookList[position])

        holder.getItem(position)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    inner class OrderHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setContent(book: Book) {
            binding = ItemOrderbookBinding.bind(itemView)
            binding.txtBookTitle.text = book.title
            binding.txtAuthor.text = book.author
            binding.txtYear.text = book.year.toString()
            Glide.with(context) //1
                .load(book.imgUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .skipMemoryCache(true) //2
                .diskCacheStrategy(DiskCacheStrategy.NONE) //3
                .transform(CircleCrop()) //4
                .into(binding.imgItem)
        }

        fun getItem (position: Int): Unit {
            return itemView.setOnClickListener { onItemClick(position) }
        }
    }
}