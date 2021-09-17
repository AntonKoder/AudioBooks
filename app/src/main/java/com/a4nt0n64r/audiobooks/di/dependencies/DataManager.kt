package com.a4nt0n64r.audiobooks.di.dependencies

import android.util.Log
import com.a4nt0n64r.audiobooks.models.toBookDB
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import com.a4nt0n64r.audiobooks.repository.api.MyCallBack
import com.a4nt0n64r.audiobooks.repository.api.NetworkRepository
import com.a4nt0n64r.audiobooks.repository.realm.DatabaseFunctions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(
    private val database: DatabaseFunctions,
    private val networkRepository: NetworkRepository
) {

    suspend fun getBooks(action: MyCallBack) {
        database.getBooks(object : MyCallBack {
            override fun onError(message: String) {
                Log.d("__ERROR", "Error in callback in db")

                val saveAction = object : MyCallBack {
                    override fun onSuccess(value: List<BookUI>) {
                        saveBooks(value)
                        action.onSuccess(value)
                    }

                    override fun onError(message: String) {
                        action.onError(message)
                    }
                }
                networkRepository.getBooks(saveAction)
            }

            override fun onSuccess(value: List<BookUI>) {
                Log.d("__SUCCESS", "Success in callback in db")
                action.onSuccess(value)
            }
        })
    }

    private fun saveBooks(bookList: List<BookUI>) {
        database.saveListOfBooks(bookList.map { it.toBookDB() })
    }
}
