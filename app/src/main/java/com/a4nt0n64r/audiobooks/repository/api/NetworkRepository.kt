package com.a4nt0n64r.audiobooks.repository.api

import com.a4nt0n64r.audiobooks.utils.MyCallBack

interface NetworkRepository {
    fun getBooks(myCallBack: MyCallBack)
}
