package com.a4nt0n64r.audiobooks.repository.realm

import com.a4nt0n64r.audiobooks.models.domain.BookDB
import com.a4nt0n64r.audiobooks.utils.MyCallBack

interface DatabaseFunctions {
    suspend fun getBooks(myCallBack: MyCallBack)
    suspend fun saveBook(book: BookDB)
    suspend fun saveListOfBooks(list: List<BookDB>)
    suspend fun deleteBooks()
}
