package com.a4nt0n64r.audiobooks.screens.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.a4nt0n64r.audiobooks.BOOK
import com.a4nt0n64r.audiobooks.BuildConfig
import com.a4nt0n64r.audiobooks.databinding.FragmentPlayerBinding
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import com.bumptech.glide.Glide

class PlayerFragment : Fragment() {

    private var nullableBinding: FragmentPlayerBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var viewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nullableBinding = FragmentPlayerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = PlayerViewModel()
        val bundle = this.arguments
        if (bundle != null) {
            viewModel.setBook(bundle.getSerializable(BOOK) as BookUI)
        }
        val bookObserver: Observer<BookUI> = Observer {
            binding.author.text = it.author
            binding.bookName.text = it.name
            Glide.with(binding.bookImage.context)
                .load(BuildConfig.API_URL + "static/" + it.imageUrl)
                .into(binding.bookImage)
        }
        viewModel.book.observe(this, bookObserver)
    }
}
