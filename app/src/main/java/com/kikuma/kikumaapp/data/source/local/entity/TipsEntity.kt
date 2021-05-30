package com.kikuma.kikumaapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "tipsentities")
        //primaryKeys = ["tipsId", "resultId"],
        //foreignKeys = [ForeignKey(entity = DiseaseEntity::class,
        //        parentColumns = ["resultId"],
        //        childColumns = ["resultId"])],
        //indices = [Index(value = ["tipsId"]),
        //        Index(value = ["resultId"])])
data class TipsEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "tipsId")
        var tipsId: String,

        @NonNull
        @ColumnInfo(name = "resultId")
        var resultId: String,

        @NonNull
        @ColumnInfo(name = "tips")
        var tips: String,

        @NonNull
        @ColumnInfo(name = "forDisease")
        var forDisease: String
)
