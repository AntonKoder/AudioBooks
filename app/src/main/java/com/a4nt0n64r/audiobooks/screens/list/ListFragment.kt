package com.a4nt0n64r.audiobooks.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.a4nt0n64r.audiobooks.databinding.FragmentListBinding
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import com.a4nt0n64r.audiobooks.utils.APP_ACTIVITY
import com.a4nt0n64r.audiobooks.utils.BOOK
import com.a4nt0n64r.audiobooks.utils.PLAYER_FRAGMENT

class ListFragment : Fragment() {

    private var nullableBinding: FragmentListBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var viewModel: ListViewModel
    private lateinit var observerOnList: Observer<List<BookUI>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nullableBinding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ListViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupRVListener()
        viewModel.bookList.observe(viewLifecycleOwner, observerOnList)
        viewModel.getBooks()
    }

    private fun setupRecyclerView() {
        val adapter = BooksAdapter()
        val recyclerView = binding.listOfBooks
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        observerOnList = Observer {
            val list = it
            adapter.setData(list)
        }
    }

    private fun setupRVListener() {
        binding.listOfBooks.addOnItemTouchListener(
            BooksRecyclerViewItemClickListener(
                requireActivity(),
                binding.listOfBooks,
                object : BooksRecyclerViewItemClickListener.OnItemClickListener {

                    override fun onItemClick(view: View, position: Int) {
                        if (viewModel.bookList.value?.get(position)!!.isFree) {
                            bookClick(
                                position
                            )
                        }
                    }
                })
        )
    }

    fun bookClick(position: Int) {
        val bundle = Bundle()
        bundle.putSerializable(BOOK, viewModel.bookList.value?.get(position))
        APP_ACTIVITY.navigate(PLAYER_FRAGMENT, bundle)
    }
}
