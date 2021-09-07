package com.a4nt0n64r.audiobooks.di.dependencies

import com.a4nt0n64r.audiobooks.models.toBookDB
import com.a4nt0n64r.audiobooks.models.toBookUI
import com.a4nt0n64r.audiobooks.models.toBooksUI
import com.a4nt0n64r.audiobooks.models.ui.BookUI
import com.a4nt0n64r.audiobooks.repository.api.NetworkRepository
import com.a4nt0n64r.audiobooks.repository.realm.DatabaseFunctions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(
    private val database: DatabaseFunctions,
    private val networkRepository: NetworkRepository
) {

    suspend fun getBooks(): List<BookUI> {
        return if (database.getBooks().isNullOrEmpty()) {
            val books = networkRepository.getBooks().toBooksUI()
            saveBooks(books)
            books
        } else database.getBooks().map { it.toBookUI() }
    }

    private suspend fun saveBooks(bookList: List<BookUI>) {
        database.saveListOfBooks(bookList.map { it.toBookDB() })
    }

    // Make request to DB sync TODO("Remove after coroutines work")

    fun getBooksFromDB(): List<BookUI> = database.getBooks2().map { it.toBookUI() }

    suspend fun getBooksFromNetwork(): List<BookUI> = networkRepository.getBooks().toBooksUI()

    private fun saveBooksToDB(bookList: List<BookUI>) {
        database.saveListOfBooks2(bookList.map { it.toBookDB() })
    }
}
