package com.kikuma.kikumaapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.utils.DataDummy

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getAllArticle(): LiveData<List<ArticleEntity>> = homeRepository.getAllArticles()
}