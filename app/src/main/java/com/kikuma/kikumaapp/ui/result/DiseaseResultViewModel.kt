package com.kikuma.kikumaapp.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.DiseaseEntity
import com.kikuma.kikumaapp.data.source.local.entity.TipsEntity
import com.kikuma.kikumaapp.utils.DataDummy

class DiseaseResultViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private lateinit var resultId: String

    fun setResultCourse(resultId: String) {
        this.resultId = resultId
    }

    fun getResult(): LiveData<DiseaseEntity> = homeRepository.getResultWithTips(resultId)

    fun getTips(): LiveData<List<TipsEntity>> = homeRepository.getAllTipsByResult(resultId)
}