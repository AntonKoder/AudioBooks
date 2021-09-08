package com.a4nt0n64r.audiobooks.screens.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a4nt0n64r.audiobooks.di.components.DaggerNetworkComponent
import com.a4nt0n64r.audiobooks.di.components.NetworkComponent
import com.a4nt0n64r.audiobooks.di.dependencies.DataManager
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import com.a4nt0n64r.audiobooks.utils.MyCallBack
import kotlinx.coroutines.async
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
        viewModelScope.async {
            dataManager.getBooks(object : MyCallBack {
                override fun onError(message: String) {
                    Log.d("ASD", message)
                }

                override fun onSuccess(value: List<BookUI>) {
                    _bookList.postValue(value)
                }
            })
        }
    }
}
