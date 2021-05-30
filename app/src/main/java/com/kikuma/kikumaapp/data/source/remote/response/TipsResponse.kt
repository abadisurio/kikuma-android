package com.kikuma.kikumaapp.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TipsResponse(
        var tipsId: String,
        var resultId: String,
        var tips: String,
        var forDisease: String
): Parcelable
