package com.a4nt0n64r.audiobooks.screens.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.a4nt0n64r.audiobooks.di.dependencies.DataManager
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val dataManager: DataManager) : ViewModel() {

    var booksList = MutableLiveData<List<BookUI>>()

    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            booksList.postValue(dataManager.getBooks())
        }
    }
}

class ListViewModelFactory(
    private val dataManager: DataManager
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ListViewModel(dataManager) as T
}
