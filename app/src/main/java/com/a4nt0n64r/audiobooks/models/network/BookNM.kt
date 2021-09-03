package com.a4nt0n64r.audiobooks.models.network

import com.google.gson.annotations.SerializedName

data class BookNM(

    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("is_free") val isFree: Boolean,
    @SerializedName("category_id") val categoryId: String,
    @SerializedName("audiofile_url") val audiofileUrl: String,
    @SerializedName("audio_duration") val audioDuration: Int,
    @SerializedName("pub_date") val pubDate: String,
    @SerializedName("is_published") val isPublished: Boolean,
    @SerializedName("id") val id: String
)
