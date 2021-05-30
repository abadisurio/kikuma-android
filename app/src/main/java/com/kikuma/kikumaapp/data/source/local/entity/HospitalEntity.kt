package com.kikuma.kikumaapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospitalentities")
data class HospitalEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "hospitalId")
        var hospitalId: String,

        @ColumnInfo(name = "hospital")
        var hospital: String,

        @ColumnInfo(name = "address")
        var address: String,

        @ColumnInfo(name = "rate")
        var rate: Double,

        @ColumnInfo(name = "price")
        var price: String,

        @ColumnInfo(name = "imagePath")
        var imagePath: String
)
