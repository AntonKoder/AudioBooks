package com.a4nt0n64r.audiobooks.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.a4nt0n64r.audiobooks.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private var nullableBinding: ListFragmentBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nullableBinding = ListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
//        TODO("init VM")
    }
}
