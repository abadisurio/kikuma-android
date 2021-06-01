package com.kikuma.kikumaapp.ui.result

import android.util.Log
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

    private val diseaseId = MutableLiveData<String>()
    val forDisease = MutableLiveData<String>()

    fun setResultDisease(diseaseId: String, forDisease: String) {
        this.diseaseId.value = diseaseId
        this.forDisease.value = forDisease
    }

    var getDisease: LiveData<Resource<DiseaseEntity>> = Transformations.switchMap(diseaseId) { mDiseaseId ->
        Log.d("ini diseaseId", diseaseId.value.toString())
        homeRepository.getDetailDisease(diseaseId.value.toString())
    }

    var getTips: LiveData<Resource<List<TipsEntity>>> = Transformations.switchMap(forDisease) { mTipsId ->
        homeRepository.getTipsForDisease(diseaseId.value.toString())
    }

    //fun getResult(): LiveData<DiseaseEntity> = homeRepository.getResultWithTips(resultId)

    //fun getTips(): LiveData<Resource<List<TipsEntity>>> = homeRepository.getAllTipsByResult(resultId)
}