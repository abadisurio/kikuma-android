package com.kikuma.kikumaapp.ui.detailarticle

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.utils.DataDummy

class DetailArticleViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private lateinit var articleId: String

    fun setSelectedArticle(articleId: String) {
        this.articleId = articleId
    }

    fun getArticle(): LiveData<ArticleEntity> = homeRepository.getDetailArticle(articleId)
}