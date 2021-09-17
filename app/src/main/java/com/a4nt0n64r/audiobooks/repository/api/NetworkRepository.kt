package com.a4nt0n64r.audiobooks.repository.api

interface NetworkRepository {
    fun getBooks(myCallBack: MyCallBack)
}
