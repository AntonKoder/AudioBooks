package com.a4nt0n64r.audiobooks.screens.player

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a4nt0n64r.audiobooks.MyService
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import com.a4nt0n64r.audiobooks.utils.APP_ACTIVITY

class PlayerViewModel : ViewModel() {

    private val _book = MutableLiveData<BookUI>()
    val book: LiveData<BookUI> get() = _book

    private val _isPlaying = MutableLiveData<Boolean>(false)
    val isPlaying: LiveData<Boolean> get() = _isPlaying

    fun setBook(book: BookUI) {
        _book.value = book
    }

    fun playBook() {
        val serviceIntent = Intent(APP_ACTIVITY, MyService::class.java)
        serviceIntent.putExtra("audio_link", book.value?.audioUrl)
        APP_ACTIVITY.startService(serviceIntent)
        _isPlaying.value = true
        Log.d("serv", "start")
    }

    fun stopPlayingBook() {
        APP_ACTIVITY.stopService(Intent(APP_ACTIVITY, MyService::class.java))
        _isPlaying.value = false
        Log.d("serv", "stop")
    }
}
