package com.kikuma.kikumaapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diseaseentities")
data class DiseaseEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "diseaseId")
        var diseaseId: String,

        @ColumnInfo(name = "disease")
        var disease: String,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "imagePath")
        var imagePath: String
)
