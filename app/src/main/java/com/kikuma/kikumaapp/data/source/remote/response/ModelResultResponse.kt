package com.kikuma.kikumaapp.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelResultResponse(
        @field:SerializedName("resultId")
        var resultId: String,
        @field:SerializedName("historyParent")
        var historyParent: String,
        @field:SerializedName("disease")
        var disease: String,
        @field:SerializedName("percentage")
        var percentage: String,
): Parcelable
