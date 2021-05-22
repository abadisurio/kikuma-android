package com.kikuma.kikumaapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articleentities")
data class ArticleEntity(
    @PrimaryKey
    @ColumnInfo(name = "articleId")
    var articleId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "imagePath")
    var imagePath: String,

    @ColumnInfo(name = "posted")
    var posted: String
)
