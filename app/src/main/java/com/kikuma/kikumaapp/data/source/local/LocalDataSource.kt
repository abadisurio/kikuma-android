package com.kikuma.kikumaapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity
import com.kikuma.kikumaapp.data.source.local.room.HomeDao

class LocalDataSource private constructor(private val mHomeDao: HomeDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(homeDao: HomeDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(homeDao)
    }

    fun getAllArticles(): DataSource.Factory<Int, ArticleEntity> = mHomeDao.getAllArticles()

    fun getDetailArticle(articleId: String): LiveData<ArticleEntity> =
        mHomeDao.getDetailArticle(articleId)

    fun insertArticle(article: List<ArticleEntity>) = mHomeDao.insertArticle(article)

    fun getAllHistory(): DataSource.Factory<Int, HistoryEntity> = mHomeDao.getAllHistory()

    fun insertHistory(history: List<HistoryEntity>) = mHomeDao.insertHistory(history)
}