package com.kikuma.kikumaapp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.kikuma.kikumaapp.data.source.local.entity.*

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

    //disease
    @Query("SELECT * FROM diseaseentities")
    fun getAllResultDisease(): LiveData<List<DiseaseEntity>>

    @Transaction
    @Query("SELECT * FROM diseaseentities WHERE disease = :diseaseName")
    fun getDetailDisease(diseaseName: String): LiveData<DiseaseEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertResult(disease: List<DiseaseEntity>)

    @Update
    fun updateResult(disease: DiseaseEntity)

    //tips
    @Query("SELECT * FROM tipsentities")
    fun getTips(): LiveData<List<TipsEntity>>

    @Transaction
    @Query("SELECT * FROM tipsentities WHERE forDisease = :forDisease")
    fun getTipsForDisease(forDisease: String): LiveData<List<TipsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTips(tips: List<TipsEntity>)

    @Update
    fun updateTips(tips: TipsEntity)

    @Transaction
    @Query("SELECT * FROM modelresultentities WHERE historyParent = :historyId")
    fun getModelResults(historyId: String): LiveData<List<ModelResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModelResult(modelResultList: List<ModelResultEntity>)
}
