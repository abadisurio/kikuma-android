package com.kikuma.kikumaapp.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelResultResponse(
        var resultId: String,
        var historyParent: String,
        var disease: String,
        var percentage: String,
): Parcelable
