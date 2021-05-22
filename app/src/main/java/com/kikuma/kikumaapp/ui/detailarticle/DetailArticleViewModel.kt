package com.kikuma.kikumaapp.ui.detailarticle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.vo.Resource

class DetailArticleViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    val articleId = MutableLiveData<String>()

    fun setSelectedArticle(articleId: String) {
        this.articleId.value = articleId
    }

    var detailArticle: LiveData<Resource<ArticleEntity>> = Transformations.switchMap(articleId) { mArticleId ->
        homeRepository.getDetailArticle(mArticleId)
    }
}