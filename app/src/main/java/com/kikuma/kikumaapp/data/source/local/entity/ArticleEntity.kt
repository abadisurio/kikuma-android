package com.kikuma.kikumaapp.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleEntity(
    var articleId: String,
    var title: String,
    var description: String,
    var imagePath: String,
    var posted: String
): Parcelable
