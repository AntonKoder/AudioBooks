package com.a4nt0n64r.audiobooks.repository.realm

import android.util.Log
import com.a4nt0n64r.audiobooks.models.domain.BookDB
import io.realm.Realm
import io.realm.RealmResults

class DatabaseImpl(private val realm: Realm) : DatabaseFunctions {

    override suspend fun getBooks(): RealmResults<BookDB> {
        val results: RealmResults<BookDB> = realm.where<BookDB>(BookDB::class.java).findAllAsync()
        Log.d("TAG", "Get this: $results")
        return results
    }

    override fun getBooks2(): RealmResults<BookDB> {
        val results: RealmResults<BookDB> = realm.where<BookDB>(BookDB::class.java).findAllAsync()
        Log.d("TAG", "Get this: $results")
        return results
    }

    override suspend fun saveBook(book: BookDB) {
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

    override suspend fun saveListOfBooks(list: List<BookDB>) {
        list.forEach { book -> saveBook(book) }
    }
}
