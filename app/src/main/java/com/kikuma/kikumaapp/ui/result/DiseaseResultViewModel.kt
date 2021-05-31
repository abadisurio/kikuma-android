package com.kikuma.kikumaapp.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.DiseaseEntity
import com.kikuma.kikumaapp.data.source.local.entity.TipsEntity
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.vo.Resource

class DiseaseResultViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    val resultId = MutableLiveData<String>()
    val forDisease = MutableLiveData<String>()

    fun setResultDisease(resultId: String, forDisease: String) {
        this.resultId.value = resultId
        this.forDisease.value = forDisease
    }

    var getResult: LiveData<Resource<DiseaseEntity>> = Transformations.switchMap(resultId) { mResultId ->
        homeRepository.getDetailDisease(mResultId)
    }

    var getTips: LiveData<Resource<List<TipsEntity>>> = Transformations.switchMap(forDisease) { mTipsId ->
        homeRepository.getTipsForDisease(mTipsId)
    }

    //fun getResult(): LiveData<DiseaseEntity> = homeRepository.getResultWithTips(resultId)

    //fun getTips(): LiveData<Resource<List<TipsEntity>>> = homeRepository.getAllTipsByResult(resultId)
}