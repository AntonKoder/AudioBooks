package com.a4nt0n64r.audiobooks.screens.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.a4nt0n64r.audiobooks.databinding.PlayerFragmentBinding

class PlayerFragment : Fragment() {

    private var nullableBinding: PlayerFragmentBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var viewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nullableBinding = PlayerFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
//        TODO("init VM")
    }
}
