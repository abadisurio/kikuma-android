package com.kikuma.kikumaapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "modelresultentities")
data class ModelResultEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "resultId")
        var resultId: String,

        @ColumnInfo(name = "historyParent")
        var historyParent: String,

        @ColumnInfo(name = "disease")
        var disease: String,

        @ColumnInfo(name = "percentage")
        var percentage: String,
)
