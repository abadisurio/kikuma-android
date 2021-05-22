package com.kikuma.kikumaapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historyentities")
data class HistoryEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "historyId")
    var historyId: String,

    @NonNull
    @ColumnInfo(name = "disease")
    var disease: String,

    @NonNull
    @ColumnInfo(name = "posted")
    var posted: String,
)
