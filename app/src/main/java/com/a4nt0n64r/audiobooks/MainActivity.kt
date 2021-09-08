package com.a4nt0n64r.audiobooks

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.a4nt0n64r.audiobooks.databinding.ActivityMainBinding
import com.a4nt0n64r.audiobooks.screens.list.ListFragment
import com.a4nt0n64r.audiobooks.screens.player.PlayerFragment
import com.a4nt0n64r.audiobooks.utils.APP_ACTIVITY
import com.a4nt0n64r.audiobooks.utils.BACKSTACK
import com.a4nt0n64r.audiobooks.utils.DUMMY_CODE
import com.a4nt0n64r.audiobooks.utils.LIST_FRAGMENT
import com.a4nt0n64r.audiobooks.utils.PLAYER_FRAGMENT

class MainActivity : AppCompatActivity() {

    private var nullableBinding: ActivityMainBinding? = null
    private val binding get() = nullableBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nullableBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        checkPermissions()
    }

    private fun checkPermissions() {
        val permissions = arrayOf(Manifest.permission.INTERNET)
        ActivityCompat.requestPermissions(
            this,
            permissions,
            DUMMY_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            DUMMY_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    // permission was granted, do your work....
                    navigate(LIST_FRAGMENT, null)
                } else {
                    // permission denied
                    // Disable the functionality that depends on this permission.
                    finish()
                }
                return
            }
        }
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
