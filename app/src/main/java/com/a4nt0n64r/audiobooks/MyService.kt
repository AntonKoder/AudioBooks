package com.a4nt0n64r.audiobooks

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.util.Log
import androidx.annotation.Nullable

class MyService : Service() {
    lateinit var player: MediaPlayer

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("serv", "onStartCommand")

        val extras = intent!!.extras
        val url = extras?.get("audio_link") as String

        player = MediaPlayer.create(
            this,
            Uri.parse(BuildConfig.API_URL + "static/" + url)
        )
        // staring the player
        player.start()

        // start sticky means service will be explicity started and stopped
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // stopping the player when service is destroyed
        player.stop()
    }
}
