package com.a4nt0n64r.audiobooks.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a4nt0n64r.audiobooks.di.components.DaggerNetworkComponent
import com.a4nt0n64r.audiobooks.di.components.NetworkComponent
import com.a4nt0n64r.audiobooks.di.dependencies.DataManager
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        if (dataManager.getBooksFromDB().isNullOrEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                _bookList.postValue(dataManager.getBooksFromNetwork())
            }
        } else {
            _bookList.postValue(dataManager.getBooksFromDB())
        }
    }
}
