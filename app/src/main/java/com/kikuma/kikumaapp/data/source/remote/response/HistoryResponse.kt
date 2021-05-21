package com.kikuma.kikumaapp.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoryResponse(
    var disease: String,
    var posted: String
): Parcelable
