package com.a4nt0n64r.audiobooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.a4nt0n64r.audiobooks.databinding.ActivityMainBinding
import com.a4nt0n64r.audiobooks.screens.list.ListFragment
import com.a4nt0n64r.audiobooks.screens.player.PlayerFragment

class MainActivity : AppCompatActivity() {

    private var nullableBinding: ActivityMainBinding? = null
    private val binding get() = nullableBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nullableBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        navigate(LIST_FRAGMENT, null)
    }

    fun navigate(destination: String, bundle: Bundle?) {
        when (destination) {
            LIST_FRAGMENT -> {
                showListFragment(bundle)
            }
            PLAYER_FRAGMENT -> {
                showPlayerFragment(bundle)
            }
        }
    }

    private fun showListFragment(bundle: Bundle?) {
        val listFragment = ListFragment()
        listFragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                listFragment, LIST_FRAGMENT
            )
            .addToBackStack(BACKSTACK)
            .commit()
    }

    private fun showPlayerFragment(bundle: Bundle?) {
        val listFragment = PlayerFragment()
        listFragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                listFragment, LIST_FRAGMENT
            )
            .addToBackStack(BACKSTACK)
            .commit()
    }
}
