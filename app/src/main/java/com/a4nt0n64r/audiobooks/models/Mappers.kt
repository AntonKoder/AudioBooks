package com.a4nt0n64r.audiobooks.models

import com.a4nt0n64r.audiobooks.models.domain.BookDB
import com.a4nt0n64r.audiobooks.models.network.ApiResponse
import com.a4nt0n64r.audiobooks.models.network.BookNM
import com.a4nt0n64r.audiobooks.models.ui.BookUI

fun ApiResponse.toBooksUI(): List<BookUI> {
    return items.map { it.toBookUI() }
}

fun BookNM.toBookUI(): BookUI {
    return BookUI(
        this.title,
        this.author,
        this.imageUrl,
        this.audiofileUrl,
        this.audioDuration,
        this.isFree
    )
}

fun BookDB.toBookUI(): BookUI {
    return BookUI(
        this.name!!,
        this.author!!,
        this.imageUrl!!,
        this.audioUrl!!,
        this.duration!!,
        this.isFree!!
    )
}

fun BookUI.toBookDB(): BookDB {
    return BookDB(
        null,
        this.name,
        this.author,
        this.imageUrl,
        this.audioUrl,
        this.duration,
        this.isFree
    )
}
