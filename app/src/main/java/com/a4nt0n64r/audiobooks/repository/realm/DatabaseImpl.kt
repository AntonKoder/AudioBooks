package com.a4nt0n64r.audiobooks.repository.realm

import android.util.Log
import com.a4nt0n64r.audiobooks.models.domain.BookDB
import com.a4nt0n64r.audiobooks.models.toBookUI
import com.a4nt0n64r.audiobooks.utils.MyCallBack
import io.realm.Realm
import io.realm.RealmResults

class DatabaseImpl(private val realm: Realm) : DatabaseFunctions {

    override fun saveBook(book: BookDB) {
        try {
            realm.beginTransaction()
            // Auto Increment ID
            val currentIdNumber: Number? = realm.where(BookDB::class.java).max("id")
            val nextId = (currentIdNumber?.toInt()?.plus(1)) ?: 1
            book.id = nextId

            realm.copyToRealmOrUpdate(book)
            realm.commitTransaction()

            Log.d("TAG", "Book added successfully!")
        } catch (e: Exception) {
            Log.d("TAG", "Fail $e")
        }
    }

    override suspend fun deleteBooks() {
        realm.beginTransaction()
        realm.delete(BookDB::class.java)
        realm.commitTransaction()
    }

    override fun saveListOfBooks(list: List<BookDB>) {
        list.forEach { book -> saveBook(book) }
    }

    override suspend fun getBooks(myCallBack: MyCallBack) {
        val results: RealmResults<BookDB> = realm.where<BookDB>(BookDB::class.java).findAll()
        if (results.isEmpty()) {
            Log.d("__ERROR", "Empty DB")
            myCallBack.onError("Error in db")
        } else {
            Log.d("__SUCCESS", "all ok in get books")
            myCallBack.onSuccess(results.map { it.toBookUI() })
        }
        Log.d("TAG", "Get this: $results")
    }
}
