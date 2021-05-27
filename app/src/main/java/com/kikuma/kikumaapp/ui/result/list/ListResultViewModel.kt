package com.kikuma.kikumaapp.ui.result.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.DiseaseEntity
import com.kikuma.kikumaapp.utils.DataDummy

class ListResultViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getListResult(): LiveData<List<DiseaseEntity>> = homeRepository.getAllResult()
}