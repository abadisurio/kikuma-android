package com.kikuma.kikumaapp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kikuma.kikumaapp.data.source.local.entity.*
import com.kikuma.kikumaapp.vo.Resource

interface HomeDataSource {

    fun getAllArticles(): LiveData<Resource<PagedList<ArticleEntity>>>

    fun getDetailArticle(articleId: String): LiveData<Resource<ArticleEntity>>

    fun getAllHistory(): LiveData<Resource<PagedList<HistoryEntity>>>

    fun getAllResult(): LiveData<List<DiseaseEntity>>

    fun getHospital(): LiveData<List<HospitalEntity>>

    fun getResultWithTips(resultId: String): LiveData<DiseaseEntity>

    fun getAllTipsByResult(resultId: String): LiveData<List<TipsEntity>>
}