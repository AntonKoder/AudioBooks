package com.a4nt0n64r.audiobooks.screens.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a4nt0n64r.audiobooks.models.ui.BookUI

class PlayerViewModel : ViewModel() {

    private val _book = MutableLiveData<BookUI>()
    val book: LiveData<BookUI> get() = _book

    private val _isPlaying = MutableLiveData<Boolean>(false)
    val isPlaying: LiveData<Boolean> get() = _isPlaying

    fun setBook(book: BookUI) {
        _book.value = book
    }
}
