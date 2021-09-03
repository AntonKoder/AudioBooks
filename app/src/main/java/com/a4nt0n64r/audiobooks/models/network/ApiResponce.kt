package com.a4nt0n64r.audiobooks.models.network

import com.google.gson.annotations.SerializedName

data class ApiResponce(

    @SerializedName("total") val numberOfItems: Int,
    @SerializedName("items") val items: List<BookNM>
)
