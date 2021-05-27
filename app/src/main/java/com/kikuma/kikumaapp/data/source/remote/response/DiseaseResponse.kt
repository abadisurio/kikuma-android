package com.kikuma.kikumaapp.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DiseaseResponse(
        var resultId: String,
        var disease: String,
        var description: String,
        var percentage: String
): Parcelable
