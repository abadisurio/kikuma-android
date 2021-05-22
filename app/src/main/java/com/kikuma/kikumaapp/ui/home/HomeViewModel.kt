package com.kikuma.kikumaapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.ArticleEntity
import com.kikuma.kikumaapp.vo.Resource

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getAllArticle(): LiveData<Resource<PagedList<ArticleEntity>>> = homeRepository.getAllArticles()
}