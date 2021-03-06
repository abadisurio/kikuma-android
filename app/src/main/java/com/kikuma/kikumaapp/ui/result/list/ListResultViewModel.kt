package com.kikuma.kikumaapp.ui.result.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.DiseaseEntity
import com.kikuma.kikumaapp.data.source.local.entity.HistoryEntity
import com.kikuma.kikumaapp.data.source.local.entity.ModelResultEntity
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.vo.Resource

class ListResultViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getListModelResult(historyId: String): LiveData<Resource<List<ModelResultEntity>>> = homeRepository.getModelResults(historyId)
    fun refreshListModelResult(historyId: String): LiveData<Resource<List<ModelResultEntity>>> = homeRepository.refreshModelResults(historyId)
    fun getOneHistory(historyId: String): LiveData<Resource<HistoryEntity>> = homeRepository.getOneHistory(historyId)
//    fun setModelResult(imageBase64: String): LiveData<Resource<String>> = homeepository.setModelResult()
}