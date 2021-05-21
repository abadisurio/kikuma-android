package com.kikuma.kikumaapp.data.source

import androidx.lifecycle.LiveData
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity

interface HomeDataSource {

    fun getAllArticles(): LiveData<List<ArticleEntity>>

    fun getDetailArticle(articleId: String): LiveData<ArticleEntity>

    fun getAllHistory(): LiveData<List<HistoryEntity>>
}