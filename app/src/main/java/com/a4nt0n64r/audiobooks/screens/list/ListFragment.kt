package com.a4nt0n64r.audiobooks.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.a4nt0n64r.audiobooks.APP_ACTIVITY
import com.a4nt0n64r.audiobooks.BOOK
import com.a4nt0n64r.audiobooks.R
import com.a4nt0n64r.audiobooks.databinding.ListFragmentBinding
import com.a4nt0n64r.audiobooks.di.components.DaggerNetworkComponent
import com.a4nt0n64r.audiobooks.di.components.NetworkComponent
import com.a4nt0n64r.audiobooks.di.dependencies.DataManager
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var dataManager: DataManager

    private var nullableBinding: ListFragmentBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var viewModel: ListViewModel
    private lateinit var observerOnList: Observer<List<BookUI>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nullableBinding = ListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkComponent = getNetworkComponent()
        networkComponent.inject(this)
        val vm: ListViewModel by viewModels {
            ListViewModelFactory(
                dataManager
            )
        }
        viewModel = vm
    }

    private fun getNetworkComponent(): NetworkComponent {
        return DaggerNetworkComponent.builder()
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpRVListener()
        viewModel.bookList.observe(viewLifecycleOwner, observerOnList)
        viewModel.getBooks()
    }

    private fun setUpRecyclerView() {
        val adapter = BooksAdapter()
        val recyclerView = binding.listOfBooks
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        observerOnList = Observer {
            val list = it
            adapter.setData(list)
        }
    }

    private fun setUpRVListener() {
        binding.listOfBooks.addOnItemTouchListener(
            BooksRecyclerViewItemClickListener(
                requireActivity(),
                binding.listOfBooks,
                object : BooksRecyclerViewItemClickListener.OnItemClickListener {

                    override fun onItemClick(view: View, position: Int) {
                        bookClick(
                            position
                        )
                    }
                })
        )
    }

    fun bookClick(position: Int) {
        val bundle = Bundle()
        bundle.putSerializable(BOOK, viewModel.bookList.value?.get(position))
    }
}
