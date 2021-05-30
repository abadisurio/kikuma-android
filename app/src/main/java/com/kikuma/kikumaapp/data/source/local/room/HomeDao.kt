package com.kikuma.kikumaapp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity
import com.kikuma.kikumaapp.data.source.local.entity.HospitalEntity

@Dao
interface HomeDao {

    //article
    @Query("SELECT * FROM articleentities")
    fun getAllArticles(): DataSource.Factory<Int, ArticleEntity>

    @Transaction
    @Query("SELECT * FROM articleentities WHERE articleId = :articleId")
    fun getDetailArticle(articleId: String): LiveData<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: List<ArticleEntity>)

    //history
    @Query("SELECT * FROM historyentities")
    fun getAllHistory(): DataSource.Factory<Int, HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHistory(movies: List<HistoryEntity>)

    //clinic
    @Query("SELECT * FROM hospitalentities")
    fun getAllHospitals(): LiveData<List<HospitalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHospitals(hospital: List<HospitalEntity>)

    @Update
    fun updateHospital(hospital: HospitalEntity)
}