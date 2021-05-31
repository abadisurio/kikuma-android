package com.kikuma.kikumaapp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kikuma.kikumaapp.data.source.local.entity.*
import com.kikuma.kikumaapp.data.source.remote.response.ModelResultResponse
import com.kikuma.kikumaapp.vo.Resource

interface HomeDataSource {

    //article
    fun getAllArticles(): LiveData<Resource<PagedList<ArticleEntity>>>

    fun getDetailArticle(articleId: String): LiveData<Resource<ArticleEntity>>

    //history
    fun getAllHistory(): LiveData<Resource<PagedList<HistoryEntity>>>
    fun refreshAllHistory(): LiveData<Resource<PagedList<HistoryEntity>>>
    fun insertHistory(): LiveData<Boolean>

    //clinic
    fun getAllHospital(): LiveData<Resource<List<HospitalEntity>>>

    //result
    fun getAllResult(): LiveData<Resource<List<DiseaseEntity>>>

    fun getDetailDisease(diseaseId: String): LiveData<Resource<DiseaseEntity>>

    //tips
    fun getTipsForDisease(forDisease: String): LiveData<Resource<List<TipsEntity>>>

    fun getModelResults(historyId: String): LiveData<Resource<List<ModelResultEntity>>>
    fun setModelResults(imageBase64: String): LiveData<String>
    fun insertModelResult(diseaseResponse: List<ModelResultResponse>)

}