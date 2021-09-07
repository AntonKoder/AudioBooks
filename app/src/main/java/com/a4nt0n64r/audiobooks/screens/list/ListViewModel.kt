package com.a4nt0n64r.audiobooks.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.a4nt0n64r.audiobooks.di.dependencies.DataManager
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val dataManager: DataManager) : ViewModel() {

    private val _bookList = MutableLiveData<List<BookUI>>()
    val bookList : LiveData<List<BookUI>> get() = _bookList

    fun getBooks() {
        dataManager.getBooks2()
    }
}

class ListViewModelFactory(
    private val dataManager: DataManager
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ListViewModel(dataManager) as T
}
