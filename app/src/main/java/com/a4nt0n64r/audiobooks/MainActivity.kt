package com.a4nt0n64r.audiobooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.a4nt0n64r.audiobooks.databinding.ActivityMainBinding
import com.a4nt0n64r.audiobooks.screens.list.ListFragment

class MainActivity : AppCompatActivity() {

    private var nullableBinding: ActivityMainBinding? = null
    private val binding get() = nullableBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nullableBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        nav()
    }

    fun nav(){
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                ListFragment(), "sdasda"
            )
            .addToBackStack("Sdasd")
            .commit()
    }
}
