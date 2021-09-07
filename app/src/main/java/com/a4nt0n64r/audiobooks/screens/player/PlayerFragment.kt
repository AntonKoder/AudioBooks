package com.a4nt0n64r.audiobooks.screens.player

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.a4nt0n64r.audiobooks.BOOK
import com.a4nt0n64r.audiobooks.databinding.FragmentPlayerBinding
import com.a4nt0n64r.audiobooks.models.ui.BookUI

class PlayerFragment : Fragment() {

    private var nullableBinding: FragmentPlayerBinding? = null
    private val binding get() = nullableBinding!!

//    private lateinit var viewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nullableBinding = FragmentPlayerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bundle = this.arguments
        if (bundle != null) {
            val book = bundle.getSerializable(BOOK) as BookUI
            Log.d("TAG", book.toString())
        }
    }
}
