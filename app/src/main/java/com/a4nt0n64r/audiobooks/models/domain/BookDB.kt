package com.a4nt0n64r.audiobooks.models.domain

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDB(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val author: String,
    val imageUrl: String,
    val audioUrl: String,
    val duration: Int
): RealmObject()
