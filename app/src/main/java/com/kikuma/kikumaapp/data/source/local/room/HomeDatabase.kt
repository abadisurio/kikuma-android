package com.kikuma.kikumaapp.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kikuma.kikumaapp.data.source.local.entity.*

@Database(entities = [ArticleEntity::class, HistoryEntity::class, HospitalEntity::class, DiseaseEntity::class, TipsEntity::class,  ModelResultEntity::class],
    version = 1,
    exportSchema = false)
abstract class HomeDatabase : RoomDatabase() {
    abstract fun homeDao(): HomeDao

    companion object {
        @Volatile
        private var INSTANCE: HomeDatabase? = null

        fun getInstance(context: Context): HomeDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    HomeDatabase::class.java,
                    "Kikuma.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}