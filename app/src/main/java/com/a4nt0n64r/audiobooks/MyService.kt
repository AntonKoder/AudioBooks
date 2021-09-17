package com.a4nt0n64r.audiobooks

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.os.HandlerCompat.postDelayed
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyService : Service() {
    lateinit var player: MediaPlayer

    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> get() = _progress

    private val binder = LocalBinder()

    // **************************************
    val playerHandler = Handler(Looper.getMainLooper())

    // **************************************

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): MyService = this@MyService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("serv", "onStartCommand")

        val extras = intent!!.extras
        val url = extras?.get("audio_link") as String

        player = MediaPlayer.create(
            this,
            Uri.parse(BuildConfig.API_URL + "static/" + url)
        )
        player.start()
//        _progress.value = player.currentPosition.div(1000)

        // ***********
        playerHandler.post(object : Runnable {
            override fun run() {
                _progress.value = player.currentPosition.div(1000)
                playerHandler.postDelayed(this, 1000)
            }
        })
        // ***********

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }
}
