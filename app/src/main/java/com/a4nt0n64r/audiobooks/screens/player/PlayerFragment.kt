package com.a4nt0n64r.audiobooks.screens.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.a4nt0n64r.audiobooks.BuildConfig
import com.a4nt0n64r.audiobooks.R
import com.a4nt0n64r.audiobooks.databinding.FragmentPlayerBinding
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import com.a4nt0n64r.audiobooks.utils.BOOK
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
        initViewModelParameters()
        initBookObserver()
        initPlayPauseObserver()
    }

    private fun initViewModelParameters() {
        val bundle = this.arguments
        if (bundle != null) {
            viewModel.setBook(bundle.getSerializable(BOOK) as BookUI)
        }
    }

    private fun initBookObserver() {
        val bookObserver: Observer<BookUI> = Observer {
            binding.author.text = it.author
            binding.bookName.text = it.name
            Glide.with(binding.bookImage.context)
                .load(BuildConfig.API_URL + "static/" + it.imageUrl)
                .into(binding.bookImage)
            binding.progress.max = it.duration
        }
        viewModel.book.observe(this, bookObserver)
    }

    private fun initPlayPauseObserver() {
        val isPlayingObserver: Observer<Boolean> = Observer {
            if (!it) {
                binding.playPause.setImageResource(R.drawable.play_arrow_40dp)
            } else {
                binding.playPause.setImageResource(R.drawable.pause_40dp)
            }
        }
        viewModel.isPlaying.observe(this, isPlayingObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playPause.setOnClickListener {
            if (viewModel.isPlaying.value != null) {
                if (!viewModel.isPlaying.value!!) {
//                    playBook()
                } else {
//                    stopBook()
                }
            }
        }
    }

    override fun onDestroy() {
        val bundle = Bundle()
        bundle.putSerializable(BOOK, viewModel.book.value)
        super.onDestroy()
    }
}
