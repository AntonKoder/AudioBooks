package com.a4nt0n64r.audiobooks.models.ui

import java.io.Serializable

data class BookUI(
    val name: String,
    val author: String,
    val imageUrl: String,
    val audioUrl: String,
    val duration: Int,
    val isFree: Boolean
) : Serializable
