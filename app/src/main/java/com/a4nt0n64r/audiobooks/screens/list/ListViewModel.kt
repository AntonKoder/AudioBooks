package com.a4nt0n64r.audiobooks.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a4nt0n64r.audiobooks.di.components.DaggerNetworkComponent
import com.a4nt0n64r.audiobooks.di.components.NetworkComponent
import com.a4nt0n64r.audiobooks.di.dependencies.DataManager
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import javax.inject.Inject

class ListViewModel() : ViewModel() {

    @Inject
    lateinit var dataManager: DataManager

    init {
        val networkComponent = getNetworkComponent()
        networkComponent.inject(this)
    }

    private fun getNetworkComponent(): NetworkComponent {
        return DaggerNetworkComponent.builder()
            .build()
    }

    private val _bookList = MutableLiveData<List<BookUI>>()
    val bookList: LiveData<List<BookUI>> get() = _bookList

    fun getBooks() {
        dataManager.getBooks2()
    }
}
