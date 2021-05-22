package com.kikuma.kikumaapp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity

@Dao
interface HomeDao {

    @Query("SELECT * FROM articleentities")
    fun getAllArticles(): DataSource.Factory<Int, ArticleEntity>

    @Query("SELECT * FROM historyentities")
    fun getAllHistory(): DataSource.Factory<Int, HistoryEntity>

    @Transaction
    @Query("SELECT * FROM articleentities WHERE articleId = :articleId")
    fun getDetailArticle(articleId: String): LiveData<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHistory(movies: List<HistoryEntity>)
}