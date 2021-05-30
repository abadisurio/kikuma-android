package com.kikuma.kikumaapp.ui.result.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kikuma.kikumaapp.data.source.HomeRepository
import com.kikuma.kikumaapp.data.source.local.entity.DiseaseEntity
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.vo.Resource

class ListResultViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getListResult(): LiveData<Resource<List<DiseaseEntity>>> = homeRepository.getAllResult()
}