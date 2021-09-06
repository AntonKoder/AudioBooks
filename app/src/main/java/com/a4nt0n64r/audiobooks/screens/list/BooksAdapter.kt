package com.a4nt0n64r.audiobooks.screens.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a4nt0n64r.audiobooks.R
import com.a4nt0n64r.audiobooks.models.ui.BookUI

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    private var listCategory = emptyList<BookUI>()

    fun setData(list: List<BookUI>) {
        listCategory = list
        notifyDataSetChanged()
    }

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookName = itemView.findViewById<TextView>(R.id.book_name)
        val bookAuthor = itemView.findViewById<TextView>(R.id.book_author)
        val bookImage = itemView.findViewById<ImageView>(R.id.book_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bookName.text = listCategory[position].name
        holder.bookAuthor.text = listCategory[position].author
//        holder.bookImage.text = listCategory[position].name  загрузить картинку через глайд
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }
}
