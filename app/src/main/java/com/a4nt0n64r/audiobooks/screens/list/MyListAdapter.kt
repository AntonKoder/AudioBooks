package com.a4nt0n64r.audiobooks.screens.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a4nt0n64r.audiobooks.BuildConfig
import com.a4nt0n64r.audiobooks.R
import com.a4nt0n64r.audiobooks.databinding.BookItemBinding
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import com.bumptech.glide.Glide

class MyListAdapter(val bookClick: () -> Unit) :
    ListAdapter<BookUI, MyListAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.book_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyListAdapter.ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = BookItemBinding.bind(itemView)
        fun bind(item: BookUI) = with(itemView) {
            // TODO: Bind the data with View
            binding.bookName.text = item.name
            binding.bookAuthor.text = item.author
            Glide.with(binding.bookImage.context)
                .load(BuildConfig.API_URL + "static/" + item.imageUrl)
                .into(binding.bookImage)

            setOnClickListener {
                // TODO: Handle on click
//                Log.d("bla", item.name)

                if (item.isFree) {
//                    bookClick ???
                    bookClick()
                }
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<BookUI>() {

    override fun areItemsTheSame(oldItem: BookUI, newItem: BookUI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BookUI, newItem: BookUI): Boolean {
        return oldItem == newItem
    }
}
