package com.a4nt0n64r.audiobooks.di.dependencies

import android.util.Log
import com.a4nt0n64r.audiobooks.models.toBookDB
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import com.a4nt0n64r.audiobooks.repository.api.NetworkRepository
import com.a4nt0n64r.audiobooks.repository.realm.DatabaseFunctions
import com.a4nt0n64r.audiobooks.utils.MyCallBack
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
                Log.d("ERROR", "Error in callback in db")

//                тут надо завести экшен на сохранение!
                networkRepository.getBooks(action)
            }

            override fun onSuccess(value: List<BookUI>) {
                action.onSuccess(value)
            }
        })
    }

    private suspend fun saveBooks(bookList: List<BookUI>) {
        database.saveListOfBooks(bookList.map { it.toBookDB() })
    }
}
