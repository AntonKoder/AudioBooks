package com.a4nt0n64r.audiobooks.models.domain

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDB(
    @PrimaryKey
    var id: Int? = null,
    var name: String? = null,
    var author: String? = null,
    var imageUrl: String? = null,
    var audioUrl: String? = null,
    var duration: Int? = null,
) : RealmObject()
