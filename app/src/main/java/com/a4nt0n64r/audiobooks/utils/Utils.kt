package com.a4nt0n64r.audiobooks.utils

import com.a4nt0n64r.audiobooks.models.ui.BookUI

interface MyCallBack {
    fun onSuccess(value: List<BookUI>)
    fun onError(message: String)
}
