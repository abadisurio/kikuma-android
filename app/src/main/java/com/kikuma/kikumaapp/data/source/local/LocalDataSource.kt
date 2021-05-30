package com.kikuma.kikumaapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.kikuma.kikumaapp.data.source.local.entity.*
import com.kikuma.kikumaapp.data.source.local.room.HomeDao

class LocalDataSource private constructor(private val mHomeDao: HomeDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(homeDao: HomeDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(homeDao)
    }

    //article
    fun getAllArticles(): DataSource.Factory<Int, ArticleEntity> = mHomeDao.getAllArticles()

    fun getDetailArticle(articleId: String): LiveData<ArticleEntity> =
        mHomeDao.getDetailArticle(articleId)

    fun insertArticle(article: List<ArticleEntity>) = mHomeDao.insertArticle(article)

    //history
    fun getAllHistory(): DataSource.Factory<Int, HistoryEntity> = mHomeDao.getAllHistory()

    fun insertHistory(history: List<HistoryEntity>) = mHomeDao.insertHistory(history)

    //clinic
    fun getAllHospitals(): LiveData<List<HospitalEntity>> = mHomeDao.getAllHospitals()

    fun insertHospital(hospital: List<HospitalEntity>) = mHomeDao.insertHospitals(hospital)

    //disease
    fun getAllResult(): LiveData<List<DiseaseEntity>> = mHomeDao.getAllResultDisease()

    fun insertResult(disease: List<DiseaseEntity>) = mHomeDao.insertResult(disease)

    fun getDetailResult(resultId: String): LiveData<DiseaseEntity> =
            mHomeDao.getDetailResult(resultId)

    //tips
    fun getTipsForDisease(forDisease: String): LiveData<List<TipsEntity>> =
            mHomeDao.getTipsForDisease(forDisease)

    //fun getAllTips(): LiveData<List<TipsEntity>> = mHomeDao.getTips()
    fun insertTips(tips: List<TipsEntity>) = mHomeDao.insertTips(tips)
}