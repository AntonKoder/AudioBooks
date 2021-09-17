package com.a4nt0n64r.audiobooks.repository.realm

import com.a4nt0n64r.audiobooks.models.domain.BookDB
import com.a4nt0n64r.audiobooks.repository.api.MyCallBack

interface DatabaseFunctions {
    suspend fun getBooks(myCallBack: MyCallBack)
    fun saveBook(book: BookDB)
    fun saveListOfBooks(list: List<BookDB>)
    suspend fun deleteBooks()
}
